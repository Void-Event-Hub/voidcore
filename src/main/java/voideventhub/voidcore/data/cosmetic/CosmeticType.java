package voideventhub.voidcore.data.cosmetic;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import voideventhub.voidcore.item.VCItems;

import java.util.HashMap;

public enum CosmeticType {

    NONE(ItemStack.EMPTY.getItem(), ItemStack.EMPTY.getItem(), ItemStack.EMPTY.getItem(), ItemStack.EMPTY.getItem()),
    AMETHYST(VCItems.AMETHYST_HELMET, VCItems.AMETHYST_CHESTPLATE, VCItems.AMETHYST_LEGGINGS, VCItems.AMETHYST_BOOTS);


    private final HashMap<EquipmentSlot, Item> slots;

    CosmeticType(Item head, Item chest, Item legs, Item feet) {
        this.slots = new HashMap<>();
        this.slots.put(EquipmentSlot.HEAD, head);
        this.slots.put(EquipmentSlot.CHEST, chest);
        this.slots.put(EquipmentSlot.LEGS, legs);
        this.slots.put(EquipmentSlot.FEET, feet);
    }

    /**
     * Returns the corresponding item for the given slot.
     * @param slot equipment slot
     * @return item
     */
    public Item get(EquipmentSlot slot) {
        return slots.get(slot);
    }

}
