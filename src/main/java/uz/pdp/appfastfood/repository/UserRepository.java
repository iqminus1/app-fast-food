package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Cacheable(key = "#id", value = "userById")
    default User find(Integer id) {
        return findById(id).orElseThrow();
    }

    @Cacheable(key = "#email", value = "user")
    default User findUserByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new RuntimeException("user not found by email -> " + email));
    }

    @CachePut(key = "#result.email", value = {"user"})
    @CacheEvict(key = "#result.id", value = "userById")
    User save(User user);

    @CacheEvict(value = {"user,userById"}, allEntries = true)
    void deleteById(Integer id);

}