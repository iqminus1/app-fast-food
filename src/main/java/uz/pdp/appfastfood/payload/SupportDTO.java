package uz.pdp.appfastfood.payload;

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
public class SupportDTO implements Serializable {
    private Integer id;
    private Integer fromUserId;
    private String text;
    private Integer toUserId;
    private SupportStatus status;
}