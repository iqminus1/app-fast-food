package uz.pdp.appfastfood.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.trigger.LocalTimeDeserializer;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Filial}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilialCrudDTO implements Serializable {
    @NotNull
    private Integer regionId;

    @NotNull
    @NotBlank
    private String nameUz;

    @NotNull
    @NotBlank
    private String descriptionUz;

    @NotNull
    @NotBlank
    private String nameRu;

    @NotNull
    @NotBlank
    private String descriptionRU;

    private double latitude;
    private double longitude;

    @NotNull
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime openAt;

    @NotNull
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime closeAt;

    private boolean open;

}