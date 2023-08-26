package voideventhub.voidcore.client.entity.knight;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.common.item.KnightArmorItem;

public class KnightArmorModel extends AnimatedGeoModel<KnightArmorItem> {

    private final String texture;

    @Override
    public Identifier getModelResource(KnightArmorItem object) {
        return new Identifier(VoidCore.MOD_ID, "geo/knight_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(KnightArmorItem object) {
        return new Identifier(VoidCore.MOD_ID, "textures/models/armor/" + texture + ".png");
    }

    @Override
    public Identifier getAnimationResource(KnightArmorItem animatable) {
        return new Identifier(VoidCore.MOD_ID, "animations/knight_armor_animation.json");
    }

    public static KnightArmorModel none() {
        return new KnightArmorModel("knight_armor");
    }

    public static KnightArmorModel diamond() {
        return new KnightArmorModel("knight_armor_diamond");
    }

    public static KnightArmorModel gold() {
        return new KnightArmorModel("knight_armor_gold");
    }

    public static KnightArmorModel iron() {
        return new KnightArmorModel("knight_armor_iron");
    }

    public static KnightArmorModel leather() {
        return new KnightArmorModel("knight_armor_leather");
    }

    public static KnightArmorModel netherite() {
        return new KnightArmorModel("knight_armor_netherite");
    }

    private KnightArmorModel(String texture) {
        this.texture = texture;
    }

}
