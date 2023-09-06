package voideventhub.voidcore.common.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

import java.util.List;

/**
 * Determiner of what cosmetic-variant needs to be displayed for the given armor slot and material
 */
public interface ArmorCosmeticRepository {

    /**
     * Get the cosmetic item for the given slot and material specifically for what the player is currently wearing
     * Would the player be wearing diamond armor, it would return the diamond version of the cosmetic item for the given slot
     * @param slot the slot to get the cosmetic item for
     * @param cosmetic the material of the cosmetic item
     * @param currentlyEquipped the material of the currently equipped armor item
     */
    ArmorItem getArmorCosmetic(EquipmentSlot slot, ArmorMaterial cosmetic, ArmorMaterial currentlyEquipped);

    /**
     * Set the cosmetic item for the given slot and material for what the player is currently wearing
     * @param slot the slot to set the cosmetic item for
     * @param cosmetic the material of the cosmetic item
     * @param currentlyEquipped the material of the currently equipped armor item
     * @param item the item to set the cosmetic item for
     */
    void setArmorCosmetic(EquipmentSlot slot, ArmorMaterial cosmetic, ArmorMaterial currentlyEquipped, ArmorItem item);

    /**
     * @return all cosmetic armor materials
     */
    List<ArmorMaterial> getCosmeticArmorMaterials();

}
