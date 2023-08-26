package voideventhub.voidcore.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.item.ArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import voideventhub.voidcore.client.entity.knight.*;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.client.screens.CosmeticsScreen;
import voideventhub.voidcore.client.screens.VCKeyBinds;
import voideventhub.voidcore.client.entity.amethyst.AmethystArmorRenderer;
import voideventhub.voidcore.common.item.VCItems;

public class VoidCoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        VoidCore.LOGGER.info("Initializing VoidCoreClient");
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_HELMET);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_CHESTPLATE);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_LEGGINGS);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_BOOTS);

        initAllKnightArmor();

        KeyBindingHelper.registerKeyBinding(VCKeyBinds.OPEN_COSMETICS_SCREEN);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (VCKeyBinds.OPEN_COSMETICS_SCREEN.wasPressed()) {
                client.setScreen(new CosmeticsScreen(null));
            }
        });
    }

    private static void initAllKnightArmor() {
        initKnightArmor(KnightArmorModel.none(), VCItems.KNIGHT_HELMET);
        initKnightArmor(KnightArmorModel.none(), VCItems.KNIGHT_CHESTPLATE);
        initKnightArmor(KnightArmorModel.none(), VCItems.KNIGHT_LEGGINGS);
        initKnightArmor(KnightArmorModel.none(), VCItems.KNIGHT_BOOTS);

        initKnightArmor(KnightArmorModel.netherite(), VCItems.NETHERITE_KNIGHT_HELMET);
        initKnightArmor(KnightArmorModel.netherite(), VCItems.NETHERITE_KNIGHT_CHESTPLATE);
        initKnightArmor(KnightArmorModel.netherite(), VCItems.NETHERITE_KNIGHT_LEGGINGS);
        initKnightArmor(KnightArmorModel.netherite(), VCItems.NETHERITE_KNIGHT_BOOTS);

        initKnightArmor(KnightArmorModel.diamond(), VCItems.DIAMOND_KNIGHT_HELMET);
        initKnightArmor(KnightArmorModel.diamond(), VCItems.DIAMOND_KNIGHT_CHESTPLATE);
        initKnightArmor(KnightArmorModel.diamond(), VCItems.DIAMOND_KNIGHT_LEGGINGS);
        initKnightArmor(KnightArmorModel.diamond(), VCItems.DIAMOND_KNIGHT_BOOTS);

        initKnightArmor(KnightArmorModel.gold(), VCItems.GOLD_KNIGHT_HELMET);
        initKnightArmor(KnightArmorModel.gold(), VCItems.GOLD_KNIGHT_CHESTPLATE);
        initKnightArmor(KnightArmorModel.gold(), VCItems.GOLD_KNIGHT_LEGGINGS);
        initKnightArmor(KnightArmorModel.gold(), VCItems.GOLD_KNIGHT_BOOTS);

        initKnightArmor(KnightArmorModel.iron(), VCItems.IRON_KNIGHT_HELMET);
        initKnightArmor(KnightArmorModel.iron(), VCItems.IRON_KNIGHT_CHESTPLATE);
        initKnightArmor(KnightArmorModel.iron(), VCItems.IRON_KNIGHT_LEGGINGS);
        initKnightArmor(KnightArmorModel.iron(), VCItems.IRON_KNIGHT_BOOTS);

        initKnightArmor(KnightArmorModel.leather(), VCItems.LEATHER_KNIGHT_HELMET);
        initKnightArmor(KnightArmorModel.leather(), VCItems.LEATHER_KNIGHT_CHESTPLATE);
        initKnightArmor(KnightArmorModel.leather(), VCItems.LEATHER_KNIGHT_LEGGINGS);
        initKnightArmor(KnightArmorModel.leather(), VCItems.LEATHER_KNIGHT_BOOTS);
    }

    private static void initKnightArmor(KnightArmorModel model, ArmorItem item) {
        GeoArmorRenderer.registerArmorRenderer(new KnightArmorRenderer(model), item);
    }
}
