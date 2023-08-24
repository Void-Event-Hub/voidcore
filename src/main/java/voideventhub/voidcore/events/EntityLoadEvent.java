package voideventhub.voidcore.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import voideventhub.voidcore.PlayerActionService;

public class EntityLoadEvent {

    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ServerPlayerEntity player) {
                PlayerActionService service = new PlayerActionService(world.getServer().getPlayerManager());
                service.playerJoin(player.getUuid());
            }
        });
    }

}
