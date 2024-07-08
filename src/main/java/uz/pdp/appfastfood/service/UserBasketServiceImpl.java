package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.*;
import uz.pdp.appfastfood.enums.OrderStatus;
import uz.pdp.appfastfood.enums.OrderType;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.UserBasketCrudDTO;
import uz.pdp.appfastfood.payload.UserBasketDTO;
import uz.pdp.appfastfood.repository.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static uz.pdp.appfastfood.utils.AppConstants.getUser;

@RequiredArgsConstructor
@Service
public class UserBasketServiceImpl implements UserBasketService {
    private final ProductRepository productRepository;
    private final UserBasketRepository userBasketRepository;
    private final PrizeProductRepository prizeProductRepository;
    private final OrderRepository orderRepository;
    private final FilialRepository filialRepository;

    @Override
    public ApiResultDTO<List<UserBasketDTO>> readAllUserBasket() {
        User user = getUser();
        return ApiResultDTO.success(userBasketRepository.findAllByUserId(user.getId()).stream()
                .map(this::toDTO).toList());
    }

    @Override
    public ApiResultDTO<UserBasketDTO> readUserBasket(Integer id) {
        UserBasket userBasket = checkUsersBasket(id);
        return ApiResultDTO.success(toDTO(userBasket));
    }


    @CacheEvict(value = "userBasketAll", allEntries = true)
    @Override
    public ApiResultDTO<UserBasketDTO> create(UserBasketCrudDTO crudDTO) {
        User user = getUser();
        UserBasket userBasket = new UserBasket(user,
                productRepository.find(crudDTO.getProductId()),
                crudDTO.getQuantity(),
                filialRepository.find(crudDTO.getFilialId()));
        userBasketRepository.save(userBasket);
        return ApiResultDTO.success(toDTO(userBasket));
    }


    @CacheEvict(key = "#id", value = {"userBasket", "userBasketAll"}, allEntries = true)
    @Override
    public ApiResultDTO<UserBasketDTO> update(UserBasketCrudDTO crudDTO, Integer id) {
        UserBasket userBasket = userBasketRepository.find(id);
        updateEntity(userBasket, crudDTO);
        userBasketRepository.save(userBasket);
        return ApiResultDTO.success(toDTO(userBasket));
    }

    @Override
    public ApiResultDTO<List<UserBasketDTO>> acceptBasket(Integer prizeId, Location location, OrderType type) {
        User user = getUser();
        List<UserBasket> allByUserId = userBasketRepository.findAllByUserId(user.getId());
        Map<Integer, List<UserBasket>> map = new HashMap<>();
        allByUserId.stream()
                .peek(basket -> {
                    if (!map.containsKey(basket.getFilial().getId())) {
                        map.put(basket.getFilial().getId(), new ArrayList<>());
                    }
                    map.get(basket.getFilial().getId()).add(basket);
                });

        List<Order> orders = new ArrayList<>();
        if (type == null) {
            type = OrderType.ORDER_COURIER;
        }
        for (Integer key : map.keySet()) {
            List<UserBasket> userBaskets = map.get(key);
            List<HistoryProduct> products = userBaskets.stream()
                    .map(userBasket -> new HistoryProduct(userBasket.getProduct()))
                    .toList();
            orders.add(new Order(user,
                    products,
                    OrderStatus.ACCEPTED_BASKET,
                    null,
                    location,
                    filialRepository.find(key),
                    type));
        }

        if (prizeId != null) {
            for (Order order : orders) {
                if (order.getPrize() != null) {
                    order.setPrize(prizeProductRepository.find(prizeId));
                    orderRepository.save(order);
                    break;
                }
            }
        }
        orderRepository.saveAll(orders);
        userBasketRepository.deleteAll(allByUserId);
        return ApiResultDTO.success(allByUserId.stream().map(this::toDTO).toList());
    }

    @CacheEvict(key = "#id", value = {"userBasket", "userBasketAll"}, allEntries = true)
    @Override
    public void delete(Integer id) {

        userBasketRepository.delete(checkUsersBasket(id));
    }

    @Override
    public void clearUserBasket() {
        User user = getUser();
        userBasketRepository.deleteAll(userBasketRepository.findAllByUserId(user.getId()));
    }

    private UserBasketDTO toDTO(UserBasket userBasket) {
        return new UserBasketDTO(userBasket.getId(),
                userBasket.getProduct().getId(),
                userBasket.getQuantity(),
                userBasket.getFilial().getId());
    }

    private void updateEntity(UserBasket userBasket, UserBasketCrudDTO crudDTO) {
        userBasket.setQuantity(crudDTO.getQuantity());
        userBasket.setProduct(productRepository.find(crudDTO.getProductId()));
    }


    private UserBasket checkUsersBasket(Integer id) {
        UserBasket userBasket = userBasketRepository.find(id);
        User user = getUser();
        if (!user.equals(userBasket.getUser())) {
            throw new RuntimeException("This is not your basket");
        }
        return userBasket;
    }
}
