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
@SQLDelete(sql = ("update delivery set deleted = true where id = ?"))
public class Delivery extends AbsIntEntity {
    @ManyToOne
    private Filial filial;

    private Double priceKm;

    private Double minPrice;

    private String textUz;

    private String textRu;
}
