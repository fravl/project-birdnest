package fi.projectbirdnest.persistence;

import fi.projectbirdnest.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationRepository extends JpaRepository<Violation, Long> {
}
