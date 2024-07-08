package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ApiResultDTO<List<User>> read() {
        return ApiResultDTO.success(userRepository.findAll());
    }

    @Override
    public ApiResultDTO<User> read(Integer id) {
        return ApiResultDTO.success(userRepository.find(id));
    }

    @Override
    public ApiResultDTO<User> setStatus(boolean status, Integer id) {
        User user = userRepository.find(id);
        user.setEnable(status);
        userRepository.save(user);
        return ApiResultDTO.success(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
