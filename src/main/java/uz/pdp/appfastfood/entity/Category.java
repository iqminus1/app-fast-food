package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update category set deleted = true where id = ?"))
public class Category extends AbsIntEntity {
    private String nameRu;

    private String nameUz;

    @ManyToOne
    private Category parentCategory;
}
