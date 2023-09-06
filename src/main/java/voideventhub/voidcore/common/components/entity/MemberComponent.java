package voideventhub.voidcore.common.components.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.repository.models.member.Role;

import java.util.List;

/**
 * Contains discord information about a player
 */
public interface MemberComponent extends ComponentV3 {

    /**
     * @return the Discord ID of this member
     */
    long getDiscordId();

    /**
     * @return the Discord username of this member
     */
    @Nullable
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

    /**
     *
     * @return whether this member is patron or a server booster
     */
    boolean isPremiumMember();

}
