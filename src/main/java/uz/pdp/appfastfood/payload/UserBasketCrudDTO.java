package uz.pdp.appfastfood.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.UserBasket}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasketCrudDTO implements Serializable {
    @NotNull
    private Integer productId;
    @Min(1)
    private Integer quantity;
    @NotNull
    private Integer filialId;
}