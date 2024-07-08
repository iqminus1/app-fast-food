package uz.pdp.appfastfood.service;

import org.springframework.data.domain.Page;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.ThoughtCrudDTO;
import uz.pdp.appfastfood.payload.ThoughtDTO;

public interface ThoughtService {
    ApiResultDTO<Page<ThoughtDTO>> read(int page, int size);

    ApiResultDTO<ThoughtDTO> read(Integer id);

    ApiResultDTO<ThoughtDTO> create(ThoughtCrudDTO crudDTO);

    ApiResultDTO<ThoughtDTO> update(ThoughtCrudDTO crudDTO, Integer id);

    void delete(Integer id);
}
