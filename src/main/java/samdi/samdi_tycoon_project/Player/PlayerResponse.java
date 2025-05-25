package samdi.samdi_tycoon_project.Player;

public record PlayerResponse(
        Long id,
        String username,
        int money,
        int age,
        String inventory
) {
}
