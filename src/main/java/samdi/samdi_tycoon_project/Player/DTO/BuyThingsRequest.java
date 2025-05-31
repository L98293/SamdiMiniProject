package samdi.samdi_tycoon_project.Player.DTO;

import lombok.Getter;

public class BuyThingsRequest {
    private Long playerId;
//    private Long thingsId;
    private int quantity;

    public BuyThingsRequest(Long playerId, Long thingsId, int quantity) {
        this.playerId = playerId;
//        this.thingsId = thingsId;
        this.quantity = quantity;
    }

    public Long getPlayerId() {
        return playerId;
    }

//    public Long getThingsId() {
//        return thingsId;
//    }

    public int getQuantity() {
        return quantity;
    }
}
