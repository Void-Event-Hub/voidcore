package voideventhub.voidcore.data.cosmetic;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;

public class Cosmetic {

    private final HashMap<EquipmentSlot, CosmeticType> cosmetics;

    public Cosmetic() {
        this.cosmetics = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            this.cosmetics.put(slot, CosmeticType.NONE);
        }
    }

    public CosmeticType get(EquipmentSlot slot) {
        return this.cosmetics.get(slot);
    }

    public void set(EquipmentSlot slot, CosmeticType cosmetic) {
        this.cosmetics.put(slot, cosmetic);
    }

    public NbtCompound write() {
        NbtCompound nbt = new NbtCompound();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            nbt.putString(slot.getName(), this.cosmetics.get(slot).name());
        }
        return nbt;
    }

    public Cosmetic(NbtCompound nbt) {
        this.cosmetics = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            this.cosmetics.put(slot, CosmeticType.valueOf(nbt.getString(slot.getName())));
        }
    }
}
