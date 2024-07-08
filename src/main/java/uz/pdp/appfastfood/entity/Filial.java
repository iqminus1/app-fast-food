package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appfastfood.entity.templates.AbsIntEntity;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update filial set deleted = true where id = ?"))
public class Filial extends AbsIntEntity {
    @ManyToOne
    private Region region;
    private String nameUz;
    private String descriptionUz;

    private String nameRu;
    private String descriptionRU;

    private double latitude;
    private double longitude;


    private LocalTime openAt;
    private LocalTime closeAt;

    private boolean open;
}
