package school.hei.tsinjo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@PojaGenerated
public class PojaApplication {

  public static void main(String[] args) {
    SpringApplication.run(PojaApplication.class, args);
  }
}
