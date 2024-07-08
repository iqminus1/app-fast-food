package uz.pdp.appfastfood.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appfastfood.entity.Region;
import uz.pdp.appfastfood.entity.Role;
import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.enums.PermissionEnum;
import uz.pdp.appfastfood.enums.RoleTypeEnum;
import uz.pdp.appfastfood.repository.RegionRepository;
import uz.pdp.appfastfood.repository.RoleRepository;
import uz.pdp.appfastfood.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RegionRepository regionRepository;

    @Override
    public void run(String... args) throws Exception {
        checkAdmin();
        checkUser();
    }


    private void checkUser() {
        if (userRepository.findByEmail("user@gmail.com").isPresent()) {
            return;
        }
        User user = new User("+998990000000",
                "user@gmail.com",
                "User User",
                LocalDate.of(2000, 1, 1),
                passwordEncoder.encode("123"),
                null,
                true,
                roleRepository.findByType(RoleTypeEnum.USER).orElseGet(() -> {
                    Role role = new Role("User role", new ArrayList<>(), RoleTypeEnum.USER);
                    roleRepository.save(role);
                    return role;
                })
        );
        userRepository.save(user);
    }

    private void checkAdmin() {
        if (userRepository.findByEmail("admin@gmail.com").isPresent()) {
            return;
        }
        User user = new User("+998900000000",
                "admin@gmail.com",
                "Admin Admin",
                LocalDate.of(2000, 1, 1),
                passwordEncoder.encode("123"),
                null,
                true,
                roleRepository.findByType(RoleTypeEnum.ADMIN).orElseGet(() -> {
                    Role role = new Role("Admin role", Arrays.stream(PermissionEnum.values()).toList(), RoleTypeEnum.ADMIN);
                    roleRepository.save(role);
                    return role;
                })
        );
        userRepository.save(user);
    }
}
