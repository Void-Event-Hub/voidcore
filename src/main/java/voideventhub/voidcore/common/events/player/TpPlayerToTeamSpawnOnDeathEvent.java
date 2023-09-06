package voideventhub.voidcore.common.events.player;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import voideventhub.voidcore.common.components.VCComponents;

public class TpPlayerToTeamSpawnOnDeathEvent {

    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof ServerPlayerEntity player) {
                if (!player.getWorld().getComponent(VCComponents.WORLD).eventHasStarted()) {
                    return;
                }

                tpPlayerToTeamSpawn(player);
            }
        });
    }

    private static void tpPlayerToTeamSpawn(ServerPlayerEntity player) {
        if (player.getScoreboardTeam() == null) {
            return;
        }

        Team team = (Team) player.getScoreboardTeam();
        Vec3d teamSpawn = team.getComponent(VCComponents.TEAM).getSpawn();

        if (teamSpawn == null) {
            return;
        }

        player.teleport(teamSpawn.getX(), teamSpawn.getY(), teamSpawn.getZ());
    }

}
