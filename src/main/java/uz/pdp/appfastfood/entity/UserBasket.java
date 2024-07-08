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
@SQLDelete(sql = ("update user_basket set deleted = true where id = ?"))
public class UserBasket extends AbsIntEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer quantity;

    @ManyToOne
    private Filial filial;

}
