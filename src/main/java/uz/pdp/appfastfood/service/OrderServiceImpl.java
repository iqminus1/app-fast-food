package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.*;
import uz.pdp.appfastfood.enums.EmployeeTypeEnum;
import uz.pdp.appfastfood.enums.OrderStatus;
import uz.pdp.appfastfood.enums.OrderType;
import uz.pdp.appfastfood.enums.PermissionEnum;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.OrderDTO;
import uz.pdp.appfastfood.repository.EmployeeRepository;
import uz.pdp.appfastfood.repository.OrderRepository;

import java.util.*;

import static uz.pdp.appfastfood.utils.AppConstants.getUser;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ApiResultDTO<List<OrderDTO>> read() {
        return ApiResultDTO.success(orderRepository.findAll().stream()
                .map(this::toDTO)
                .toList());
    }

    @Override
    public ApiResultDTO<List<OrderDTO>> readForCourier() {
        Employee employee = employeeRepository.findEmployeeByUserId(getUser().getId());
        if (!List.of(EmployeeTypeEnum.COURIER, EmployeeTypeEnum.CASHIER, EmployeeTypeEnum.MANAGER).contains(employee.getType())) {
            throw new RuntimeException();
        }
        List<Order> forCourier = orderRepository
                .findAll()
                .stream()
                .filter(order -> order.getType().equals(OrderType.ORDER_COURIER)
                                && Objects.equals(OrderStatus.ACCEPTED_ORDER, order.getStatus())
                ).toList();
        return ApiResultDTO.success(forCourier.stream().map(this::toDTO).toList());
    }

    @Override
    public ApiResultDTO<List<OrderDTO>> readByFilial(Integer filialId) {
        return ApiResultDTO.success(orderRepository.findAllByFilialId(filialId).stream()
                .map(this::toDTO).toList());
    }

    @Override
    public ApiResultDTO<Map<OrderStatus, List<OrderDTO>>> readByStatus() {
        User user = getUser();
        if (!user.getRole().getPermissions().contains(PermissionEnum.ORDER_READ_ALL)) {
            throw new RuntimeException("403 Forbidden");
        }
        Map<OrderStatus, List<OrderDTO>> map = new HashMap<>();
        orderRepository.findAll().stream()
                .peek(order -> {
                    if (!map.containsKey(order.getStatus())) {
                        map.put(order.getStatus(), new ArrayList<>());
                    }
                    map.get(order.getStatus()).add(toDTO(order));
                });
        return ApiResultDTO.success(map);
    }

    @Override
    public ApiResultDTO<OrderDTO> read(Integer id) {
        User user = getUser();
        Order order = orderRepository.find(id);
        checkOrder(user, order, PermissionEnum.ORDER_READ);
        return null;
    }

    @Override
    public ApiResultDTO<OrderDTO> updateStatus(Integer id, OrderStatus status) {
        User user = getUser();
        Order order = orderRepository.find(id);
        checkOrder(user, order, PermissionEnum.ORDER_UPDATE);
        order.setStatus(status);
        orderRepository.save(order);
        return ApiResultDTO.success(toDTO(order));
    }

    @Override
    public ApiResultDTO<OrderDTO> updateLocation(Integer id, Location location) {
        User user = getUser();
        Order order = orderRepository.find(id);
        checkOrder(user, order, PermissionEnum.ORDER_UPDATE);
        order.setLocation(location);
        orderRepository.save(order);

        return ApiResultDTO.success(toDTO(order));
    }

    private void checkOrder(User user, Order order, PermissionEnum permissionEnum) {
        if (!user.getId().equals(order.getUser().getId())) {
            if (!user.getRole().getPermissions().contains(permissionEnum)) {
                throw new RuntimeException();
            }
        }
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO(
                order.getId(),
                order.getUser().getId(),
                order.getProducts().stream().map(HistoryProduct::getId).toList(),
                order.getStatus(),
                null,
                order.getLocation().getId(),
                order.getFilial().getId(),
                order.getType());

        if (order.getPrize() != null) {
            orderDTO.setPrizeId(order.getPrize().getId());
        }
        return orderDTO;
    }

}
