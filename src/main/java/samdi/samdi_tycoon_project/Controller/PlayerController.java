package samdi.samdi_tycoon_project.Controller;

import org.springframework.web.bind.annotation.*;
import samdi.samdi_tycoon_project.Domain.Player;
import samdi.samdi_tycoon_project.PlayerRepository;
import samdi.samdi_tycoon_project.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerService playerService, PlayerRepository playerRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public String deafultPlayers() {
        return "플레이어 정보를 확인 하려면 /inventory/{id}로 접근하세요.";
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어를 찾을 수 없습니다."));
    }

    @PostMapping
    public String updatePlayer(@RequestBody Player player) {
        return playerRepository.save(player).toString();
    }

    @GetMapping("/inventory/{id}")
    public String getInventory(@PathVariable Long id) {
        return playerService.getInventoryByPlayerId(id)
                .orElse("해당 플레이어의 인벤토리 정보를 알 수 없습니다..");
    }

    @GetMapping("/money/{id}")
    public String getMoney(@PathVariable Long id) {
        return playerService.getMoneyByPlayerID(id)
                .orElse("해당 플레이어의 잔액정보를 알 수 없습니다.");
    }

    @GetMapping("/health/{id}")
    public String getHealth(@PathVariable Long id) {
        return playerService.getHealableByPlayerID(id)
                .orElse("해당 플레이어의 체력정보를 알 수 없습니다.");
    }
}
