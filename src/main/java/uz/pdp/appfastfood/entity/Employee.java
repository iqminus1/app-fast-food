package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;
import uz.pdp.appfastfood.enums.EmployeeTypeEnum;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update employee set deleted = true where id = ?"))
public class Employee extends AbsIntEntity {
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private EmployeeTypeEnum type;

    private boolean atWork;
}
