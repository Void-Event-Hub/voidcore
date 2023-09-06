package voideventhub.voidcore.common.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

public interface DeathComponent extends ComponentV3 {

    /**
     * Increments the death count of the player by 1
     */
    void incrementDeathCount();

    /**
     * Gets the death count of the player
     * @return the death count of the player
     */
    int getDeathCount();


}
