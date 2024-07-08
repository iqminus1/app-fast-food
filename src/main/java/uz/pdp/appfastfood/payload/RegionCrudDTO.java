package uz.pdp.appfastfood.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RegionCrudDTO implements Serializable {
    @NotNull
    @NotBlank
    private String nameRu;
    @NotNull
    @NotBlank
    private String nameUz;
}