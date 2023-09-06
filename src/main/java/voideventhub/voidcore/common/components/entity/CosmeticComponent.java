package voideventhub.voidcore.common.components.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

/**
 * Contains the cosmetics a player has equipped
 */
public interface CosmeticComponent extends ComponentV3 {

    /**
     * Get the armor item cosmetic for the given slot specifically for what the player is currently wearing
     *
     * @param slot              the slot where the cosmetic is equipped
     * @param currentlyEquipped the material of the currently equipped armor item
     * @return armor item cosmetic
     */
    @Nullable
    ArmorItem getArmorCosmetic(EquipmentSlot slot, ArmorMaterial currentlyEquipped);

    /**
     * @return the cape texture for the player
     */
    @Nullable
    Identifier getCapeTexture();

    /**
     * @return the elytra texture for the player
     */
    @Nullable
    Identifier getElytraTexture();

    /**
     * @return sword Cosmetic item
     */
    @Nullable
    SwordItem getSwordItem();

    /**
     * @return shield Cosmetic item
     */
    @Nullable
    ShieldItem getShieldItem();

    /**
     * Set the armor material cosmetic for the given slot
     * Since the item can differ depending on what the player is wearing, we pass in the material to
     * specify which set of armor to use
     *
     * @param slot     the slot where the cosmetic is equipped
     * @param material the material of the armor
     */
    void setArmorCosmetic(EquipmentSlot slot, @Nullable ArmorMaterial material);

    /**
     * Set the cape texture for the player
     *
     * @param capeTexture cape texture
     */
    void setCapeTexture(@Nullable Identifier capeTexture);

    /**
     * Set the elytra texture for the player
     *
     * @param elytraTexture elytra texture
     */
    void setElytraTexture(@Nullable Identifier elytraTexture);

    /**
     * Set the sword item cosmetic
     */
    void setSwordItem(@Nullable SwordItem item);

    /**
     * Set the shield item cosmetic
     */
    void setShieldItem(@Nullable ShieldItem item);

    /**
     * @return whether the player has a full set of the given cosmetic material equipped
     */
    boolean hasFullSetEquipped(ArmorMaterial cosmeticMaterial);

}
