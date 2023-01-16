package fi.projectbirdnest.persistence;

import fi.projectbirdnest.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {
}
