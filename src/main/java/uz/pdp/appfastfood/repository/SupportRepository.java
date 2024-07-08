package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Support;
import uz.pdp.appfastfood.enums.SupportStatus;

import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<Support, Integer> {
    @Cacheable(key = "#id", value = "supportEntity")
    default Support find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("not found by id ->" + id));
    }

    @Cacheable(key = "#status", value = "supportEntitiesByStatus")
    List<Support> findAllByStatus(SupportStatus status);

    List<Support> findAllByStatusAndFromUserIdOrToUserId(SupportStatus status, Integer fromUser_id, Integer toUser_id);

    @CachePut(key = "#result.id", value = "supportEntity")
    @CacheEvict(key = "supportEntitiesByStatus", allEntries = true)
    Support save(Support support);

    @CacheEvict(key = "#id", value = {"supportEntity", "supportEntitiesByStatus"}, allEntries = true)
    void deleteById(Integer id);

    @CacheEvict(key = "#support.id", value = {"supportEntity", "supportEntitiesByStatus"}, allEntries = true)
    void delete(Support support);
}