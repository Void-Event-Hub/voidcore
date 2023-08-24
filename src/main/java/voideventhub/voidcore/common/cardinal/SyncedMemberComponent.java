package voideventhub.voidcore.common.cardinal;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.repository.models.member.Role;

import java.util.List;

public class SyncedMemberComponent implements MemberComponent, AutoSyncedComponent {

    private final Entity provider;

    private long discordId;
    private String discordUsername;
    private boolean hasConfirmedMinecraft;
    private List<Role> roles;

    public SyncedMemberComponent(Entity provider) {
        this.provider = provider;
    }

    @Override
    public long getDiscordId() {
        return this.discordId;
    }

    @Override
    public @Nullable String getDiscordUsername() {
        return this.discordUsername;
    }

    @Override
    public boolean hasConfirmedMinecraft() {
        return this.hasConfirmedMinecraft;
    }

    @Override
    public List<Role> getRoles() {
        if (this.roles == null) {
            this.roles = List.of();
        }
        return this.roles;
    }

    @Override
    public boolean isPatron() {
        if (this.roles == null) {
            return false;
        }
        return this.roles.stream().anyMatch(role -> role.getName().equals("Patron"));
    }

    @Override
    public boolean isServerBooster() {
        if (this.roles == null) {
            return false;
        }
        return this.roles.stream().anyMatch(role -> role.getName().equals("Server Booster"));
    }

    public void setDiscordId(long discordId) {
        this.discordId = discordId;
    }

    public void setDiscordUsername(String discordUsername) {
        this.discordUsername = discordUsername;
    }

    public void setHasConfirmedMinecraft(boolean hasConfirmedMinecraft) {
        this.hasConfirmedMinecraft = hasConfirmedMinecraft;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound tag) {
        if (this.discordId != 0) {
            tag.putLong("discordId", this.discordId);
        }

        if (this.discordUsername != null) {
            tag.putString("discordUsername", this.discordUsername);
        }

        tag.putBoolean("hasConfirmedMinecraft", this.hasConfirmedMinecraft);

        if (this.roles != null) {
            NbtList rolesList = new NbtList();
            for (Role role : this.roles) {
                rolesList.add(role.write(new NbtCompound()));
            }

            tag.put("roles", rolesList);
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        if (tag.contains("discordId")) {
            this.discordId = tag.getLong("discordId");
        }
        if (tag.contains("discordUsername")) {
            this.discordUsername = tag.getString("discordUsername");
        }
        this.hasConfirmedMinecraft = tag.getBoolean("hasConfirmedMinecraft");

        if (tag.contains("roles")) {
            this.roles = tag.getList("roles", NbtElement.LIST_TYPE).stream().map(nbt -> new Role((NbtCompound) nbt)).toList();
        }
    }
}
