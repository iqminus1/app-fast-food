package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Region;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    @Cacheable(value = "regionAllEntities")
    List<Region> findAll();

    @Cacheable(key = "#id", value = "regionEntity")
    default Region find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Region not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "regionEntity")
    @CacheEvict(value = "regionAllEntities", allEntries = true)
    Region save(Region region);

    @CacheEvict(key = "#id", value = {"regionEntity", "regionAllEntities"}, allEntries = true)
    void deleteById(Integer id);
}