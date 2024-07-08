package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.ProductCrudDTO;
import uz.pdp.appfastfood.payload.ProductDTO;

import java.util.List;

public interface ProductService {
    ApiResultDTO<List<ProductDTO>> readByDiscount();
    ApiResultDTO<List<ProductDTO>> read();

    ApiResultDTO<ProductDTO> read(Integer id);

    ApiResultDTO<ProductDTO> create(ProductCrudDTO crudDTO);

    ApiResultDTO<ProductDTO> update(ProductCrudDTO crudDTO, Integer id);

    void delete(Integer id);
}
