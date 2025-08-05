package school.hei.tsinjo.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.tsinjo.model.Help;
import school.hei.tsinjo.repository.HelpRepository;

@Service
@AllArgsConstructor
public class HelpService {

  private final HelpRepository helpRepository;

  public List<Help> findAll() {
    return helpRepository.findAll();
  }
}
