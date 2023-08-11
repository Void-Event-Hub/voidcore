package voideventhub.voidcore.cardinal;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

public interface PatronComponent extends ComponentV3 {

    /**
     * @return true if the player is a patron
     */
    boolean isPatron();

}
