package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.PrizeProductCrudDTO;
import uz.pdp.appfastfood.payload.PrizeProductDTO;

import java.util.List;

public interface PrizeProductService {
    ApiResultDTO<List<PrizeProductDTO>> read();

    ApiResultDTO<PrizeProductDTO> read(Integer id);

    ApiResultDTO<PrizeProductDTO> create(PrizeProductCrudDTO crudDTO);

    ApiResultDTO<PrizeProductDTO> update(PrizeProductCrudDTO crudDTO, Integer id);

    void delete(Integer id);
}
