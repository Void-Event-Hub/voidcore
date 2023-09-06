package voideventhub.voidcore.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.argument.TeamArgumentType;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import voideventhub.voidcore.common.components.VCComponents;
import voideventhub.voidcore.common.components.team.TeamComponent;

public class SetTeamSpawnCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("team")
                .then(CommandManager.literal("setspawn").requires(source -> source.hasPermissionLevel(3))
                        .then(CommandManager.argument("name", TeamArgumentType.team())
                                .executes(context -> {
                                    Team team = TeamArgumentType.getTeam(context, "name");

                                    if (team == null) {
                                        context.getSource().sendError(Text.of("Team not found"));
                                        return 0;
                                    }

                                    ServerPlayerEntity player = context.getSource().getPlayer();

                                    if (player == null) {
                                        context.getSource().sendError(Text.of("You are not a player"));
                                        return 0;
                                    }

                                    TeamComponent teamComponent = team.getComponent(VCComponents.TEAM);
                                    teamComponent.setSpawn(player.getPos());

                                    context.getSource().sendFeedback(Text.of("Set " + team.getName() + " spawn to " + player.getPos()), true);

                                    return 0;
                                })
                        )
                )
        );
    }

}
