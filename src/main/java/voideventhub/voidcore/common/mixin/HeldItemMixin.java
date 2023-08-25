package voideventhub.voidcore.common.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

//TODO: There is currently a bug where the actual held item is rendered in first person. Third person looks fine.
@Mixin(value = HeldItemFeatureRenderer.class, priority = 650)
public abstract class HeldItemMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {

    @Shadow
    protected void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
    }

    public HeldItemMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        boolean bl = livingEntity.getMainArm() == Arm.RIGHT;
        ItemStack itemStack = bl ? livingEntity.getOffHandStack() : livingEntity.getMainHandStack();
        ItemStack itemStack2 = bl ? livingEntity.getMainHandStack() : livingEntity.getOffHandStack();
        if (!itemStack.isEmpty() || !itemStack2.isEmpty()) {
            matrixStack.push();
            if (this.getContextModel().child) {
                float m = 0.5F;
                matrixStack.translate(0.0, 0.75, 0.0);
                matrixStack.scale(0.5F, 0.5F, 0.5F);
            }

            this.renderItem(livingEntity, Items.SHIELD.getDefaultStack(), ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND, Arm.RIGHT, matrixStack, vertexConsumerProvider, i);
            this.renderItem(livingEntity, Items.DIAMOND_AXE.getDefaultStack(), ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND, Arm.LEFT, matrixStack, vertexConsumerProvider, i);
            matrixStack.pop();
        }
    }

}
