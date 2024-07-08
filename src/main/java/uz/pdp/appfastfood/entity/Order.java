package uz.pdp.appfastfood.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;
import uz.pdp.appfastfood.enums.OrderStatus;
import uz.pdp.appfastfood.enums.OrderType;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update order set deleted = true where id = ?"))
public class Order extends AbsIntEntity {
    @ManyToOne
    private User user;

    @ToString.Exclude
    @OneToMany()
    private List<HistoryProduct> products;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private PrizeProduct prize;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Filial filial;

    @Enumerated(EnumType.STRING)
    private OrderType type;
}
