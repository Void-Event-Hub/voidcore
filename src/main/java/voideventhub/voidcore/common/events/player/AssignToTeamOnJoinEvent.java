package voideventhub.voidcore.common.events.player;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.common.components.VCComponents;

public class AssignToTeamOnJoinEvent {

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.player;
            ServerWorld world = player.getWorld();

            if (!world.getComponent(VCComponents.WORLD).eventHasStarted()) {
                return;
            }

            Team team = getTeamWithLeastOnlineMembers(world);

            if (team == null) {
                return;
            }

            world.getScoreboard().addPlayerToTeam(player.getEntityName(), team);
            Vec3d spawn = team.getComponent(VCComponents.TEAM).getSpawn();

            if (spawn == null) {
                return;
            }

            player.teleport(spawn.getX(), spawn.getY(), spawn.getZ());
        });
    }

    private static @Nullable Team getTeamWithLeastOnlineMembers(ServerWorld world) {
        var teams = world.getScoreboard().getTeams();

        if (teams.size() == 0) {
            return null;
        }

        return teams.stream()
                .min(AssignToTeamOnJoinEvent::teamSizeCompare)
                .orElse(null);
    }

    private static int teamSizeCompare(Team team1, Team team2) {
        int team1OnlineMembers = team1.getPlayerList().size();
        int team2OnlineMembers = team2.getPlayerList().size();

        if (team1OnlineMembers < team2OnlineMembers) {
            return -1;
        } else if (team1OnlineMembers > team2OnlineMembers) {
            return 1;
        }

        return 0;
    }
}
