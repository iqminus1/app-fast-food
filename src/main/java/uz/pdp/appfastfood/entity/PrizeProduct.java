package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update prize_product set deleted = true where id = ?"))
public class PrizeProduct extends AbsIntEntity {@ManyToOne
    private Product product;
    private Integer quantity;
}
