package voideventhub.voidcore.entity.client;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import voideventhub.voidcore.VoidCore;
import voideventhub.voidcore.item.KnightArmorItem;

public class KnightArmorModel extends AnimatedGeoModel<KnightArmorItem> {

    @Override
    public Identifier getModelResource(KnightArmorItem object) {
        return new Identifier(VoidCore.MOD_ID, "geo/knight_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(KnightArmorItem object) {
        return new Identifier(VoidCore.MOD_ID, "textures/models/armor/knight_armor.png");
    }

    @Override
    public Identifier getAnimationResource(KnightArmorItem animatable) {
        return new Identifier(VoidCore.MOD_ID, "animations/knight_armor_animation.json");
    }

}
