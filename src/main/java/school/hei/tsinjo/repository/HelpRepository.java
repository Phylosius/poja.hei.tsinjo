package school.hei.tsinjo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.tsinjo.model.Help;

@Repository
public interface HelpRepository extends JpaRepository<Help, String> {}
