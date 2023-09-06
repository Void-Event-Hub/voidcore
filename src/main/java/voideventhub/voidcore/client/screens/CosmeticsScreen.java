package voideventhub.voidcore.client.screens;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.EntityComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.Positioning;
import io.wispforest.owo.ui.core.Sizing;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.common.components.entity.CosmeticComponent;
import voideventhub.voidcore.common.components.VCComponents;
import voideventhub.voidcore.common.item.ArmorCosmeticRepository;
import voideventhub.voidcore.common.item.CosmeticRepository;
import voideventhub.voidcore.common.networking.VCNetwork;

import java.util.Arrays;
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

        ArmorCosmeticRepository provider = CosmeticRepository.getInstance();
        List<ArmorMaterial> cosmeticMaterials = provider.getCosmeticArmorMaterials();

        ClientPlayerEntity entity = EntityComponent.createRenderablePlayer(this.client.player.getGameProfile());
        entity.readNbt(this.client.player.writeNbt(new NbtCompound()));

        rootComponent.child(Components.entity(Sizing.fixed(35), entity).lookAtCursor(true).positioning(Positioning.relative(15, 50)));
        rootComponent.childById(FlowLayout.class, "cosmetics-list")
                .child(getCosmeticCard("Clear", null));

        for (ArmorMaterial cosmeticMaterial : cosmeticMaterials) {
            String name = cosmeticMaterial.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);

            rootComponent.childById(FlowLayout.class, "cosmetics-list")
                    .child(getCosmeticCard(name, cosmeticMaterial));
        }
    }

    private FlowLayout getCosmeticCard(String name, ArmorMaterial cosmeticMaterial) {
        FlowLayout card = this.model.expandTemplate(FlowLayout.class, "cosmetic", Map.of(
                        "cosmetic-id", name,
                        "name", name
                )
        );
        ButtonComponent acceptButton = card.childById(ButtonComponent.class, name + "-accept");

        CosmeticComponent cosmeticComponent = this.client.player.getComponent(VCComponents.COSMETIC);
        acceptButton.active(!cosmeticComponent.hasFullSetEquipped(cosmeticMaterial));

        acceptButton.onPress(button -> {
            setCosmetic(cosmeticMaterial);
            this.client.setScreen(new CosmeticsScreen(this.parent)); // refresh screen to update buttons
        });
        return card;
    }

    private void setCosmetic(ArmorMaterial cosmeticMaterial) {
        PlayerEntity player = this.client.player;

        List<EquipmentSlot> armorSlots = Arrays.stream(EquipmentSlot.values())
                .filter(slot -> slot.getType() == EquipmentSlot.Type.ARMOR)
                .toList();

        CosmeticComponent cosmeticComponent = player.getComponent(VCComponents.COSMETIC);

        for (EquipmentSlot slot : armorSlots) {
            cosmeticComponent.setArmorCosmetic(slot, cosmeticMaterial);
        }

        VCNetwork.updatePlayerCosmetics(player);
    }

}
