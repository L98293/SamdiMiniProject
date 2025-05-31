package samdi.samdi_tycoon_project.Things.DTO;

public record CreateThingsRequest(
        String thingsName,
        int thingsQuantity,
        int thingsPrice
) {
}
