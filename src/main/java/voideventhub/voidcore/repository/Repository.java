package voideventhub.voidcore.repository;

import voideventhub.voidcore.repository.models.EventApplication;

import java.util.List;
import java.util.UUID;

public interface Repository {

    /**
     * Save a player action to the database
     * @param playerAction The player action to save
     * @param uuid The UUID of the player entity
     * @return Whether the action was saved successfully
     */
    boolean writePlayerAction(PlayerAction playerAction, UUID uuid);

    /**
     * Get a list of players with the patron role
     * @return A list of all patrons
     */
    List<UUID> getPatrons();

    List<UUID> getAcceptedApplicants();

    List<EventApplication> getApplications();



}
