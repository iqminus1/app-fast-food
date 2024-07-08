package uz.pdp.appfastfood.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.entity.Order;
import uz.pdp.appfastfood.enums.OrderStatus;
import uz.pdp.appfastfood.enums.OrderType;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Integer id;
    private Integer userId;
    private List<Integer> productIds;
    private OrderStatus status;
    private Integer prizeId;
    private Integer locationId;
    private Integer filialId;
    private OrderType type;
}