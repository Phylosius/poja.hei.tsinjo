package school.hei.tsinjo.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import school.hei.tsinjo.client.VolaClient;
import school.hei.tsinjo.client.dto.VolaPayment;
import school.hei.tsinjo.model.Payment;
import school.hei.tsinjo.repository.DonationRepository;
import school.hei.tsinjo.repository.PaymentRepository;

@Service
@AllArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final DonationRepository donationRepository;
  private final VolaClient volaClient;

  @Scheduled(fixedRate = 60000) // every minute
  public void updatePendingPayments() {
    List<Payment> pendingPayments = paymentRepository.findByStatus(Payment.PaymentStatus.VERIFYING);
    for (Payment payment : pendingPayments) {
      donationRepository
          .findByPaymentId(payment.getId())
          .ifPresent(
              donation -> {
                VolaPayment volaPayment =
                    volaClient.getPayment(
                        donation.getDonor().getEmail(),
                        payment.getPspPaymentId(),
                        payment.getMethod());

                if (volaPayment != null
                    && volaPayment.getVerificationStatus()
                        != VolaPayment.VerificationStatus.VERIFYING) {
                  payment.setStatus(mapVolaStatus(volaPayment.getVerificationStatus()));
                  paymentRepository.save(payment);
                }
              });
    }
  }

  private Payment.PaymentStatus mapVolaStatus(VolaPayment.VerificationStatus volaStatus) {
    return switch (volaStatus) {
      case SUCCEEDED -> Payment.PaymentStatus.SUCCEEDED;
      case FAILED -> Payment.PaymentStatus.FAILED;
      default -> Payment.PaymentStatus.VERIFYING;
    };
  }
}
