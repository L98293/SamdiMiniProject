package samdi.samdi_tycoon_project.Player.DTO;

public record CreatePlayerRequest(
        String username,
        int money,
        int age,
        String gender,
        int health,
        String inventory
) {
}
