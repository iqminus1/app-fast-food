package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.RegionCrudDTO;
import uz.pdp.appfastfood.payload.RegionDTO;

import java.util.List;

public interface RegionService {
    ApiResultDTO<List<RegionDTO>> read();

    ApiResultDTO<RegionDTO> read(Integer id);

    ApiResultDTO<RegionDTO> create(RegionCrudDTO crudDTO);

    ApiResultDTO<RegionDTO> update(RegionCrudDTO crudDTO, Integer id);

    void delete(Integer id);
}
