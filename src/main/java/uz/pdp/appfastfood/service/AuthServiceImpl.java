package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Code;
import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.enums.RoleTypeEnum;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.SignInDTO;
import uz.pdp.appfastfood.payload.SignUpDTO;
import uz.pdp.appfastfood.payload.TokenDTO;
import uz.pdp.appfastfood.repository.CodeRepository;
import uz.pdp.appfastfood.repository.RegionRepository;
import uz.pdp.appfastfood.repository.RoleRepository;
import uz.pdp.appfastfood.repository.UserRepository;
import uz.pdp.appfastfood.security.JwtProvider;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegionRepository regionRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final CodeRepository codeRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationProvider authProvider;


    @Cacheable(key = "#username", value = "user.email")
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username);
    }

    @Override
    public String signUp(SignUpDTO signUp) {
        User user = new User(signUp.getPhoneNumber(),
                signUp.getEmail(),
                signUp.getFullName(),
                signUp.getBirthDate(),
                passwordEncoder.encode(signUp.getPassword()),
                regionRepository.findById(signUp.getRegionId()).orElseThrow(),
                false,
                roleRepository.findByType(RoleTypeEnum.USER).orElseThrow()
        );
        userRepository.save(user);

        mailService.verifySend(user.getEmail());

        return "ok";
    }

    @Override
    public ApiResultDTO<?> verify(String code, String email) {
        Code codeEntity = codeRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("this email has not any verify code"));
        if (codeEntity.getCode().equals(code)) {
            codeRepository.delete(codeEntity);
            User user = userRepository.findByEmail(email).orElseThrow();
            user.setEnable(true);
            userRepository.save(user);
            return ApiResultDTO.success(new TokenDTO(jwtProvider.generateToken(email)));
        }
        if (codeEntity.getAttempt() == 1) {
            User user = userRepository.findByEmail(email).orElseThrow();
            userRepository.delete(user);
            codeRepository.delete(codeEntity);
            throw new RuntimeException("code is not equal and deleted account");
        }
        codeEntity.setAttempt(codeEntity.getAttempt() - 1);
        codeRepository.save(codeEntity);
        return ApiResultDTO.error("code is not equals your attempt count -> " + codeEntity.getAttempt());
    }

    @CacheEvict(key = "#signInDTO.email", value = "user")
    @Override
    public ApiResultDTO<TokenDTO> signIn(SignInDTO signInDTO) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword());
        authProvider.authenticate(authentication);
        String token = jwtProvider.generateToken(signInDTO.getEmail());
//        codeRepository.findByEmail(signInDTO.getEmail()).ifPresent(codeRepository::delete);
        return ApiResultDTO.success(new TokenDTO(token));
    }


}
