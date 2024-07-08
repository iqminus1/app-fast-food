package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.PrizeProductCrudDTO;
import uz.pdp.appfastfood.service.PrizeProductService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/prize-product")
public class PrizeProductController {
    private final PrizeProductService prizeProductService;


    @GetMapping
    public ApiResultDTO<?> read() {
        return prizeProductService.read();
    }

    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return prizeProductService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).PRODUCT_CREATE.name())")
    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid PrizeProductCrudDTO crudDTO) {
        return prizeProductService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).PRODUCT_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid PrizeProductCrudDTO crudDTO, @PathVariable Integer id) {
        return prizeProductService.update(crudDTO, id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).PRODUCT_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        prizeProductService.delete(id);
    }
}
