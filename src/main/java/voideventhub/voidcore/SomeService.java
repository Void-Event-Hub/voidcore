package voideventhub.voidcore;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import voideventhub.voidcore.repository.MongoDbRepository;
import voideventhub.voidcore.repository.PlayerAction;
import voideventhub.voidcore.repository.Repository;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SomeService {

    private final PlayerManager playerManager;

    public SomeService(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void playerJoin(UUID playerId) {
        playerAction(playerId, PlayerAction.JOIN_SERVER);
    }

    public void playerAction(UUID playerId, PlayerAction action) {
        CompletableFuture.supplyAsync(() -> {
            Repository repository = MongoDbRepository.getInstance();
            repository.writePlayerAction(action, playerId);

            return null;
        }).thenAcceptAsync((kick) -> {
            ServerPlayerEntity player = playerManager.getPlayer(playerId);

            if (player == null) {
                return;
            }

            if (action == PlayerAction.JOIN_SERVER) {
                player.sendMessage(Text.of("§7Welcome to §9Void Event Hub§7!"));
            }
        }, playerManager.getServer());
    }
}
