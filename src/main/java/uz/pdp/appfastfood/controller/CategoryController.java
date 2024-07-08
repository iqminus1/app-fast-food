package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.CategoryCrudDTO;
import uz.pdp.appfastfood.service.CategoryService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.BASE_PATH_V1 + "/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ApiResultDTO<?> read() {
        return categoryService.read();
    }

    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return categoryService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).CATEGORY_CREATE.name())")
    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid CategoryCrudDTO crudDTO) {
        return categoryService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).CATEGORY_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid CategoryCrudDTO crudDTO, @PathVariable Integer id) {
        return categoryService.update(crudDTO, id);
    }

    @PreAuthorize("hasAnyAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).CATEGORY_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}
