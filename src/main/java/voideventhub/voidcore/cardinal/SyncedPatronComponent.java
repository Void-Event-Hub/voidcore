package voideventhub.voidcore.cardinal;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import voideventhub.voidcore.repository.MongoDbRepository;
import voideventhub.voidcore.repository.Repository;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class SyncedPatronComponent implements PatronComponent, AutoSyncedComponent {

    private boolean isPatron;
    private final Entity provider;

    public SyncedPatronComponent(Entity provider) {
        this.provider = provider;

        if (!this.provider.getEntityWorld().isClient()) {
            determinePatronStatus();
        }
    }

    @Override
    public boolean isPatron() {
        return this.isPatron;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.isPatron = tag.getBoolean("isPatron");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putBoolean("isPatron", this.isPatron);
    }

    private void determinePatronStatus() {
        CompletableFuture.supplyAsync(() -> {
            Repository repository = MongoDbRepository.getInstance();
            return repository.getPatrons();
        }).thenAcceptAsync((patrons) -> {
            UUID uuid = this.provider.getUuid();
            this.isPatron = patrons.contains(uuid);
        }, this.provider.getServer());
    }
}
