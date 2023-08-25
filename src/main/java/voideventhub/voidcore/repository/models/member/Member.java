package voideventhub.voidcore.repository.models.member;

import java.util.List;
import java.util.UUID;

/**
 * Represents a member of the Discord server
 */
public interface Member {

    /**
     * @return the UUID of the Minecraft account linked to this member
     */
    UUID getMinecraftUuid();

    /**
     * @return the Discord ID of this member
     */
    long getDiscordId();

    /**
     * @return the Discord username of this member
     */
    String getDiscordUsername();

    /**
     * @return whether this member has confirmed their Minecraft account
     */
    boolean hasConfirmedMinecraft();

    /**
     * @return the roles this member has on the Discord server
     */
    List<Role> getRoles();

    /**
     *
     * @return whether this member is a patron
     */
    boolean isPatron();

    /**
     *
     * @return whether this member is a server booster
     */
    boolean isServerBooster();



}
