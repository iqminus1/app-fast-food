package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.RegionCrudDTO;
import uz.pdp.appfastfood.service.RegionService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/region")
public class RegionController {
    private final RegionService regionService;

    @GetMapping()
    public ApiResultDTO<?> read() {
        return regionService.read();
    }

    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return regionService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).REGION_CREATE.name())")
    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid RegionCrudDTO crudDTO) {
        return regionService.create(crudDTO);
    }
    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).REGION_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid RegionCrudDTO crudDTO, @PathVariable Integer id) {
        return regionService.update(crudDTO, id);
    }
    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).REGION_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        regionService.delete(id);
    }
}
