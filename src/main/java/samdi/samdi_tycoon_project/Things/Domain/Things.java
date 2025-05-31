package samdi.samdi_tycoon_project.Things.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
    private int thingsQuantity;
    private int thingsPrice;
}
