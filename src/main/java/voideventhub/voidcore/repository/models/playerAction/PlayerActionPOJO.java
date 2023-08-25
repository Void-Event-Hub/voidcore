package voideventhub.voidcore.repository.models.playerAction;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.UUID;

public class PlayerActionPOJO implements PlayerAction {

    @BsonProperty("player_id")
    private String playerId;

    @BsonProperty("action")
    private String action;

    @BsonProperty("timestamp")
    private Date timestamp;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public UUID getUuid() {
        return UUID.fromString(this.playerId);
    }

    @Override
    public PlayerActionType getActionType() {
        return PlayerActionType.valueOf(this.action);
    }

    @Override
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
