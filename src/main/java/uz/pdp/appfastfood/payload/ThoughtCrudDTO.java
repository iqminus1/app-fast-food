package uz.pdp.appfastfood.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.enums.ThoughtType;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Thought}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThoughtCrudDTO implements Serializable {
    private ThoughtType type;
    private String message;
}