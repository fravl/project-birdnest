package fi.projectbirdnest.persistence;

import fi.projectbirdnest.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ViolationRepository extends JpaRepository<Violation, Long> {
    Optional<Violation> findByDrone_SerialNumber(String serialNumber);
    List<Violation> findByLastSeenGreaterThanOrderByIdDesc(Instant instant);
    List<Violation> findByLastSeenLessThan(Instant instant);
}
