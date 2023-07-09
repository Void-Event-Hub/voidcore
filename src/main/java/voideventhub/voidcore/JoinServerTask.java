package voideventhub.voidcore;

import net.minecraft.server.network.ServerPlayerEntity;
import voideventhub.voidcore.repository.MongoDbRepository;
import voideventhub.voidcore.repository.PlayerAction;
import voideventhub.voidcore.repository.Repository;

public class JoinServerTask extends Thread {

    private final ServerPlayerEntity entity;
    private final PlayerAction playerAction;

    public JoinServerTask(ServerPlayerEntity entity, PlayerAction playerAction) {
        this.entity = entity;
        this.playerAction = playerAction;
    }

    public void run() {
        Repository repository = MongoDbRepository.getInstance();
        repository.writePlayerAction(playerAction, entity.getUuid());
    }
}
