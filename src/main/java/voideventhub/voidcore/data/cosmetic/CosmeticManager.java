package voideventhub.voidcore.data.cosmetic;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;

import java.util.HashMap;
import java.util.UUID;

public class CosmeticManager extends PersistentState {

    private final HashMap<UUID, Cosmetic> playerCosmetics = new HashMap<>();

    public static CosmeticManager get(ServerWorld world) {
        PersistentStateManager stateManager = world.getPersistentStateManager();
        return stateManager.getOrCreate(CosmeticManager::new, CosmeticManager::new, "cosmetic");
    }

    public CosmeticManager() {
        super();
    }

    public CosmeticManager(NbtCompound nbt) {
        for (String uuid : nbt.getKeys()) {
            playerCosmetics.put(UUID.fromString(uuid), new Cosmetic(nbt.getCompound(uuid)));
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        for (UUID uuid : playerCosmetics.keySet()) {
            nbt.put(uuid.toString(), playerCosmetics.get(uuid).write());
        }
        return nbt;
    }

    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeNbt(this.writeNbt(new NbtCompound()));
        return buf;
    }

    public void setPlayerCosmetics(UUID uuid, EquipmentSlot slot, CosmeticType cosmeticType) {
        if (!playerCosmetics.containsKey(uuid)) {
            playerCosmetics.put(uuid, new Cosmetic());
        }

        playerCosmetics.get(uuid).set(slot, cosmeticType);
        setDirty(true);
    }

}
