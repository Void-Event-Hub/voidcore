package voideventhub.voidcore.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import voideventhub.voidcore.GetPatronsTask;

public class ServerLoadEvent {

    public static void register() {
        ServerWorldEvents.LOAD.register((server, world) -> {
            world.getServer().execute(new GetPatronsTask());
        });
    }

}
