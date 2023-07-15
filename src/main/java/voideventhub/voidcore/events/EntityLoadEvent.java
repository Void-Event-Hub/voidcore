package voideventhub.voidcore.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import voideventhub.voidcore.SomeService;

public class EntityLoadEvent {

    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ServerPlayerEntity player) {
                SomeService service = new SomeService(world.getServer().getPlayerManager());
                service.playerJoin(player.getUuid());
            }
        });
    }

}
