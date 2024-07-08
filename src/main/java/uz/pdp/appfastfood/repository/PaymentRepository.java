package uz.pdp.appfastfood.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appfastfood.entity.Payment;

import java.sql.Timestamp;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Cacheable(value = "paymentHistory")
    List<Payment> findAllByUpdateAtBetween(Timestamp start, Timestamp end);
}