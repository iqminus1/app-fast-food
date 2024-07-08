package uz.pdp.appfastfood.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appfastfood.enums.EmployeeTypeEnum;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.appfastfood.entity.Employee}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCrudDTO implements Serializable {
    @NotNull
    private Integer userId;
    @NotNull
    private EmployeeTypeEnum type;
    private boolean atWork;
}