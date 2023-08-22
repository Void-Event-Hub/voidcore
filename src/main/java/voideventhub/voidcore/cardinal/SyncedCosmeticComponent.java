package voideventhub.voidcore.cardinal;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class SyncedCosmeticComponent implements CosmeticComponent, AutoSyncedComponent {

    private final Entity provider;

    private final HashMap<EquipmentSlot, @Nullable ArmorItem> armorCosmetics;

    private @Nullable SwordItem swordItem;
    private @Nullable ShieldItem shieldItem;

    private @Nullable Identifier capeTexture;
    private @Nullable Identifier elytraTexture;

    public SyncedCosmeticComponent(Entity provider) {
        this.provider = provider;
        this.armorCosmetics = new HashMap<>();
    }

    @Override
    public @Nullable ArmorItem getArmorCosmetic(EquipmentSlot slot) {
        return this.armorCosmetics.get(slot);
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
    public void setArmorCosmetic(EquipmentSlot slot, @Nullable ArmorItem armorItem) {
        this.armorCosmetics.put(slot, armorItem);
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
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (tag.contains(slot.getName())) {
                Identifier id = new Identifier(tag.getString(slot.getName()));
                ArmorItem armorItem = (ArmorItem) Registry.ITEM.get(id);
                this.armorCosmetics.put(slot, armorItem);
            } else {
                this.armorCosmetics.put(slot, null);
            }
        }
    }

    private void writeArmorCosmeticsToNbt(NbtCompound tag) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ArmorItem armorItem = this.armorCosmetics.get(slot);
            if (armorItem != null) {
                tag.putString(slot.getName(), Registry.ITEM.getId(armorItem).toString());
            }
        }
    }

}
