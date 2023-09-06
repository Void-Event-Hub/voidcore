package voideventhub.voidcore.common.components.team;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.common.components.VCComponents;

public class SyncedTeamComponent implements TeamComponent, AutoSyncedComponent {

    @Nullable
    private final MinecraftServer server;
    private final Team team;
    private final Scoreboard scoreboard;

    @Nullable
    private Vec3d spawn;

    public SyncedTeamComponent(Team team, Scoreboard scoreboard, @Nullable MinecraftServer server) {
        this.team = team;
        this.scoreboard = scoreboard;
        this.server = server;
        VoidCore.LOGGER.error("SyncedTeamComponent constructor called");
    }

    @Override
    public void setSpawn(@Nullable Vec3d position) {
        this.spawn = position;
        sync();
    }

    @Override
    @Nullable
    public Vec3d getSpawn() {
        return this.spawn;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        if (tag.contains("spawnX") && tag.contains("spawnY") && tag.contains("spawnZ")) {
            this.spawn = new Vec3d(
                    tag.getDouble("spawnX"),
                    tag.getDouble("spawnY"),
                    tag.getDouble("spawnZ")
            );
        }
    }

    @Override
    public void writeToNbt(@NotNull NbtCompound tag) {
        if (this.spawn == null) {
            return;
        }

        tag.putDouble("spawnX", this.spawn.getX());
        tag.putDouble("spawnY", this.spawn.getY());
        tag.putDouble("spawnZ", this.spawn.getZ());
    }

    private void sync() {
        VCComponents.TEAM.sync(this.team);
    }
}
