package samdi.samdi_tycoon_project.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import samdi.samdi_tycoon_project.Inventory.Domain.InventoryItem;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByPlayerIdAndThings_Id(Long playerId, long thingsId);
}
