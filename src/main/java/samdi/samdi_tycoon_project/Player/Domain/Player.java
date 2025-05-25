package samdi.samdi_tycoon_project.Player.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data // Lombok 어노테이션, 자동으로 @Getter와, @Setter, toString 생성
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true)
    private String username;

    private int money;

    @Column(nullable = true, length = 60)
    @Min(18)
    @Max(60)// tmi: 우리나라에서 개인 사업자를 만들기 위해서는 만 19세 이상이고, 정년은 만 60세이다.
    private int age;

    private String gender; // 이 세상에 성별은 단 두개 뿐임(남성과 여성)

    @Column(length = 100)
    @Size(min = 0, max = 100) // 플레이어 체력의 최소는 0, 최대는 100
    private int health;

    private String inventory;
}
