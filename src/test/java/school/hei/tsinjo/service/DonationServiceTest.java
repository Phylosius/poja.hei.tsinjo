package school.hei.tsinjo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.hei.tsinjo.endpoint.rest.controller.dto.DonationForm;
import school.hei.tsinjo.model.Donation;
import school.hei.tsinjo.model.Payment;
import school.hei.tsinjo.repository.DonationRepository;

class DonationServiceTest {

  @Mock private DonationRepository donationRepository;

  @Mock private VolaAsyncService volaAsyncService;

  @InjectMocks private DonationService donationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createDonation() {
    // given
    DonationForm form = new DonationForm();
    form.setFullName("Test Donor");
    form.setEmail("test.donor@example.com");
    form.setAmount(10000);
    form.setPspPaymentId("psp-123");

    // when
    donationService.createDonation(form);

    // then
    ArgumentCaptor<Donation> donationCaptor = ArgumentCaptor.forClass(Donation.class);
    verify(donationRepository).save(donationCaptor.capture());
    Donation savedDonation = donationCaptor.getValue();
    assertEquals("Test Donor", savedDonation.getDonor().getFullName());
    assertEquals(10000, savedDonation.getPayment().getAmount());
    assertEquals(Payment.PaymentStatus.VERIFYING, savedDonation.getPayment().getStatus());

    verify(volaAsyncService)
        .createPayment("test.donor@example.com", "psp-123", Payment.PaymentMethod.ORANGE_MONEY);
  }
}
