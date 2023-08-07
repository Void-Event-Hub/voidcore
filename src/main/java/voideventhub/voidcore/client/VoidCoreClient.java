package voideventhub.voidcore.client;

import net.fabricmc.api.ClientModInitializer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import voideventhub.voidcore.VoidCore;
import voideventhub.voidcore.entity.client.AmethystArmorRenderer;
import voideventhub.voidcore.item.VCItems;

public class VoidCoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        VoidCore.LOGGER.info("Initializing VoidCoreClient");
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_HELMET);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_CHESTPLATE);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_LEGGINGS);
        GeoArmorRenderer.registerArmorRenderer(new AmethystArmorRenderer(), VCItems.AMETHYST_BOOTS);
    }
}
