package samdi.samdi_tycoon_project.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import samdi.samdi_tycoon_project.Building.Domain.Building;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findByBuildingName(String buildingName);
}
