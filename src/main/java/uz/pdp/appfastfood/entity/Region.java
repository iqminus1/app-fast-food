package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update region set deleted = true where id = ?"))
public class Region extends AbsIntEntity {
    private String nameRu;
    private String nameUz;
}
