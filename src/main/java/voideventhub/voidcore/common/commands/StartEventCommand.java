package voideventhub.voidcore.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.common.components.VCComponents;

public class StartEventCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("startevent")
                .requires(source -> source.hasPermissionLevel(3))
                .executes(context -> {
                    ServerWorld world = context.getSource().getWorld();
                    world.getScoreboard().getTeams().forEach(team -> {
                        Vec3d spawn = team.getComponent(VCComponents.TEAM).getSpawn();
                        if (spawn == null) {
                            context.getSource().sendError(Text.of("Team " + team.getName() + " has no spawn"));
                        }
                    });

                    var players = world.getPlayers();
                    players.forEach(StartEventCommand::tpPlayerToTeamSpawn);
                    players.forEach(player -> player.sendMessage(Text.of(VoidCore.TEXT_COLOR + "Started event!"), false));

                    context.getSource().sendFeedback(Text.of("Started event"), true);
                    return 0;
                }));
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
