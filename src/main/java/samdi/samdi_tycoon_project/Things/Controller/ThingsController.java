package samdi.samdi_tycoon_project.Things.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samdi.samdi_tycoon_project.Things.DTO.CreateThingsRequest;
import samdi.samdi_tycoon_project.Things.Domain.Things;
import samdi.samdi_tycoon_project.Things.ThingsRepository;
import samdi.samdi_tycoon_project.Things.ThingsService;

@RestController
@RequestMapping("/things")
public class ThingsController {
    private final ThingsRepository thingsRepository;
    public final ThingsService thingsService;

    public ThingsController(ThingsRepository thingsRepository, ThingsService thingsService) {
        this.thingsRepository = thingsRepository;
        this.thingsService = thingsService;
    }

    @GetMapping
    public String deafultThings() {
        return "현재 존재하는 물건(상품명)을 조회하려면 /things/{id}경로로 접속하세요";
    }

    // 상품 생성
    @PostMapping()
    public void CreateThings(@RequestBody CreateThingsRequest request) {
        thingsService.createThings(request);
    }

    // 상품 조회
    @GetMapping("/{id}")
    public Things getThingsById(@PathVariable Long id) {
        return thingsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 물품을 조회할 수 없습니다."));
    }

    // 가격 조회
    @GetMapping("/{id}/price") // things/{id}/price
    public ResponseEntity<?> getPrice(@PathVariable Long id) {
        return thingsService.getThingsPriceByThingsId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}