package school.hei.tsinjo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.hei.tsinjo.client.VolaClient;
import school.hei.tsinjo.client.dto.VolaPayment;
import school.hei.tsinjo.model.Donation;
import school.hei.tsinjo.model.Donor;
import school.hei.tsinjo.model.Payment;
import school.hei.tsinjo.repository.DonationRepository;
import school.hei.tsinjo.repository.PaymentRepository;

class PaymentServiceTest {

  @Mock private PaymentRepository paymentRepository;

  @Mock private DonationRepository donationRepository;

  @Mock private VolaClient volaClient;

  @InjectMocks private PaymentService paymentService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void updatePendingPayments_succeeded() {
    // given
    Payment verifyingPayment =
        Payment.builder()
            .id("p1")
            .status(Payment.PaymentStatus.VERIFYING)
            .pspPaymentId("psp-1")
            .method(Payment.PaymentMethod.ORANGE_MONEY)
            .build();
    Donor donor = Donor.builder().email("donor@example.com").build();
    Donation donation = Donation.builder().payment(verifyingPayment).donor(donor).build();
    when(paymentRepository.findByStatus(Payment.PaymentStatus.VERIFYING))
        .thenReturn(List.of(verifyingPayment));
    when(donationRepository.findByPaymentId("p1")).thenReturn(Optional.of(donation));
    VolaPayment volaResponse = new VolaPayment();
    volaResponse.setVerificationStatus(VolaPayment.VerificationStatus.SUCCEEDED);
    when(volaClient.getPayment("donor@example.com", "psp-1", Payment.PaymentMethod.ORANGE_MONEY))
        .thenReturn(volaResponse);

    // when
    paymentService.updatePendingPayments();

    // then
    verify(paymentRepository).save(argThat(p -> p.getStatus() == Payment.PaymentStatus.SUCCEEDED));
  }

  @Test
  void updatePendingPayments_failed() {
    // given
    Payment verifyingPayment =
        Payment.builder()
            .id("p1")
            .status(Payment.PaymentStatus.VERIFYING)
            .pspPaymentId("psp-1")
            .method(Payment.PaymentMethod.ORANGE_MONEY)
            .build();
    Donor donor = Donor.builder().email("donor@example.com").build();
    Donation donation = Donation.builder().payment(verifyingPayment).donor(donor).build();
    when(paymentRepository.findByStatus(Payment.PaymentStatus.VERIFYING))
        .thenReturn(List.of(verifyingPayment));
    when(donationRepository.findByPaymentId("p1")).thenReturn(Optional.of(donation));
    VolaPayment volaResponse = new VolaPayment();
    volaResponse.setVerificationStatus(VolaPayment.VerificationStatus.FAILED);
    when(volaClient.getPayment("donor@example.com", "psp-1", Payment.PaymentMethod.ORANGE_MONEY))
        .thenReturn(volaResponse);

    // when
    paymentService.updatePendingPayments();

    // then
    verify(paymentRepository).save(argThat(p -> p.getStatus() == Payment.PaymentStatus.FAILED));
  }

  @Test
  void updatePendingPayments_still_verifying() {
    // given
    Payment verifyingPayment =
        Payment.builder()
            .id("p1")
            .status(Payment.PaymentStatus.VERIFYING)
            .pspPaymentId("psp-1")
            .method(Payment.PaymentMethod.ORANGE_MONEY)
            .build();
    Donor donor = Donor.builder().email("donor@example.com").build();
    Donation donation = Donation.builder().payment(verifyingPayment).donor(donor).build();
    when(paymentRepository.findByStatus(Payment.PaymentStatus.VERIFYING))
        .thenReturn(List.of(verifyingPayment));
    when(donationRepository.findByPaymentId("p1")).thenReturn(Optional.of(donation));
    VolaPayment volaResponse = new VolaPayment();
    volaResponse.setVerificationStatus(VolaPayment.VerificationStatus.VERIFYING);
    when(volaClient.getPayment("donor@example.com", "psp-1", Payment.PaymentMethod.ORANGE_MONEY))
        .thenReturn(volaResponse);

    // when
    paymentService.updatePendingPayments();

    // then
    verify(paymentRepository, never()).save(any());
  }

  @Test
  void updatePendingPayments_no_pending() {
    // given
    when(paymentRepository.findByStatus(Payment.PaymentStatus.VERIFYING))
        .thenReturn(Collections.emptyList());

    // when
    paymentService.updatePendingPayments();

    // then
    verify(donationRepository, never()).findByPaymentId(any());
    verify(volaClient, never()).getPayment(any(), any(), any());
    verify(paymentRepository, never()).save(any());
  }
}
