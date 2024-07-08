package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.entity.Location;
import uz.pdp.appfastfood.enums.OrderType;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.UserBasketCrudDTO;
import uz.pdp.appfastfood.payload.UserBasketDTO;
import uz.pdp.appfastfood.service.UserBasketService;
import uz.pdp.appfastfood.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/user-basket")
public class UserBasketController {
    private final UserBasketService userBasketService;

    @GetMapping
    public ApiResultDTO<List<UserBasketDTO>> readAllUserBasket() {
        return userBasketService.readAllUserBasket();
    }

    @GetMapping("/{id}")
    public ApiResultDTO<UserBasketDTO> readUserBasket(@PathVariable Integer id) {
        return userBasketService.readUserBasket(id);
    }

    @PostMapping
    public ApiResultDTO<UserBasketDTO> create(@RequestBody @Valid UserBasketCrudDTO crudDTO) {
        return userBasketService.create(crudDTO);
    }

    @PostMapping
    public ApiResultDTO<?> acceptBasket(@RequestBody Location location, @RequestParam Integer prizeId, @RequestParam OrderType type) {
        return userBasketService.acceptBasket(prizeId, location, type);
    }

    @PutMapping("/{id}")
    public ApiResultDTO<UserBasketDTO> update(@RequestBody @Valid UserBasketCrudDTO crudDTO, @PathVariable Integer id) {
        return userBasketService.update(crudDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userBasketService.delete(id);
    }

    @DeleteMapping
    public void clearUserBasket() {
        userBasketService.clearUserBasket();
    }
}
