package uz.pdp.appfastfood.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Category;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.CategoryCrudDTO;
import uz.pdp.appfastfood.payload.CategoryDTO;
import uz.pdp.appfastfood.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Cacheable(value = "categoryAll")
    @Override
    public ApiResultDTO<List<CategoryDTO>> read() {
        List<CategoryDTO> all = categoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
        return ApiResultDTO.success(all);
    }


    @Cacheable(key = "#id", value = "category")
    @Override
    public ApiResultDTO<CategoryDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(categoryRepository.find(id)));
    }

    @CacheEvict(value = "categoryAll", allEntries = true)
    @Transactional
    @Override
    public ApiResultDTO<CategoryDTO> create(CategoryCrudDTO crudDTO) {
        Category parentCategory = null;
        if (crudDTO.getParentCategoryId() != null) {
            parentCategory = categoryRepository.find(crudDTO.getParentCategoryId());
        }
        Category category = new Category(crudDTO.getNameRu(), crudDTO.getNameUz(), parentCategory);
        categoryRepository.save(category);
        return ApiResultDTO.success(toDTO(category));
    }

    @CachePut(key = "#id", value = "category")
    @CacheEvict(value = "categoryAll", allEntries = true)
    @Override
    public ApiResultDTO<CategoryDTO> update(CategoryCrudDTO crudDTO, Integer id) {
        Category category = categoryRepository.find(id);
        Category parentCategory = null;
        if (crudDTO.getParentCategoryId() != null) {
            parentCategory = categoryRepository.find(crudDTO.getParentCategoryId());
        }
        category.setParentCategory(parentCategory);
        category.setNameRu(crudDTO.getNameRu());
        category.setNameUz(crudDTO.getNameUz());
        categoryRepository.save(category);
        return ApiResultDTO.success(toDTO(category));
    }

    @CacheEvict(key = "#id", value = {"category", "categoryAll"}, allEntries = true)
    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO toDTO(Category category) {
        Integer parentCategoryId = null;
        if (category.getParentCategory() != null) {
            parentCategoryId = category.getParentCategory().getId();
        }
        return new CategoryDTO(category.getId(), category.getNameRu(), category.getNameUz(), parentCategoryId);
    }
}
