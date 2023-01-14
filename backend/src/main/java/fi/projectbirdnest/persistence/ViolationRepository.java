package fi.projectbirdnest.persistence;

import fi.projectbirdnest.model.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViolationRepository extends JpaRepository<Violation, Long> {
    Optional<Violation> findByDrone_SerialNumber(String serialNumber);
}
