package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.UserBasket;

import java.util.List;

@Repository
public interface UserBasketRepository extends JpaRepository<UserBasket, Integer> {
    @Cacheable(key = "#id", value = "userBasket")
    default UserBasket find(Integer id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Basket not found by id -> " + id));
    }

    @Cacheable(key = "#userId", value = "userBasketAll")
    List<UserBasket> findAllByUserId(Integer userId);
}