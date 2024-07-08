package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    @Cacheable(key = "#id", value = "delivery")
    default Delivery find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Delivery not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "delivery")
    Delivery save(Delivery delivery);

    @CacheEvict(key = "#id", value = "delivery")
    void deleteById(Integer id);
}