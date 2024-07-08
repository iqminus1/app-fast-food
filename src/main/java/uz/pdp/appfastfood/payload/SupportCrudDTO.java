package uz.pdp.appfastfood.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.enums.SupportStatus;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Support}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportCrudDTO implements Serializable {
    private Integer fromUserId;
    @NotNull
    @NotBlank
    private String text;
    private Integer toUserId;
    private SupportStatus status;
}