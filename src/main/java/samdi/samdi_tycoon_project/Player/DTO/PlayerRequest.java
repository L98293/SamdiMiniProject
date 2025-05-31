package samdi.samdi_tycoon_project.Player.DTO;

public record PlayerRequest(
        String username,
        String playerGender,
        int playerMoney,
        int playerAge,
        int playerHealth

) {
}
