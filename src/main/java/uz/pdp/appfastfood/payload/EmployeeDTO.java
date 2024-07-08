package uz.pdp.appfastfood.payload;

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
public class EmployeeDTO implements Serializable {
    private Integer id;
    private Integer userId;
    private EmployeeTypeEnum type;
    private boolean atWork;
}