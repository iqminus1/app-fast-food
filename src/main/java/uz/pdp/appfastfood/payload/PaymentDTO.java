package uz.pdp.appfastfood.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.enums.PaymentType;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Payment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer orderId;
    private PaymentType type;
}