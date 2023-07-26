package voideventhub.voidcore.entity.client;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import voideventhub.voidcore.VoidCore;
import voideventhub.voidcore.item.AmethystArmorItem;

public class AmethystArmorModel extends AnimatedGeoModel<AmethystArmorItem> {
    @Override
    public Identifier getModelResource(AmethystArmorItem object) {
        return new Identifier(VoidCore.MOD_ID, "geo/amethyst_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(AmethystArmorItem object) {
        return new Identifier(VoidCore.MOD_ID, "textures/models/armor/amethyst_armor.png");
    }

    @Override
    public Identifier getAnimationResource(AmethystArmorItem animatable) {
        return new Identifier(VoidCore.MOD_ID, "animations/amethyst_armor_animation.json");
    }
}
