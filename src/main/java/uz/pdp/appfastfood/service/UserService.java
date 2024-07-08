package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.payload.ApiResultDTO;

import java.util.List;

public interface UserService {
    ApiResultDTO<List<User>> read();

    ApiResultDTO<User> read(Integer id);

    ApiResultDTO<User> setStatus(boolean status,Integer id);

    void delete(Integer id);
}
