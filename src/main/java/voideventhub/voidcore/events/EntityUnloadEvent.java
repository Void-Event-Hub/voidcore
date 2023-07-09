package voideventhub.voidcore.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import voideventhub.voidcore.JoinServerTask;
import voideventhub.voidcore.repository.PlayerAction;

public class EntityUnloadEvent {

    public static void register() {
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            if (entity instanceof ServerPlayerEntity player) {
                world.getServer().execute(new JoinServerTask(player, PlayerAction.LEAVE_SERVER));
            }
        });
    }

}
