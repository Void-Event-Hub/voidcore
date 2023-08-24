package voideventhub.voidcore.repository.models.playerAction;

import java.util.Date;
import java.util.UUID;

/**
 * Represents an action performed by a player on the server
 */
public interface PlayerAction {

        /**
        * @return the id of the player who performed the action
        */
        UUID getUuid();

        /**
        * @return the type of action performed
        */
        PlayerActionType getActionType();

        /**
        * @return the timestamp of the action
        */
        Date getTimestamp();
}
