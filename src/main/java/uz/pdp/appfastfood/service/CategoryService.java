package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.CategoryCrudDTO;
import uz.pdp.appfastfood.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    ApiResultDTO<List<CategoryDTO>> read();

    ApiResultDTO<CategoryDTO> read(Integer id);

    ApiResultDTO<CategoryDTO> create(CategoryCrudDTO crudDTO);

    ApiResultDTO<CategoryDTO> update(CategoryCrudDTO crudDTO, Integer id);

    void delete(Integer id);
}
