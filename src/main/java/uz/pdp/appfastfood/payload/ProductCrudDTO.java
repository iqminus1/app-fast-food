package uz.pdp.appfastfood.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.entity.Product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCrudDTO implements Serializable {
    @NotNull
    private Integer categoryId;
    @NotNull
    @NotBlank
    private String nameRu;
    @NotNull
    @NotBlank
    private String nameUz;
    @NotNull
    private Double price;
    private Double discount;

    private Integer attachmentId;
}