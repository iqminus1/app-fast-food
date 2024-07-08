package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;
import uz.pdp.appfastfood.enums.ThoughtType;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update thought set deleted = true where id = ?"))
public class Thought extends AbsIntEntity {
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private ThoughtType type;

    private String message;
}
