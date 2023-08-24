package voideventhub.voidcore.events;

import voideventhub.voidcore.repository.MongoDbRepository;

public class VPSEvents {

    public static void register() {
        // These events make queries to the database, so we only want to register them
        // if we have a valid connection to avoid crashes
        if (((MongoDbRepository)MongoDbRepository.getInstance()).validConnection()) {
            EntityUnloadEvent.register();
            EntityLoadEvent.register();
        }
        ServerTickEvent.register();
    }

}
