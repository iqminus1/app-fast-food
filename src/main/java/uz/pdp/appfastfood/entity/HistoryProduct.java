package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update history_product set deleted = true where id = ?"))
public class HistoryProduct extends AbsIntEntity {
    @ManyToOne
    private Category category;

    private String nameRu;

    private String nameUz;

    private Double price;
    private Double discount;
    @ManyToOne
    private Attachment attachment;

    public HistoryProduct(Product product) {
        this.attachment = product.getAttachment();
        this.discount = product.getDiscount();
        this.category = product.getCategory();
        this.nameUz = product.getNameUz();
        this.nameRu = product.getNameRu();
        this.price = product.getPrice();
    }
}