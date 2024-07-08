package uz.pdp.appfastfood.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Delivery}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryCrudDTO implements Serializable {
    private Integer filialId;
    private Double priceKm;
    private Double minPrice;
    private String textUz;
    private String textRu;
}