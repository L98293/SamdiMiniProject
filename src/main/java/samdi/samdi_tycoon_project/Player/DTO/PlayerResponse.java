package samdi.samdi_tycoon_project.Player.DTO;

public record PlayerResponse(
        Long id,
        String username,
        int playerMoney,
        int playerAge,
        String playerInventory
) {
}
