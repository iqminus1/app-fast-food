package uz.pdp.appfastfood.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.enums.PaymentType;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.service.PaymentService;
import uz.pdp.appfastfood.utils.AppConstants;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).PAYMENT_READ.name())")
    @GetMapping
    public ApiResultDTO<?> paymentsByType(@RequestParam LocalDate start, @RequestParam LocalDate end, @RequestParam(required = false) Integer filialId) {
        return paymentService.paymentsByType(start, end, filialId);
    }

    @PostMapping("/{orderId}")
    public ApiResultDTO<?> orderPayment(@RequestParam PaymentType type, @PathVariable Integer orderId) {
        return paymentService.orderPayment(type, orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        paymentService.delete(id);
    }
}
