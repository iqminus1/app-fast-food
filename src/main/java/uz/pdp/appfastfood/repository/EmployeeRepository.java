package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appfastfood.entity.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Cacheable(key = "#id", value = "employeeEntityByUserId")
    default Employee findEmployeeByUserId(Integer id) {
        return findByUserId(id).orElseThrow(() -> new RuntimeException("employee not found by user id -> " + id));
    }

    Optional<Employee> findByUserId(Integer userId);
    @CachePut(key = "#result.user.id",value = "employeeEntityByUserId")
    Employee save(Employee employee);

    @CacheEvict(key = "#employee.user.id",value = "employeeEntityByUserId")
    void delete(Employee employee);
}