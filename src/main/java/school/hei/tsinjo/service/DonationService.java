package school.hei.tsinjo.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.tsinjo.endpoint.rest.controller.dto.DonationForm;
import school.hei.tsinjo.model.Donation;
import school.hei.tsinjo.model.Donor;
import school.hei.tsinjo.model.Payment;
import school.hei.tsinjo.repository.DonationRepository;

@Service
@AllArgsConstructor
public class DonationService {

  private final DonationRepository donationRepository;
  private final VolaAsyncService volaAsyncService;

  public void createDonation(DonationForm form) {
    Donor donor =
        Donor.builder()
            .id(UUID.randomUUID().toString())
            .fullName(form.getFullName())
            .email(form.getEmail())
            .build();

    Payment payment =
        Payment.builder()
            .id(UUID.randomUUID().toString())
            .pspPaymentId(form.getPspPaymentId())
            .amount(form.getAmount())
            .status(Payment.PaymentStatus.VERIFYING)
            .method(Payment.PaymentMethod.ORANGE_MONEY)
            .build();

    Donation donation =
        Donation.builder().id(UUID.randomUUID().toString()).donor(donor).payment(payment).build();

    donationRepository.save(donation);

    volaAsyncService.createPayment(donor.getEmail(), payment.getPspPaymentId(), payment.getMethod());
  }
}
