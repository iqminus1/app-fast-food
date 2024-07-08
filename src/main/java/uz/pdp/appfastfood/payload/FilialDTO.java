package uz.pdp.appfastfood.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Filial}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilialDTO implements Serializable {
    private Integer id;
    private Integer regionId;
    private String nameUz;
    private String descriptionUz;
    private String nameRu;
    private String descriptionRU;
    private double latitude;
    private double longitude;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openAt;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closeAt;
    private boolean open;
}