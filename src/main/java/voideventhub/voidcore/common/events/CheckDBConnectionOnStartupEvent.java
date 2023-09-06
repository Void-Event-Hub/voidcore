package voideventhub.voidcore.common.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.repository.MongoDbRepository;

public class CheckDBConnectionOnStartupEvent {

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            boolean connectedToDatabase = ((MongoDbRepository) MongoDbRepository.getInstance()).validConnection();

            if (server.isSingleplayer()) {
                return;
            }

            if (connectedToDatabase) {
                VoidCore.LOGGER.info("Successfully connected to database.");
                return;
            }
            VoidCore.LOGGER.error("Could not connect to database. Check the config if the credentials are correct.");
            throw new IllegalArgumentException("Could not connect to database");
        });
    }

}
