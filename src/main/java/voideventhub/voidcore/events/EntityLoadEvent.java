package voideventhub.voidcore.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import voideventhub.voidcore.JoinServerTask;
import voideventhub.voidcore.Patrons;

public class EntityLoadEvent {

    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ServerPlayerEntity player) {
                world.getServer().execute(new JoinServerTask(player, "join"));
                if (Patrons.isPatron(player.getUuid())) {
                    player.sendMessage(Text.of("Thank you for contributing to our server! Patron boosts have been applied!"));
                }
            }
        });
    }

}
