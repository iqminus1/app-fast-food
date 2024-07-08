package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Integer> {
//    @Query("select f.region,f from Filial f order by f.region.id")
//    Map<Region, List<Filial>> findAllOrderByRegion();

    @Cacheable(key = "#id", value = "filialEntity")
    default Filial find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Filial not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "filialEntity")
    Filial save(Filial filial);

    @CacheEvict(key = "#id", value = "filialEntity")
    void deleteById(Integer id);
}