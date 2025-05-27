package samdi.samdi_tycoon_project.Things.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import samdi.samdi_tycoon_project.Player.Domain.Player;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table (name = "things")
public class Things {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String thingsName;
    private String thingsDescription;
    private int thingsPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    // LAZY를 쓰면 필요한 경우에만 값을 불러오고 EAGER을 사용하면 호출될 때 마다 값을 불러옴
    private Player owner;
}
