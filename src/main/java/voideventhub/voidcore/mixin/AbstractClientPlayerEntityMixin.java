package voideventhub.voidcore.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import voideventhub.voidcore.VoidCore;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {

    @Inject(method = "canRenderCapeTexture", at = @At("HEAD"), cancellable = true)
    private void allowCapeRenderingWhenPlayerIsPatron(CallbackInfoReturnable<Boolean> cir) {
        // TODO: check if player is patron
        cir.setReturnValue(true);
    }

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void replaceCapeTexture(CallbackInfoReturnable<Identifier> cir) {
        cir.setReturnValue(new Identifier(VoidCore.MOD_ID, "textures/capes/cape.png"));
    }

}
