package voideventhub.voidcore.common.components.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

public class SyncedDeathComponent implements DeathComponent, AutoSyncedComponent {

    private int deathCount;
    private final Entity provider;

    public SyncedDeathComponent(Entity provider) {
        this.provider = provider;
        this.deathCount = 0;
    }

    @Override
    public void incrementDeathCount() {
        this.deathCount++;
    }

    @Override
    public int getDeathCount() {
        return this.deathCount;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.deathCount = tag.getInt("deathCount");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("deathCount", this.deathCount);
    }
}
