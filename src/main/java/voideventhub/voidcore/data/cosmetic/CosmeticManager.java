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

/**
 * Manages the cosmetic data of all players.
 * Any changes to the cosmetic data of a player should be done through this class.
 */
@Deprecated
public class CosmeticManager extends PersistentState {

    private final HashMap<UUID, Cosmetic> playerCosmetics = new HashMap<>();

    /**
     * Returns the CosmeticManager of the given world.
     * @param world server world
     * @return CosmeticManager of the given world
     */
    public static CosmeticManager get(ServerWorld world) {
        PersistentStateManager stateManager = world.getPersistentStateManager();
        return stateManager.getOrCreate(CosmeticManager::new, CosmeticManager::new, "cosmetic");
    }

    /**
     * Sets the cosmetic of the given slot of the player.
     * @param uuid player uuid
     * @param slot equipment slot
     * @param cosmeticType cosmetic type
     */
    public void setPlayerCosmetics(UUID uuid, EquipmentSlot slot, CosmeticType cosmeticType) {
        if (!playerCosmetics.containsKey(uuid)) {
            playerCosmetics.put(uuid, new Cosmetic());
        }

        playerCosmetics.get(uuid).set(slot, cosmeticType);
        setDirty(true);
    }

    public CosmeticManager() {
        super();
    }

    /**
     * creates a new CosmeticManager from NBT.
     * @param nbt NbtCompound to read from
     */
    public CosmeticManager(NbtCompound nbt) {
        for (String uuid : nbt.getKeys()) {
            playerCosmetics.put(UUID.fromString(uuid), new Cosmetic(nbt.getCompound(uuid)));
        }
    }

    /**
     * Writes the current state to NBT.
     * @param nbt NbtCompound to write to
     * @return the given NbtCompound containing the current state
     */
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        for (UUID uuid : playerCosmetics.keySet()) {
            nbt.put(uuid.toString(), playerCosmetics.get(uuid).write());
        }
        return nbt;
    }

    /**
     * Returns a PacketByteBuf containing the cosmetic data.
     * Used to transfer the current state over the network.
     * @return PacketByteBuf containing the cosmetic data
     */
    public PacketByteBuf toBuf() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeNbt(this.writeNbt(new NbtCompound()));
        return buf;
    }

}
