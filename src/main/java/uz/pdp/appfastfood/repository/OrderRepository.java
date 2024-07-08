package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Cacheable(value = "orderEntities")
    List<Order> findAll();

    @Cacheable(key = "#id", value = "orderEntity")
    default Order find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Order not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "orderEntity")
    @CacheEvict(value = "orderEntities", allEntries = true)
    Order save(Order order);

    @CacheEvict(key = "#id", value = {"orderEntity", "orderEntities"}, allEntries = true)
    void deleteById(Integer id);

    List<Order> findAllByFilialId(Integer filialId);
}