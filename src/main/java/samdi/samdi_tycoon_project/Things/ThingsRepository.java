package samdi.samdi_tycoon_project.Things;

import org.springframework.data.jpa.repository.JpaRepository;
import samdi.samdi_tycoon_project.Things.Domain.Things;

import java.util.Optional;

public interface ThingsRepository extends JpaRepository<Things, Long> {
    Optional<Things> findByThingsName(String thingsName);
}
