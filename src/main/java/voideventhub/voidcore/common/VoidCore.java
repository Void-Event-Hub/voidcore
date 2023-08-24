package voideventhub.voidcore.common;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import voideventhub.voidcore.common.commands.VCCommands;
import voideventhub.voidcore.common.config.Config;
import voideventhub.voidcore.common.events.VPSEvents;
import voideventhub.voidcore.common.item.VCItems;
import voideventhub.voidcore.common.networking.VCNetwork;

public class VoidCore implements ModInitializer {
    public static final String MOD_ID = "void-core";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Config CONFIG = Config.createAndLoad();

    @Override
    public void onInitialize() {
        VPSEvents.register();
        VCItems.register();
        VCCommands.register();
        VCNetwork.registerS2CPackets();
        VCNetwork.registerC2SPackets();
    }

}