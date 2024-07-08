package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import uz.pdp.appfastfood.entity.Code;
import uz.pdp.appfastfood.repository.CodeRepository;

import java.util.Random;

@Component
@EnableAsync
@RequiredArgsConstructor
public class MailService {
    private final Random random;
    private final CodeRepository codeRepository;
    private final JavaMailSender javaMailSender;


    @Async
    public void send(String to, String text, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("Fast-Food");
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void verifySend(String email) {
        String codeString = generateCode();
        Code code = codeRepository.findByEmail(email).orElseGet(Code::new);
        code.setAttempt(5);
        code.setCode(codeString);
        code.setEmail(email);
        codeRepository.save(code);
        String text = "For verify you most click this link -> http://localhost:80/api/v1/auth/verification-account?code=%s&email=%s".formatted(codeString, email);
        send(email, text, "Fast food");

    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()) {
                sb.append(random.nextInt(0, 9));
            } else {
                if (random.nextBoolean())
                    sb.append((char) random.nextInt(65, 90));
                else
                    sb.append((char) random.nextInt(97, 122));
            }
        }
        return sb.toString();
    }
}
