package school.hei.tsinjo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.tsinjo.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
  List<Payment> findByStatus(Payment.PaymentStatus status);
}
