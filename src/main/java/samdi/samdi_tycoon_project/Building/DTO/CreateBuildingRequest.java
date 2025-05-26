package samdi.samdi_tycoon_project.Building.DTO;

public record CreateBuildingRequest(
        String buildingName,
        String buildingDescription,
        String buildingType,
        int buildingPrice
) {
}
