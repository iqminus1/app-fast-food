package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Code extends AbsIntEntity {
    private String code;
    private String email;
    private Integer attempt;
}
