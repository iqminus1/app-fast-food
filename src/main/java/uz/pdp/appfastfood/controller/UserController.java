package uz.pdp.appfastfood.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.service.UserService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/user")
public class UserController {
    private final UserService userService;


    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).USER_READ.name())")
    @GetMapping
    public ApiResultDTO<?> read() {
        return userService.read();
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).USER_READ.name())")
    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return userService.read(id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).USER_CHANGE_STATUS.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> setStatus(@PathVariable Integer id, boolean status) {
        return userService.setStatus(status, id);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).USER_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userService.delete(id);
    }
}
