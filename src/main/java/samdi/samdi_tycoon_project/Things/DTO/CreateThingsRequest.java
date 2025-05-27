package samdi.samdi_tycoon_project.Things.DTO;

public record CreateThingsRequest(
        String thingsName,
        String thingsDescription,
        int thingsPrice
) {
}
