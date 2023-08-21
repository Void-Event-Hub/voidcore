package voideventhub.voidcore.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import voideventhub.voidcore.VoidCore;
import voideventhub.voidcore.client.screens.CosmeticsScreen;
import voideventhub.voidcore.client.screens.VCKeyBinds;
import voideventhub.voidcore.entity.client.AmethystArmorRenderer;
import voideventhub.voidcore.entity.client.KnightArmorRenderer;
import voideventhub.voidcore.item.VCItems;

public class VoidCoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        VoidCore.LOGGER.info("Initializing VoidCoreClient");
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_HELMET);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_CHESTPLATE);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_LEGGINGS);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_BOOTS);

        GeoArmorRenderer.registerArmorRenderer(new KnightArmorRenderer(), VCItems.KNIGHT_HELMET);
        GeoArmorRenderer.registerArmorRenderer(new KnightArmorRenderer(), VCItems.KNIGHT_CHESTPLATE);
        GeoArmorRenderer.registerArmorRenderer(new KnightArmorRenderer(), VCItems.KNIGHT_LEGGINGS);
        GeoArmorRenderer.registerArmorRenderer(new KnightArmorRenderer(), VCItems.KNIGHT_BOOTS);

        KeyBindingHelper.registerKeyBinding(VCKeyBinds.OPEN_COSMETICS_SCREEN);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (VCKeyBinds.OPEN_COSMETICS_SCREEN.wasPressed()) {
                client.setScreen(new CosmeticsScreen(null));
            }
        });

    }
}
