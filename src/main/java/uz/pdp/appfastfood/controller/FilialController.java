package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.FilialCrudDTO;
import uz.pdp.appfastfood.service.FilialService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/filial")
public class FilialController {
    private final FilialService filialService;

    @GetMapping()
    public ApiResultDTO<?> read() {
        return filialService.read();
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).FILIAL_CREATE.name())")
    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid FilialCrudDTO crudDTO) {
        return filialService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).FILIAL_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid FilialCrudDTO crudDTO, @PathVariable Integer id) {
        return filialService.update(crudDTO, id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).FILIAL_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        filialService.delete(id);
    }
}
