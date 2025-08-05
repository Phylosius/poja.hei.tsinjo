package school.hei.tsinjo.endpoint.rest.controller.dto;

import lombok.Data;

@Data
public class DonationForm {
  private String fullName;
  private String email;
  private int amount;
  private String pspPaymentId;
}
