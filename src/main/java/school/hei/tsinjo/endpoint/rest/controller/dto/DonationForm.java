package school.hei.tsinjo.endpoint.rest.controller.dto;

import lombok.Data;
import school.hei.tsinjo.model.Payment;

@Data
public class DonationForm {
  private String email;
  private Payment.PaymentMethod paymentMethod;
  private String pspPaymentId;
}
