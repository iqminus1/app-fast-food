package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.enums.SupportStatus;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.SupportCrudDTO;
import uz.pdp.appfastfood.service.SupportService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/support")
public class SupportController {
    private final SupportService supportService;

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).SUPPORT_READ_ALL.name())")
    @GetMapping
    public ApiResultDTO<?> readAllSMSFromUsers() {
        return supportService.allSMSFromUsers();
    }

    @GetMapping("/{status}")
    public ApiResultDTO<?> readByStatus(@PathVariable SupportStatus status) {
        return supportService.smsByType(status);
    }

    @PostMapping
    public ApiResultDTO<?> sendSMS(@RequestBody @Valid SupportCrudDTO crudDTO) {
        return supportService.sendSMS(crudDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSMS(@PathVariable Integer id) {
        supportService.deleteSMS(id);
    }
}
