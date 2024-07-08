package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Filial;
import uz.pdp.appfastfood.entity.Region;
import uz.pdp.appfastfood.mapper.FilialMapper;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.FilialCrudDTO;
import uz.pdp.appfastfood.payload.FilialDTO;
import uz.pdp.appfastfood.payload.RegionDTO;
import uz.pdp.appfastfood.repository.FilialRepository;
import uz.pdp.appfastfood.repository.RegionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class FilialServiceImpl implements FilialService {
    private final FilialRepository filialRepository;
    private final FilialMapper filialMapper;
    private final RegionRepository regionRepository;

    @Cacheable(value = "filialAll")
    @Override
    public ApiResultDTO<Map<RegionDTO, List<FilialDTO>>> read() {
        Map<Region, List<Filial>> map = new HashMap<>();
        for (Filial filial : filialRepository.findAll()) {
            Region region = filial.getRegion();

            if (!map.containsKey(region)) {
                map.put(region, new ArrayList<>());
            }

            map.get(region).add(filial);
        }

        Map<RegionDTO, List<FilialDTO>> responseMap = new HashMap<>();
        for (Region region : map.keySet()) {
            List<Filial> filialsList = map.get(region);
            responseMap.put(
                    regionToDTO(region),
                    filialsList.stream()
                            .map(this::filialMapTo)
                            .toList());
        }
        return ApiResultDTO.success(responseMap);
    }

    @CacheEvict(value = "filialAll", allEntries = true)
    @Override
    public ApiResultDTO<FilialDTO> create(FilialCrudDTO crudDTO) {
        Filial filial = new Filial();
        updateEntity(filial, crudDTO);
        filialRepository.save(filial);
        return ApiResultDTO.success(filialMapTo(filial));
    }

    @CacheEvict(value = "filialAll", allEntries = true)
    @Override
    public ApiResultDTO<FilialDTO> update(FilialCrudDTO crudDTO, Integer id) {
        Filial filial = filialRepository.find(id);
        updateEntity(filial, crudDTO);
        filialRepository.save(filial);
        return ApiResultDTO.success(filialMapTo(filial));
    }

    @CacheEvict(value = "filialAll", allEntries = true)
    @Override
    public void delete(Integer id) {
        filialRepository.deleteById(id);
    }


    private FilialDTO filialMapTo(Filial filial) {
        FilialDTO dto = filialMapper.toDto(filial);
        dto.setRegionId(filial.getRegion().getId());
        return dto;
    }

    private RegionDTO regionToDTO(Region region) {
        return new RegionDTO(region.getId(), region.getNameRu(), region.getNameUz());
    }

    private void updateEntity(Filial filial, FilialCrudDTO crudDTO) {
        filialMapper.updateEntity(filial, crudDTO);
        filial.setRegion(regionRepository.find(crudDTO.getRegionId()));
    }
}
