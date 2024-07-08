package uz.pdp.appfastfood.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.entity.Category;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryCrudDTO implements Serializable {
    @NotBlank
    @NotNull
    private String nameRu;
    @NotBlank
    @NotNull
    private String nameUz;

    private Integer parentCategoryId;
}