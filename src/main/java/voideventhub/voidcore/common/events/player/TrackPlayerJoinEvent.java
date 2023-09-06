package voideventhub.voidcore.common.events.player;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import voideventhub.voidcore.common.PlayerActionService;
import voideventhub.voidcore.common.VoidCore;

public class TrackPlayerJoinEvent {

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (server.isSingleplayer() ||
                    VoidCore.CONFIG.mongodbUsername() == null ||
                    VoidCore.CONFIG.mongodbPassword() == null
            ) {
                return;
            }
            ServerPlayerEntity player = handler.player;
            PlayerActionService service = new PlayerActionService(server.getPlayerManager());
            service.playerJoin(player.getUuid());
        });
    }

}
