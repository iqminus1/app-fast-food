package uz.pdp.appfastfood.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.enums.PermissionEnum;

public interface AppConstants {
    String BASE_PATH_V1 = "/api/v1";

    static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    static void checkPermission(PermissionEnum permissionEnum) {
        if (!getUser().getRole().getPermissions().contains(permissionEnum)) {
            throw new RuntimeException();
        }
    }
}
