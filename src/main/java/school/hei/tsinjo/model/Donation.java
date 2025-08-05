package school.hei.tsinjo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Donation implements Serializable {
  @Id private String id;

  @ManyToOne(cascade = CascadeType.ALL)
  private Donor donor;

  @OneToOne(cascade = CascadeType.ALL)
  private Payment payment;

  @CreationTimestamp private Instant creationTimestamp;
}
