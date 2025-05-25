package samdi.samdi_tycoon_project.Player;

import org.springframework.data.repository.CrudRepository;
import samdi.samdi_tycoon_project.Player.Domain.Player;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByUsername(String username);
}
