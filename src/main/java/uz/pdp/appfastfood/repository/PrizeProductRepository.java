package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.PrizeProduct;

import java.util.List;

@Repository
public interface PrizeProductRepository extends JpaRepository<PrizeProduct, Integer> {
    @Cacheable(value = "prizeProductAll")
    List<PrizeProduct> findAll();

    @Cacheable(key = "#id", value = "prizeProductEntity")
    default PrizeProduct find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Prize not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "prizeProductEntity")
    @CacheEvict(value = "prizeProductAll", allEntries = true)
    PrizeProduct save(PrizeProduct prizeProduct);

    @CacheEvict(key = "#id", value = {"prizeProductEntity", "prizeProductAll"}, allEntries = true)
    void deleteById(Integer id);
}