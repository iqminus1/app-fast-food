package uz.pdp.appfastfood.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.PrizeProduct}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizeProductCrudDTO implements Serializable {
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;
}