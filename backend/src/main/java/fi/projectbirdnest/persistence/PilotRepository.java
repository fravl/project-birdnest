package fi.projectbirdnest.persistence;

import fi.projectbirdnest.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, String> {
}
