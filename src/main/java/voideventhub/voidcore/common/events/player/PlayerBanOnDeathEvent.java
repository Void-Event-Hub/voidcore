package voideventhub.voidcore.common.events.player;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.BannedPlayerEntry;
import net.minecraft.server.BannedPlayerList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.common.components.entity.DeathComponent;
import voideventhub.voidcore.common.components.VCComponents;

import java.util.Date;

public class PlayerBanOnDeathEvent {

    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof ServerPlayerEntity player) {
                DeathComponent deathComponent = player.getComponent(VCComponents.DEATH);
                deathComponent.incrementDeathCount();

                if (!VoidCore.CONFIG.enableDeathBan()) {
                    return;
                }

                if (deathComponent.getDeathCount() > VoidCore.CONFIG.totalAllowedDeaths()) {
                    banPlayer(player);
                    player.networkHandler.disconnect(Text.of("You have no lives left."));
                }
            }
        });
    }

    private static void banPlayer(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();

        if (server == null) {
            return;
        }

        PlayerManager playerManager = server.getPlayerManager();
        BannedPlayerList bannedPlayerList = playerManager.getUserBanList();

        BannedPlayerEntry banEntry = new BannedPlayerEntry(
                player.getGameProfile(),
                new Date(),
                "The gods",
                null,
                "You died and have no lives left."
        );
        bannedPlayerList.add(banEntry);
    }

}
