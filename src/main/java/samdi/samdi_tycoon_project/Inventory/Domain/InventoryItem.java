package samdi.samdi_tycoon_project.Inventory.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import samdi.samdi_tycoon_project.Player.Domain.Player;
import samdi.samdi_tycoon_project.Things.Domain.Things;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "things_id")
    private Things things;

    private int quantity;
}
