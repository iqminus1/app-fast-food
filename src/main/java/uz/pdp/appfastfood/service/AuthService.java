package uz.pdp.appfastfood.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.SignInDTO;
import uz.pdp.appfastfood.payload.SignUpDTO;
import uz.pdp.appfastfood.payload.TokenDTO;

public interface AuthService extends UserDetailsService {
    String signUp(SignUpDTO signUp);

    ApiResultDTO<?> verify(String code, String email);

    ApiResultDTO<TokenDTO> signIn(SignInDTO signInDTO);
}
