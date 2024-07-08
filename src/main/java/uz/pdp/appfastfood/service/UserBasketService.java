package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.entity.Location;
import uz.pdp.appfastfood.enums.OrderType;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.UserBasketCrudDTO;
import uz.pdp.appfastfood.payload.UserBasketDTO;

import java.util.List;

public interface UserBasketService {
    ApiResultDTO<List<UserBasketDTO>> readAllUserBasket();

    ApiResultDTO<UserBasketDTO> readUserBasket(Integer id);

    ApiResultDTO<UserBasketDTO> create(UserBasketCrudDTO crudDTO);

    ApiResultDTO<UserBasketDTO> update(UserBasketCrudDTO crudDTO, Integer id);

    ApiResultDTO<List<UserBasketDTO>> acceptBasket(Integer prizeId, Location location, OrderType type);

    void delete(Integer id);

    void clearUserBasket();
}
