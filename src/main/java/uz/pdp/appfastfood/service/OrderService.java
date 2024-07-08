package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.entity.Location;
import uz.pdp.appfastfood.enums.OrderStatus;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.OrderDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {
    ApiResultDTO<List<OrderDTO>> read();
    ApiResultDTO<List<OrderDTO>> readForCourier();
    ApiResultDTO<List<OrderDTO>> readByFilial(Integer filialId);

    ApiResultDTO<Map<OrderStatus, List<OrderDTO>>> readByStatus();

    ApiResultDTO<OrderDTO> read(Integer id);

    ApiResultDTO<OrderDTO> updateStatus(Integer id, OrderStatus status);

    ApiResultDTO<OrderDTO> updateLocation(Integer id, Location location);
}
