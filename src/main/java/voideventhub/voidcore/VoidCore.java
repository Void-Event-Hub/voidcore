package voideventhub.voidcore;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import voideventhub.voidcore.events.VPSEvents;

public class VoidCore implements ModInitializer {
    public static final String MOD_ID = "void-core";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        VPSEvents.register();
    }

}