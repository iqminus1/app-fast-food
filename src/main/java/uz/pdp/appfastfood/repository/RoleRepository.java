package uz.pdp.appfastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Role;
import uz.pdp.appfastfood.enums.RoleTypeEnum;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByType(RoleTypeEnum roleTypeEnum);
}