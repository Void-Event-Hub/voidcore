package voideventhub.voidcore.client.screens;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.Positioning;
import io.wispforest.owo.ui.core.Sizing;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.VoidCore;
import voideventhub.voidcore.cardinal.CosmeticComponent;
import voideventhub.voidcore.cardinal.VCComponents;
import voideventhub.voidcore.item.VCItems;
import voideventhub.voidcore.networking.VCNetwork;

import java.util.List;
import java.util.Map;

public class CosmeticsScreen extends BaseUIModelScreen<FlowLayout> {

    private final Screen parent;

    public CosmeticsScreen(@Nullable Screen parent) {
        super(FlowLayout.class, DataSource.asset(new Identifier(VoidCore.MOD_ID, "cosmetics_screen")));
        this.parent = parent;
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        if (this.client == null || this.client.player == null) {
            return;
        }

        rootComponent.child(Components.entity(Sizing.fixed(35), this.client.player).positioning(Positioning.relative(15, 50)));
        for (Map.Entry<VCItems.CosmeticType, List<ArmorItem>> entry : VCItems.COSMETICS.entrySet()) {
            VCItems.CosmeticType cosmeticType = entry.getKey();
            String cosmeticId = cosmeticType.getId();
            String cosmeticName = cosmeticId.substring(0, 1).toUpperCase() + cosmeticId.substring(1);

            rootComponent.childById(FlowLayout.class, "cosmetics-list")
                    .child(this.model.expandTemplate(FlowLayout.class, "cosmetic", Map.of(
                                    "cosmetic-id", cosmeticId,
                                    "name", cosmeticName
                            )
                    ));

            ButtonComponent acceptButton = rootComponent.childById(ButtonComponent.class, cosmeticId + "-accept");
            List<ArmorItem> armorItems = entry.getValue();
            acceptButton.active(!hasFullSetEquipped(cosmeticType));

            acceptButton.onPress(button -> {
                if (armorItems.isEmpty()) {
                    clearCosmetics();
                } else {
                    for (ArmorItem item : armorItems) {
                        setCosmetic(item);
                    }
                }
                this.client.setScreen(new CosmeticsScreen(this.parent)); // refresh screen to update buttons
            });
        }
    }

    private boolean hasFullSetEquipped(VCItems.CosmeticType cosmeticType) {
        List<ArmorItem> armorItems = VCItems.COSMETICS.get(cosmeticType);

        CosmeticComponent component = this.client.player.getComponent(VCComponents.COSMETIC);

        if (cosmeticType == VCItems.CosmeticType.NONE) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (component.getArmorCosmetic(slot) != null) {
                    return false;
                }
            }

            return true;
        }

        for (ArmorItem armorItem : armorItems) {
            if (component.getArmorCosmetic(armorItem.getSlotType()) != armorItem) {
                return false;
            }
        }
        return true;
    }

    private void setCosmetic(ArmorItem item) {
        PlayerEntity player = this.client.player;
        player.getComponent(VCComponents.COSMETIC).setArmorCosmetic(item.getSlotType(), item);
        VCNetwork.updatePlayerCosmetics(player);
    }

    private void clearCosmetics() {
        PlayerEntity player = this.client.player;
        player.getComponent(VCComponents.COSMETIC).setArmorCosmetic(EquipmentSlot.HEAD, null);
        player.getComponent(VCComponents.COSMETIC).setArmorCosmetic(EquipmentSlot.CHEST, null);
        player.getComponent(VCComponents.COSMETIC).setArmorCosmetic(EquipmentSlot.LEGS, null);
        player.getComponent(VCComponents.COSMETIC).setArmorCosmetic(EquipmentSlot.FEET, null);
        VCNetwork.updatePlayerCosmetics(player);
    }
}
