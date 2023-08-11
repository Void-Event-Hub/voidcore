package voideventhub.voidcore.cardinal;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public interface CosmeticComponent extends ComponentV3 {

    /**
     * Get the armor item cosmetic for the given slot
     *
     * @param slot the slot where the cosmetic is equipped
     * @return armor item cosmetic
     */
    @Nullable
    ArmorItem getArmorCosmetic(EquipmentSlot slot);

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
     * Set the armor item cosmetic for the given slot
     * @param slot the slot where the cosmetic is equipped
     * @param armorItem armor item cosmetic
     */
    void setArmorCosmetic(EquipmentSlot slot, @Nullable ArmorItem armorItem);

    /**
     * Set the cape texture for the player
     * @param capeTexture cape texture
     */
    void setCapeTexture(@Nullable Identifier capeTexture);

    /**
     * Set the elytra texture for the player
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

}
