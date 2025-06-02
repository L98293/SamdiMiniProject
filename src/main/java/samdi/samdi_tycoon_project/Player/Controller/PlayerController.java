package samdi.samdi_tycoon_project.Player.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samdi.samdi_tycoon_project.Player.DTO.CreatePlayerRequest;
import samdi.samdi_tycoon_project.Player.Domain.Player;
import samdi.samdi_tycoon_project.Player.PlayerRepository;
import samdi.samdi_tycoon_project.Player.PlayerService;

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

    @PatchMapping("/buy/{playerId}/{thingsId}/{quantity}")
    public ResponseEntity<?> buyThings(@PathVariable Long playerId, @PathVariable Long thingsId, @PathVariable int quantity) {
        playerService.buyThings(playerId, thingsId, quantity);
        return ResponseEntity.ok("구매 완료");
    }

    // 플레이어 검색
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어를 찾을 수 없습니다."));
    }

    // 플레이어 생성
    @PostMapping
    public void createPlayer(@RequestBody CreatePlayerRequest request) {
        playerService.createPlayer(request);
    }

    // 플레이어 인벤토리 조회
    @GetMapping("/inventory/{id}")
    public String getInventory(@PathVariable Long id) {
        return playerService.getInventoryByPlayerId(id)
                .orElse("해당 플레이어의 인벤토리 정보를 알 수 없습니다..");
    }


    // money에 값이 있으면 money값을 반환하고, 없으면 404 Not Found 반환
    @GetMapping("/money/{id}")
    public ResponseEntity<?> getMoney(@PathVariable Long id) { // <?>안에는 모든 값 가능 <String>, <Integer> 등
        return playerService.getMoneyByPlayerID(id)
                .map(ResponseEntity::ok) // 만약 값이 존재하면 200 OK실행
                .orElseGet(()
                        -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 값이 없으면 404 not found
    }
    // ResponseEntity<Integer>안에 money라는 값이 존재하면 ResponseEntitiy::ok(200 OK), 없으면 HttpStatus.NOT_FOUND(404 NOT FOUND)


    @GetMapping("/health/{id}")
    public ResponseEntity<?> getHealth(@PathVariable Long id) {
        return playerService.getHealthByPlayerID(id)
                .map(ResponseEntity::ok)
                .orElseGet(()
                        -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
