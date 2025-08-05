package school.hei.tsinjo.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

public class EnvConf {

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> "jdbc:h2:mem:tsinjo;DB_CLOSE_DELAY=-1");
    registry.add("spring.datasource.username", () -> "sa");
    registry.add("spring.datasource.password", () -> "sa");
    registry.add("spring.datasource.driver-class-name", () -> "org.h2.Driver");
    registry.add(
        "spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.H2Dialect");

    // Flyway
    registry.add("spring.flyway.locations", () -> "classpath:/db/migration");

    // Mock Vola API key for tests
    registry.add("vola.api.key", () -> "MOCK_API_KEY");
  }
}
