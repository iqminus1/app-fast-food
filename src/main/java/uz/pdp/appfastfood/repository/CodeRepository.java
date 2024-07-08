package uz.pdp.appfastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Code;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Integer> {
    Optional<Code> findByEmail(String email);
}