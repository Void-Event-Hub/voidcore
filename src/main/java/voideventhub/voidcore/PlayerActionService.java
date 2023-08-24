package voideventhub.voidcore;

import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import voideventhub.voidcore.cardinal.SyncedMemberComponent;
import voideventhub.voidcore.cardinal.VCComponents;
import voideventhub.voidcore.repository.MongoDbRepository;
import voideventhub.voidcore.repository.PlayerAction;
import voideventhub.voidcore.repository.Repository;
import voideventhub.voidcore.repository.models.member.Member;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerActionService {

    private final PlayerManager playerManager;

    public PlayerActionService(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void playerJoin(UUID playerId) {
        playerAction(playerId, PlayerAction.JOIN_SERVER);
        updateMemberComponent(playerId);
    }

    public void playerLeave(UUID uuid) {
        playerAction(uuid, PlayerAction.LEAVE_SERVER);
    }

    public void playerAction(UUID playerId, PlayerAction action) {
        CompletableFuture.supplyAsync(() -> {
            Repository repository = MongoDbRepository.getInstance();
            return repository.writePlayerAction(action, playerId);
        }).thenAcceptAsync((isSuccessful) -> {
            if (!isSuccessful) {
                VoidCore.LOGGER.info("Failed to write player action to database.");
                return;
            }

            ServerPlayerEntity player = playerManager.getPlayer(playerId);

            if (player == null) {
                return;
            }

            if (action == PlayerAction.JOIN_SERVER) {
                player.sendMessage(Text.of("§7Welcome to §9Void Event Hub§7!"));
            }
        }, playerManager.getServer());
    }

    private void updateMemberComponent(UUID uuid) {
        CompletableFuture.supplyAsync(() -> {
            Repository repository = MongoDbRepository.getInstance();
            return repository.getMember(uuid);
        }).thenAcceptAsync((member) -> {
            if (member == null) {
                VoidCore.LOGGER.info("No discord member found for {}.", uuid);
                return;
            }

            ServerPlayerEntity player = playerManager.getPlayer(uuid);
            if (player == null) {
                VoidCore.LOGGER.info("Player {} not found. They might have left.", uuid);
                return;
            }

            updateMemberComponent(player, member);

        }, playerManager.getServer());
    }

    private void updateMemberComponent(ServerPlayerEntity player, Member member) {
        SyncedMemberComponent component = (SyncedMemberComponent) player.getComponent(VCComponents.MEMBER);
        component.setDiscordId(member.getDiscordId());
        component.setDiscordUsername(member.getDiscordUsername());
        component.setHasConfirmedMinecraft(member.hasConfirmedMinecraft());
        component.setRoles(member.getRoles());
    }

}
