package uz.pdp.appfastfood.entity;

import jakarta.persistence.Entity;
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
@SQLDelete(sql = ("update attachment set deleted = true where id = ?"))
public class Attachment extends AbsIntEntity {
    private String originalName;
    private String name;
    private String path;
    private String contentType;
    private Long size;
}
