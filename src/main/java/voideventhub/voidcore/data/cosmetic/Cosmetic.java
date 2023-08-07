package voideventhub.voidcore.data.cosmetic;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;

/**
 * Holds the cosmetic layout of a player.
 */
public class Cosmetic {

    private final HashMap<EquipmentSlot, CosmeticType> cosmetics;

    /**
     * Creates a new Cosmetic layout with no cosmetics for each equipment slot.
     */
    public Cosmetic() {
        this.cosmetics = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            this.cosmetics.put(slot, CosmeticType.NONE);
        }
    }

    /**
     * Returns the cosmetic of the given slot.
     * @param slot equipment slot
     * @return cosmetic type
     */
    public CosmeticType get(EquipmentSlot slot) {
        return this.cosmetics.get(slot);
    }

    /**
     * Sets the cosmetic of the given slot.
     * @param slot equipment slot
     * @param cosmetic cosmetic type
     */
    public void set(EquipmentSlot slot, CosmeticType cosmetic) {
        this.cosmetics.put(slot, cosmetic);
    }

    /**
     * Writes the Cosmetic to NBT.
     * @return nbt containing the cosmetic data
     */
    public NbtCompound write() {
        NbtCompound nbt = new NbtCompound();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            nbt.putString(slot.getName(), this.cosmetics.get(slot).name());
        }
        return nbt;
    }

    /**
     * Creates a new Cosmetic from NBT.
     * @param nbt NbtCompound to read from
     */
    public Cosmetic(NbtCompound nbt) {
        this.cosmetics = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            this.cosmetics.put(slot, CosmeticType.valueOf(nbt.getString(slot.getName())));
        }
    }
}
