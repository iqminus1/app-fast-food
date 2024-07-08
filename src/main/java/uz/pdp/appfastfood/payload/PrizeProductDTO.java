package uz.pdp.appfastfood.payload;

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
public class PrizeProductDTO implements Serializable {
    private Integer id;
    private Integer productId;
    private Integer quantity;
}