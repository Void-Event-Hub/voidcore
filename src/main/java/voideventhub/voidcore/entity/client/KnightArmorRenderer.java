package voideventhub.voidcore.entity.client;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import voideventhub.voidcore.item.KnightArmorItem;

public class KnightArmorRenderer extends GeoArmorRenderer<KnightArmorItem> {
    public KnightArmorRenderer() {
        super(new KnightArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}