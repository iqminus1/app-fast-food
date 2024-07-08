package uz.pdp.appfastfood.entity.templates;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsDateEntity extends AbsAuditEntity {
  @CreatedDate
  private Timestamp createAt;

  @LastModifiedDate
  private Timestamp updateAt;
}