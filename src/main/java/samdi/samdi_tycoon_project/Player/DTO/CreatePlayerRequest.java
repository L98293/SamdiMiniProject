package samdi.samdi_tycoon_project.Player.DTO;

public record CreatePlayerRequest(
        String username,
        int playerMoney,
        int playerAge,
        String playerGender,
        int playerHealth,
        String playerInventory
) {
}
