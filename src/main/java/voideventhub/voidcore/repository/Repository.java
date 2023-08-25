package voideventhub.voidcore.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.repository.models.event.Event;
import voideventhub.voidcore.repository.models.member.Member;
import voideventhub.voidcore.repository.models.playerAction.PlayerActionType;

import java.util.UUID;

/**
 * Repository interface for accessing the database, which holds data regarding things like discord data.
 */
public interface Repository {

    /**
     * Save a player action to the database
     * @param playerActionType The player action to save
     * @param uuid The UUID of the player entity
     * @return Whether the action was saved successfully
     */
    boolean writePlayerAction(@NotNull PlayerActionType playerActionType, @NotNull UUID uuid);

    /**
     * @return Discord member tied to the given UUID
     */
    Member getMember(@NotNull UUID uuid);

    /**
     * @return the current event being held
     */
    @Nullable
    Event getCurrentEvent();

    /**
     * @param uuid id of the player
     * @param eventId id of the event
     * @return whether the player was accepted to the event
     */
    boolean wasAccepted(@NotNull UUID uuid, @NotNull String eventId);

}
