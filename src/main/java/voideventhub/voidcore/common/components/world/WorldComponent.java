package voideventhub.voidcore.common.components.world;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;

public interface WorldComponent extends ComponentV3 {

    /**
     * @return whether the event has started
     */
    boolean eventHasStarted();

    /**
     * starts the event
     */
    void startEvent();

}
