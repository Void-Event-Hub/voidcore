package voideventhub.voidcore.common.events;

import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.repository.MongoDbRepository;

public class VPSEvents {

    public static void register() {
        ServerTickEvent.register();

        // These events make queries to the database, so we only want to register them
        // if we have a valid connection to avoid crashes
        if (((MongoDbRepository)MongoDbRepository.getInstance()).validConnection()) {
            VoidCore.LOGGER.error("Could not connect to database.");
            return;
        }

        EntityUnloadEvent.register();
        EntityLoadEvent.register();
    }

}
