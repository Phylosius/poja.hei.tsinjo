package school.hei.tsinjo.endpoint.rest.controller.dto;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDto {
  private String type; // "Donation" or "Help"
  private String personName;
  private String personEmail;
  private int amount;
  private Instant date;
  private String description; // Only for Help
  private String status; // Only for Donation
}
