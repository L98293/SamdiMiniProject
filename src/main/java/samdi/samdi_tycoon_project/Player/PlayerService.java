package samdi.samdi_tycoon_project.Player;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import samdi.samdi_tycoon_project.Inventory.Domain.InventoryItem;
import samdi.samdi_tycoon_project.Inventory.InventoryRepository;
import samdi.samdi_tycoon_project.Player.DTO.CreatePlayerRequest;
import samdi.samdi_tycoon_project.Player.Domain.Player;
import samdi.samdi_tycoon_project.Things.Domain.Things;
import samdi.samdi_tycoon_project.Things.ThingsRepository;

import java.util.Optional;

@Service
public class PlayerService {

    // 의존성 주입
    // final은 한 번 주입된 후에 변경되지 않도록 함
    private final PlayerRepository playerRepository;
    private final ThingsRepository thingsRepository;
    private final InventoryRepository inventoryRepository;

    // Repository 주입
    public PlayerService(PlayerRepository playerRepository, ThingsRepository thingsRepository, InventoryRepository inventoryRepository) {
        this.playerRepository = playerRepository;
        this.thingsRepository = thingsRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void buyThings(Long playerId, Long thingId, int quantity) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(()
                        -> new EntityNotFoundException("해당 플레이어를 찾을 수 없습니다."));
        Things things = thingsRepository.findById(thingId)
                .orElseThrow(()
                -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));

        int totalPrice = things.getThingsPrice() * quantity;

        if (things.getThingsQuantity() <= quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        if (player.getPlayerMoney() < totalPrice) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        // 돈 및 물건 재고 차감
        player.setPlayerMoney(player.getPlayerMoney() - totalPrice);
        things.setThingsQuantity(things.getThingsQuantity() - quantity);

        // 구매 후 플레이어 인벤토리 새로고침
        Optional<InventoryItem> inventoryItemOpt = inventoryRepository.findByPlayerIdAndThings_Id(playerId, thingId);
        if (inventoryItemOpt.isPresent()) {
            InventoryItem inventoryItem = inventoryItemOpt.get();
            inventoryItem.setQuantity(inventoryItem.getQuantity() + quantity);
        }
        else {
            InventoryItem newItem = InventoryItem.builder()
                    .player(player)
                    .things(things)
                    .quantity(quantity)
                    .build();
            inventoryRepository.save(newItem);
        }

        playerRepository.save(player);
        thingsRepository.save(things);
    }

    // 여기아래는 CRUD
    public void createPlayer(CreatePlayerRequest request) {
//        Player p = playerRepository.findByUsername(request.username()).orElse(null);

        if (request.username() == null || request.username().isBlank()) {
            throw new IllegalArgumentException("사용자명을 입력해야합니다.");
        }
        if (playerRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("중복된 사용자명 입니다.");
        }
        if (request.playerAge() < 19 || request.playerAge() > 60) {
            throw new IllegalArgumentException("개인 사업자 혹은 법인을 가질 수 없는 나이 입니다.");
        }
        if (request.playerHealth() < 0 || request.playerHealth() > 100) {
            throw new IllegalArgumentException("존재할 수 없습니다.");
        }

        Player player = Player.builder()
                .username(request.username())
                .playerAge(request.playerAge())
                .playerHealth(request.playerHealth())
                .playerMoney(request.playerMoney())
                .playerGender(request.playerGender())
                .playerInventory(request.playerInventory())
                .build();
        //얘내 중 하나라도 없으면 그 값은 Null로 들어감
        playerRepository.save(player);
    }

    // 플레이어 인벤토리 조회
    public Optional<String> getInventoryByPlayerId(Long id) {
        return playerRepository.findById(id)
                .map(Player::getPlayerInventory);
        // .map(Player::getInventory): 는 람다식을 더 간단하게 사용한 것으로 람다식을 사용한다면 아래와 같다
        // .map(Player -> player.getInventory);
    }

    // 플레이어 잔고 조회
    public Optional<Integer> getMoneyByPlayerID(Long id) {
        return playerRepository.findById(id)
                .map(Player::getPlayerMoney);
    }

    // 플레이어 체력 조회
    public Optional<Integer> getHealthByPlayerID(Long id) {
        return playerRepository.findById(id)
                .map(Player::getPlayerHealth);
    }
}