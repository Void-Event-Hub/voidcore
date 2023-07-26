package voideventhub.voidcore.entity.client;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import voideventhub.voidcore.item.AmethystArmorItem;

public class AmethystArmorRenderer extends GeoArmorRenderer<AmethystArmorItem> {
    public AmethystArmorRenderer() {
        super(new AmethystArmorModel());

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
