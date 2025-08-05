package school.hei.tsinjo.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolaPayment {
  private String id;
  private PspPayment pspPayment;
  private Instant creationInstant;
  private Instant lastPspVerificationInstant;
  private int verificationAttemptNb;
  private User payer;
  private Application application;
  private VerificationStatus verificationStatus;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PspPayment {
    private String pspType;
    private String id;
    private int amount;
    private Instant creationInstant;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class User {
    private String email;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Application {
    private String name;
    private String apiKey;
  }

  public enum VerificationStatus {
    VERIFYING,
    SUCCEEDED,
    FAILED
  }
}
