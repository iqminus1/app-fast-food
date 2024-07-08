package uz.pdp.appfastfood.entity.templates;

import io.jsonwebtoken.io.Deserializer;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;

@Getter
@MappedSuperclass
public abstract class AbsAuditEntity implements Serializable{
    @CreatedBy
    public Integer createById;

    @LastModifiedBy
    public Integer updateById;
}