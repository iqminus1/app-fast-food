package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.DeliveryCrudDTO;
import uz.pdp.appfastfood.service.DeliveryService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;
    @GetMapping
    public ApiResultDTO<?> read() {
        return deliveryService.read();
    }

    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return deliveryService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).DELIVERY_CREATE.name())")
    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid DeliveryCrudDTO crudDTO) {
        return deliveryService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).DELIVERY_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid DeliveryCrudDTO crudDTO, @PathVariable Integer id) {
        return deliveryService.update(crudDTO, id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).DELIVERY_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        deliveryService.delete(id);
    }
}
