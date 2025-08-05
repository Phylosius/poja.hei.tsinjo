package school.hei.tsinjo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.tsinjo.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, String> {
  Optional<Donation> findByPaymentId(String paymentId);
}
