package voideventhub.voidcore.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import voideventhub.voidcore.PlayerActionService;
import voideventhub.voidcore.VoidCore;

public class EntityUnloadEvent {

    public static void register() {
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            if (world.getServer().isSingleplayer()  ||
                    VoidCore.CONFIG.mongodbUsername() == null ||
                    VoidCore.CONFIG.mongodbPassword() == null
            ) {
                return;
            }

            if (entity instanceof ServerPlayerEntity player) {
                PlayerActionService service = new PlayerActionService(world.getServer().getPlayerManager());
                service.playerLeave(player.getUuid());
            }
        });
    }

}
