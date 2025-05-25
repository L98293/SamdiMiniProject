package samdi.samdi_tycoon_project;

import org.springframework.data.repository.CrudRepository;
import samdi.samdi_tycoon_project.Domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
