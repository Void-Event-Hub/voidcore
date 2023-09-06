package voideventhub.voidcore.common.components.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.common.components.VCComponents;
import voideventhub.voidcore.common.item.CosmeticRepository;
import voideventhub.voidcore.common.item.VCArmorMaterials;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SyncedCosmeticComponent implements CosmeticComponent, AutoSyncedComponent {

    private final Entity provider;

    private final HashMap<EquipmentSlot, @Nullable ArmorMaterial> armorCosmetics;

    private @Nullable SwordItem swordItem;
    private @Nullable ShieldItem shieldItem;

    private @Nullable Identifier capeTexture;
    private @Nullable Identifier elytraTexture;

    public SyncedCosmeticComponent(Entity provider) {
        this.provider = provider;
        this.armorCosmetics = new HashMap<>();
    }

    @Override
    public @Nullable ArmorItem getArmorCosmetic(EquipmentSlot slot, ArmorMaterial currentlyEquipped) {
        CosmeticRepository provider = CosmeticRepository.getInstance();
        ArmorMaterial cosmeticMaterial = this.armorCosmetics.get(slot);
        if (cosmeticMaterial != null) {
            return provider.getArmorCosmetic(slot, cosmeticMaterial, currentlyEquipped);
        }
        return null;
    }

    @Override
    public @Nullable Identifier getCapeTexture() {
        return this.capeTexture;
    }

    @Override
    public @Nullable Identifier getElytraTexture() {
        return this.elytraTexture;
    }

    @Override
    public @Nullable SwordItem getSwordItem() {
        return this.swordItem;
    }

    @Override
    public @Nullable ShieldItem getShieldItem() {
        return this.shieldItem;
    }

    @Override
    public void setArmorCosmetic(EquipmentSlot slot, @Nullable ArmorMaterial material) {
        this.armorCosmetics.put(slot, material);
        sync();
    }

    @Override
    public void setCapeTexture(@Nullable Identifier capeTexture) {
        this.capeTexture = capeTexture;
        sync();
    }

    @Override
    public void setElytraTexture(@Nullable Identifier elytraTexture) {
        this.elytraTexture = elytraTexture;
        sync();
    }

    @Override
    public void setSwordItem(@Nullable SwordItem item) {
        this.swordItem = item;
        sync();
    }

    @Override
    public void setShieldItem(@Nullable ShieldItem item) {
        this.shieldItem = item;
        sync();
    }

    @Override
    public boolean hasFullSetEquipped(ArmorMaterial cosmeticMaterial) {
        List<ArmorMaterial> equippedArmorCosmetics = this.armorCosmetics.values()
                .stream()
                .toList();

        for (ArmorMaterial material : equippedArmorCosmetics) {
            if (material != cosmeticMaterial) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        if (tag.contains("capeTexture")) {
            this.capeTexture = new Identifier(tag.getString("capeTexture"));
        } else {
            this.capeTexture = null;
        }
        if (tag.contains("elytraTexture")) {
            this.elytraTexture = new Identifier(tag.getString("elytraTexture"));
        } else {
            this.elytraTexture = null;
        }

        if (tag.contains("swordItem")) {
            this.swordItem = (SwordItem) Registry.ITEM.get(new Identifier(tag.getString("swordItem")));
        } else {
            this.swordItem = null;
        }
        if (tag.contains("shieldItem")) {
            this.shieldItem = (ShieldItem) Registry.ITEM.get(new Identifier(tag.getString("shieldItem")));
        } else {
            this.shieldItem = null;
        }

        readArmorCosmeticsFromNbt(tag);
    }

    @Override
    public void writeToNbt(@NotNull NbtCompound tag) {
        if (this.capeTexture != null) {
            tag.putString("capeTexture", this.capeTexture.toString());
        }
        if (this.elytraTexture != null) {
            tag.putString("elytraTexture", this.elytraTexture.toString());
        }

        if (this.swordItem != null) {
            tag.putString("swordItem", Registry.ITEM.getId(this.swordItem).toString());
        }
        if (this.shieldItem != null) {
            tag.putString("shieldItem", Registry.ITEM.getId(this.shieldItem).toString());
        }


        writeArmorCosmeticsToNbt(tag);
    }

    private void sync() {
        VCComponents.COSMETIC.sync(provider);
    }

    private void readArmorCosmeticsFromNbt(NbtCompound tag) {
        for (EquipmentSlot slot : armorSlots()) {
            if (tag.contains(slot.getName())) {
                String materialName = tag.getString(slot.getName());
                ArmorMaterial material = VCArmorMaterials.valueOf(materialName);
                this.armorCosmetics.put(slot, material);
            } else {
                this.armorCosmetics.put(slot, null);
            }
        }
    }

    private void writeArmorCosmeticsToNbt(NbtCompound tag) {
        for (EquipmentSlot slot : armorSlots()) {
            ArmorMaterial material = this.armorCosmetics.get(slot);
            if (material != null) {
                tag.putString(slot.getName(), material.getName().toUpperCase());
            }
        }
    }

    private List<EquipmentSlot> armorSlots() {
        // equipment slots contain main and offhand, so we filter those out
        return Arrays.stream(EquipmentSlot.values())
                .filter(slot -> slot.getType() == EquipmentSlot.Type.ARMOR)
                .toList();
    }

}
