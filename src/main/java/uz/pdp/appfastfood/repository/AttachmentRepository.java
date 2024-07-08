package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    @Cacheable(key = "#id", value = "attachmentEntity")
    default Attachment find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Attachment not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "attachmentEntity")
    Attachment save(Attachment attachment);

    @CacheEvict(key = "#id", value = "attachmentEntity")
    void deleteById(Integer id);
}