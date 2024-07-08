package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Thought;

@Repository
public interface ThoughtRepository extends JpaRepository<Thought, Integer> {
    @Cacheable(key = "#id", value = "thought")
    default Thought find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Thought not found by id -> " + id));
    }

    @CachePut(key = "#result.id", value = "thought")
    Thought save(Thought thought);

    @CacheEvict(key = "#id", value = "thought")
    void deleteById(Integer id);
}