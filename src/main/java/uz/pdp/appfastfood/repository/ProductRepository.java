package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Cacheable(key = "#id", value = "productEntity")
    default Product find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("product not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "productEntity")
    @CacheEvict(value = "productByDiscount", allEntries = true)
    Product save(Product product);

    @CacheEvict(key = "#id", value = {"productEntity", "productByDiscount"}, allEntries = true)
    void deleteById(Integer id);

    @Cacheable(value = "productByDiscount")
    List<Product> findAllByDiscountIsNotNull();
}