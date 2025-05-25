package samdi.samdi_tycoon_project.Player;

import org.springframework.stereotype.Service;
import samdi.samdi_tycoon_project.Player.Domain.Player;
import java.util.Optional;

@Service
public class PlayerService {

    // 의존성 주입
    // final은 한 번 주입된 후에 변경되지 않도록 함
    private final PlayerRepository playerRepository;

    // Repository 주입
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // 플레이어 인벤토리 조회
    public Optional<String> getInventoryByPlayerId(Long id) {
        return playerRepository.findById(id)
                .map(Player::getInventory);
        // .map(Player::getInventory): 는 람다식을 더 간단하게 사용한 것으로 람다식을 사용한다면 아래와 같다
        // .map(Player -> player.getInventory);
    }

    // 플레이어 잔고 조회
    public Optional<Integer> getMoneyByPlayerID(Long id) {
        return playerRepository.findById(id)
                .map(Player::getMoney);
    }

    // 플레이어 체력 조회
    public Optional<Integer> getHealableByPlayerID(Long id) {
        return playerRepository.findById(id)
                .map(Player::getHealth);
    }
}
