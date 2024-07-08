package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.SignInDTO;
import uz.pdp.appfastfood.payload.SignUpDTO;
import uz.pdp.appfastfood.payload.TokenDTO;
import uz.pdp.appfastfood.service.AuthServiceImpl;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.BASE_PATH_V1 + "/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(@Lazy AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public Object signUp(@RequestBody @Valid SignUpDTO signUp) {
        return authService.signUp(signUp);
    }

    @GetMapping("/verification-account")
    public ApiResultDTO<?> verify(@RequestParam String code, @RequestParam String email) {
        return authService.verify(code, email);
    }

    @PostMapping("/sign-in")
    public ApiResultDTO<TokenDTO> signIn(@RequestBody @Valid SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }
}
