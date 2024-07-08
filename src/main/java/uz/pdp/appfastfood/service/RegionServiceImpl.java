package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Region;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.RegionCrudDTO;
import uz.pdp.appfastfood.payload.RegionDTO;
import uz.pdp.appfastfood.repository.RegionRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Cacheable(value = "regionAll")
    @Override
    public ApiResultDTO<List<RegionDTO>> read() {
        return ApiResultDTO.success(regionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList());
    }

    @Cacheable(key = "#id", value = "region")
    @Override
    public ApiResultDTO<RegionDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(regionRepository.find(id)));
    }

    @Override
    @CacheEvict(key = "#result.data.id", value = {"region", "regionAll"}, allEntries = true)
    public ApiResultDTO<RegionDTO> create(RegionCrudDTO crudDTO) {
        Region region = new Region(crudDTO.getNameRu(), crudDTO.getNameUz());
        regionRepository.save(region);
        return ApiResultDTO.success(toDTO(region));
    }

    @CachePut(key = "#id", value = "region")
    @CacheEvict(value = "regionAll", allEntries = true)
    @Override
    public ApiResultDTO<RegionDTO> update(RegionCrudDTO crudDTO, Integer id) {
        Region region = regionRepository.find(id);
        region.setNameRu(crudDTO.getNameRu());
        region.setNameUz(crudDTO.getNameUz());
        regionRepository.save(region);
        return ApiResultDTO.success(toDTO(region));
    }

    @CacheEvict(key = "#id", value = {"region", "regionAll"}, allEntries = true)
    @Override
    public void delete(Integer id) {
        regionRepository.deleteById(id);
    }

    private RegionDTO toDTO(Region region) {
        return new RegionDTO(region.getId(), region.getNameRu(), region.getNameUz());
    }
}
