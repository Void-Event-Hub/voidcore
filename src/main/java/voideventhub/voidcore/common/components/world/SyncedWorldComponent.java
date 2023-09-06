package voideventhub.voidcore.common.components.world;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import voideventhub.voidcore.common.components.VCComponents;

public class SyncedWorldComponent implements WorldComponent, AutoSyncedComponent {

    private boolean hasStarted;
    private final World provider;

    public SyncedWorldComponent(World provider) {
        this.provider = provider;
        this.hasStarted = false;
    }

    @Override
    public boolean eventHasStarted() {
        return this.hasStarted;
    }

    @Override
    public void startEvent() {
        this.hasStarted = true;
        sync();
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.hasStarted = tag.getBoolean("hasStarted");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putBoolean("hasStarted", this.hasStarted);
    }

    private void sync() {
        VCComponents.WORLD.sync(this.provider);
    }
}
