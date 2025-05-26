package samdi.samdi_tycoon_project.Building.Domain;

import jakarta.persistence.*;
import lombok.Data;
import samdi.samdi_tycoon_project.Player.Domain.Player;

@Entity
@Data
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String buildingName;
    private String buildingDescription;
    private String buildingType;
    private int buildingPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    // LAZY를 쓰면 필요한 경우에만 값을 불러오고 EAGER을 사용하면 호출될 때 마다 값을 불러옴
    private Player owner;
}
