package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;
import uz.pdp.appfastfood.enums.SupportStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update support set deleted = true where id = ?"))
public class Support extends AbsIntEntity {
    @ManyToOne
    private User fromUser;

    private String text;

    @ManyToOne
    private User toUser;

    private SupportStatus status;
}
