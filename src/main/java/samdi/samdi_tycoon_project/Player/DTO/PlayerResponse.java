package samdi.samdi_tycoon_project.Player.DTO;

public record PlayerResponse(
        Long id,
        String username,
        int money,
        int age,
        String inventory
) {
}
