package samdi.samdi_tycoon_project;

public record PlayerResponse(
        Long id,
        String username,
        String money,
        int age,
        String inventory
) {
}
