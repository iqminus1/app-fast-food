package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Employee;
import uz.pdp.appfastfood.entity.Order;
import uz.pdp.appfastfood.entity.Payment;
import uz.pdp.appfastfood.enums.EmployeeTypeEnum;
import uz.pdp.appfastfood.enums.OrderStatus;
import uz.pdp.appfastfood.enums.PaymentType;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.PaymentDTO;
import uz.pdp.appfastfood.repository.EmployeeRepository;
import uz.pdp.appfastfood.repository.OrderRepository;
import uz.pdp.appfastfood.repository.PaymentRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static uz.pdp.appfastfood.utils.AppConstants.getUser;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ApiResultDTO<List<PaymentDTO>> paymentsByType(LocalDate start, LocalDate end, Integer filialId) {
        List<Payment> allByUpdateAtBetween = paymentRepository.findAllByUpdateAtBetween(Timestamp.valueOf(start.atStartOfDay()), Timestamp.valueOf(end.atStartOfDay()));
        List<PaymentDTO> paymentDTOS = allByUpdateAtBetween.stream()
                .filter(payment -> filialId == null || payment.getOrder().getFilial().getId().equals(filialId))
                .map(this::toDTO).toList();
        return ApiResultDTO.success(paymentDTOS);
    }


    @Override
    public ApiResultDTO<PaymentDTO> orderPayment(PaymentType type, Integer orderId) {
        Employee employee = employeeRepository.findEmployeeByUserId(getUser().getId());
        if (!List.of(EmployeeTypeEnum.COURIER, EmployeeTypeEnum.CASHIER, EmployeeTypeEnum.CASHIER).contains(employee.getType())) {
            throw new RuntimeException();
        }
        Order order = orderRepository.find(orderId);
        order.setStatus(OrderStatus.CLOSED_ORDER);
        orderRepository.save(order);
        Payment payment = new Payment(employee.getUser(), order, type);
        paymentRepository.save(payment);
        return ApiResultDTO.success(toDTO(payment));
    }

    @Override
    public void delete(Integer id) {
        paymentRepository.deleteById(id);
    }

    private PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(payment.getId(),
                payment.getUser().getId(),
                payment.getOrder().getId(),
                payment.getType());
    }
}
