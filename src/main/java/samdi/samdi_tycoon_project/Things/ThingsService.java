package samdi.samdi_tycoon_project.Things;


import org.springframework.stereotype.Service;
import samdi.samdi_tycoon_project.Things.DTO.CreateThingsRequest;
import samdi.samdi_tycoon_project.Things.Domain.Things;
import java.util.Optional;

@Service
// 물건 만드는 코드
public class ThingsService {
    private final ThingsRepository ThingsRepository;

    public ThingsService(ThingsRepository thingsRepository) {
        this.ThingsRepository = thingsRepository;
    }

    public void createThings(CreateThingsRequest request) {
        Things b = ThingsRepository.findByThingsName(request.thingsName()).orElse(null);

        if(b != null) {
            throw new IllegalArgumentException("이미 존재하는 상품명 입니다.");
        }
        if (request.thingsPrice() < 0) {
            throw new IllegalArgumentException("상품 가격은 $0 이하일 수 없습니다.");
        }
        Things things = Things.builder()
                .thingsName(request.thingsName())
                .thingsQuantity(request.thingsQuantity())
                .thingsPrice(request.thingsPrice())
                .build();

          ThingsRepository.save(things);
    }

    // 물건 가격 조회
    public Optional<?> getThingsPriceByThingsId(Long id) {
        return ThingsRepository.findById(id)
                .map(Things::getThingsPrice);
    }
}
