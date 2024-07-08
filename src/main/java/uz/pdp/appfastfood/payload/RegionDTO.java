package uz.pdp.appfastfood.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Region}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO implements Serializable {
    private Integer id;
    private String nameRu;
    private String nameUz;
}