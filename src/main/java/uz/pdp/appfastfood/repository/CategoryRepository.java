package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Cacheable(key = "#id", value = "categoryEntity")
    default Category find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Category not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "categoryEntity")
    Category save(Category category);

    @CacheEvict(key = "#id", value = "categoryEntity")
    void deleteById(Integer id);
}