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
@SQLDelete(sql = ("update product set deleted = true where id = ?"))
public class Product extends AbsIntEntity {
    @ManyToOne
    private Category category;

    private String nameRu;

    private String nameUz;

    private Double price;
    private Double discount;
    @ManyToOne
    private Attachment attachment;

}
