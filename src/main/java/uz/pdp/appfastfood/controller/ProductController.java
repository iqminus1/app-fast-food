package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.ProductCrudDTO;
import uz.pdp.appfastfood.service.ProductService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/discount")
    public ApiResultDTO<?> readByDiscount() {
        return productService.readByDiscount();
    }

    @GetMapping
    public ApiResultDTO<?> read() {
        return productService.read();
    }

    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return productService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).PRODUCT_CREATE.name())")
    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid ProductCrudDTO crudDTO) {
        return productService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).PRODUCT_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid ProductCrudDTO crudDTO, @PathVariable Integer id) {
        return productService.update(crudDTO, id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).PRODUCT_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }
}
