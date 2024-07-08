package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.FilialCrudDTO;
import uz.pdp.appfastfood.payload.FilialDTO;
import uz.pdp.appfastfood.payload.RegionDTO;

import java.util.List;
import java.util.Map;

public interface FilialService {
    ApiResultDTO<Map<RegionDTO, List<FilialDTO>>> read();
    ApiResultDTO<FilialDTO> create(FilialCrudDTO crudDTO);
    ApiResultDTO<FilialDTO> update(FilialCrudDTO crudDTO, Integer id);
    void delete(Integer id);
}
