package school.hei.tsinjo.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import school.hei.tsinjo.client.VolaClient;
import school.hei.tsinjo.model.Payment;

@Service
@AllArgsConstructor
public class VolaAsyncService {

  private final VolaClient volaClient;

  @Async
  public void createPayment(String payerEmail, String pspPaymentId, Payment.PaymentMethod pspType) {
    volaClient.createPayment(payerEmail, pspPaymentId, pspType);
  }
}
