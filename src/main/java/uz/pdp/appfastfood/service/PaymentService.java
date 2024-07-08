package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.enums.PaymentType;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.PaymentDTO;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    ApiResultDTO<List<PaymentDTO>> paymentsByType(LocalDate start, LocalDate end, Integer filialId);

    ApiResultDTO<PaymentDTO> orderPayment(PaymentType type, Integer orderId);

    void delete(Integer id);
}
