package voideventhub.voidcore.common.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import voideventhub.voidcore.common.components.CosmeticComponent;
import voideventhub.voidcore.common.components.VCComponents;
import voideventhub.voidcore.common.item.VCItems;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Mixin for ArmorFeatureRenderer.
 * Allows us to render different armor models than the one the player has equipped.
 */
@Environment(EnvType.CLIENT)
@Mixin(value = ArmorFeatureRenderer.class, priority = 650)
public abstract class ArmorMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {

    @Shadow
    protected abstract void setVisible(A bipedModel, EquipmentSlot slot);

    @Shadow
    protected abstract boolean usesInnerModel(EquipmentSlot slot);

    @Shadow
    protected abstract void renderArmorParts(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorItem item, boolean usesSecondLayer, A model, boolean legs, float red, float green, float blue, @Nullable String overlay);

    @Unique
    private final List<Supplier<Boolean>> renderList = new LinkedList<>();

    public ArmorMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void renderCustomArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot slot, int light, A model, CallbackInfo ci) {
        ItemStack equippedStack = entity.getEquippedStack(slot);
        ItemStack cosmeticStack = new ItemStack(getCosmeticArmor(slot, entity));

        if (cosmeticStack.isEmpty()) {
            return;
        }

        ArmorRenderer renderer = ArmorRendererRegistryImpl.get(cosmeticStack.getItem());

        if (renderer != null) {
            renderList.add(() -> {
                renderer.render(matrices, vertexConsumers, cosmeticStack, entity, slot, light,
                        (BipedEntityModel<LivingEntity>) getContextModel());
                return true;
            });
            ci.cancel();
        } else {
            if(ArmorRendererRegistryImpl.get(equippedStack.getItem()) != null) {
                renderList.add(() -> {
                    cosmeticarmor$renderArmor(matrices, vertexConsumers, cosmeticStack, slot, light, model);
                    return true;
                });
                ci.cancel();
            }
        }
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At("TAIL"))
    private void renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float customAngle, float headYaw, float headPitch, CallbackInfo ci) {
        renderList.forEach(Supplier::get);
        renderList.clear();
    }

    @Redirect(method = "renderArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
    private ItemStack modifyVisibleArmor(LivingEntity entity, EquipmentSlot slot) {
        ItemStack equippedStack = entity.getEquippedStack(slot);

        if (equippedStack == null && slot == EquipmentSlot.CHEST) {
            return VCItems.AMETHYST_CHESTPLATE.getDefaultStack();
        }

        return equippedStack;
    }

    @Unique
    private void cosmeticarmor$renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack itemStack, EquipmentSlot armorSlot, int light, A model) {
        if (!(itemStack.getItem() instanceof ArmorItem armorItem)) {
            return;
        }
        if (armorItem.getSlotType() != armorSlot) {
            return;
        }
        this.getContextModel().setAttributes(model);
        this.setVisible(model, armorSlot);
        boolean bl = this.usesInnerModel(armorSlot);
        boolean bl2 = itemStack.hasGlint();
        if (armorItem instanceof DyeableArmorItem) {
            int i = ((DyeableArmorItem)armorItem).getColor(itemStack);
            float f = (float)(i >> 16 & 0xFF) / 255.0f;
            float g = (float)(i >> 8 & 0xFF) / 255.0f;
            float h = (float)(i & 0xFF) / 255.0f;
            this.renderArmorParts(matrices, vertexConsumers, light, armorItem, bl2, model, bl, f, g, h, null);
            this.renderArmorParts(matrices, vertexConsumers, light, armorItem, bl2, model, bl, 1.0f, 1.0f, 1.0f, "overlay");
        } else {
            this.renderArmorParts(matrices, vertexConsumers, light, armorItem, bl2, model, bl, 1.0f, 1.0f, 1.0f, null);
        }
    }

    @Unique
    private @Nullable Item getCosmeticArmor(EquipmentSlot slot, LivingEntity entity) {
        if (entity.getEquippedStack(slot).isEmpty()) {
            return null;
        }

        ArmorItem currentlyEquippedArmor = (ArmorItem) entity.getEquippedStack(slot).getItem();
        CosmeticComponent cosmetics = entity.getComponent(VCComponents.COSMETIC);

        return cosmetics.getArmorCosmetic(slot, currentlyEquippedArmor.getMaterial());
    }

}