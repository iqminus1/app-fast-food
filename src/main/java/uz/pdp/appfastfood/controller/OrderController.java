package uz.pdp.appfastfood.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.entity.Location;
import uz.pdp.appfastfood.enums.OrderStatus;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.service.OrderService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ApiResultDTO<?> read() {
        return orderService.read();
    }

    @GetMapping("/{filialId}")
    public ApiResultDTO<?> readByFilialId(@PathVariable Integer filialId) {
        return orderService.readByFilial(filialId);
    }

    @GetMapping("/by-status")
    public ApiResultDTO<?> readByStatus() {
        return orderService.readByStatus();
    }

    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return orderService.read(id);
    }
    @GetMapping("/courier")
    public ApiResultDTO<?> readForCourier(){
        return orderService.readForCourier();
    }

    @PutMapping("/{orderId}/{status}")
    public ApiResultDTO<?> updateStatus(@PathVariable Integer orderId, @PathVariable OrderStatus status) {
        return orderService.updateStatus(orderId, status);
    }

    @PutMapping("/{orderId}")
    public ApiResultDTO<?> updateLocation(@PathVariable Integer orderId, @RequestBody Location location) {
        return orderService.updateLocation(orderId, location);
    }
}
