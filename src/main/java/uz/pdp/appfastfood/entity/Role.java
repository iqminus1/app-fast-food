package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;
import uz.pdp.appfastfood.enums.PermissionEnum;
import uz.pdp.appfastfood.enums.RoleTypeEnum;

import java.sql.Types;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update role set deleted = true where id = ?"))
public class Role extends AbsIntEntity {
    private String name;

    @JdbcTypeCode(Types.ARRAY)
    private List<PermissionEnum> permissions;

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type;
}
