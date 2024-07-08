package uz.pdp.appfastfood.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import uz.pdp.appfastfood.trigger.LocalDateDeserializer;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpDTO {
    @NotNull
    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    @NotBlank
    private String fullName;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    @Length(min = 8, max = 32)
    private String password;

    private Integer regionId;
}
