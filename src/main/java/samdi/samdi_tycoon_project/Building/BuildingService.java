package samdi.samdi_tycoon_project.Building;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import samdi.samdi_tycoon_project.Building.DTO.CreateBuildingRequest;
import samdi.samdi_tycoon_project.Building.Domain.Building;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public void createBuilding(CreateBuildingRequest request) {
        Building b = buildingRepository.findByBuildingName(request.buildingName()).orElse(null);

        if(request.buildingName() != null) {
            throw new IllegalArgumentException("이미 존재하는 법인 입니다.");
        }
        if (request.buildingPrice() < 0) {
            throw new IllegalArgumentException("건물 가격은 $0 이하일 수 없습니다.");
        }
        Building building = Building.builder()
                .buildingName(request.buildingName())
                .buildingDescription(request.buildingDescription())
                .buildingPrice(request.buildingPrice())
                .buildingType(request.buildingType())
                .build();

        buildingRepository.save(building);
    }
}
