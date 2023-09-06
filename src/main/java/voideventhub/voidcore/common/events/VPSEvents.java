package voideventhub.voidcore.common.events;

import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.repository.MongoDbRepository;

public class VPSEvents {

    public static void register() {
        ServerTickEvent.register();
        PlayerDisconnectEvent.register();
        PlayerJoinEvent.register();
        AfterDeathEvent.register();

        if (((MongoDbRepository)MongoDbRepository.getInstance()).validConnection()) {
            VoidCore.LOGGER.error("Could not connect to database.");
        }
    }

}
