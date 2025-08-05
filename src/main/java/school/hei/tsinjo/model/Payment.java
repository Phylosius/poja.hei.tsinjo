package school.hei.tsinjo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
  @Id private String id;

  private String pspPaymentId; // ID from Orange Money, etc.
  private int amount;

  @CreationTimestamp private Instant creationTimestamp;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  @Enumerated(EnumType.STRING)
  private PaymentMethod method;

  public enum PaymentStatus {
    VERIFYING,
    SUCCEEDED,
    FAILED
  }

  public enum PaymentMethod {
    ORANGE_MONEY
  }
}
