package voideventhub.voidcore.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin extends FeatureRenderer<LivingEntity, BipedEntityModel<LivingEntity>> {

    public ArmorFeatureRendererMixin(FeatureRendererContext<LivingEntity, BipedEntityModel<LivingEntity>> context) {
        super(context);
    }

    @Invoker("setVisible")
    public abstract void setVisible(BipedEntityModel model, EquipmentSlot slot);

    @Invoker("renderArmorParts")
    public abstract void renderArmorParts(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorItem item, boolean glint, BipedEntityModel model, boolean secondLayer, float red, float green, float blue, String overlay);

    @Invoker("usesInnerModel")
    public abstract boolean usesInnerModel(EquipmentSlot slot);

}
