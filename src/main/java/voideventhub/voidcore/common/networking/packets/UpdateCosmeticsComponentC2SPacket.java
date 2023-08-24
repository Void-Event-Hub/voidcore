package voideventhub.voidcore.common.networking.packets;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.common.cardinal.VCComponents;

public class UpdateCosmeticsComponentC2SPacket {

    public static final Identifier ID = new Identifier(VoidCore.MOD_ID, "update_cosmetics");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, handler, buf, responseSender) -> {
            NbtCompound nbt = buf.readNbt();
            server.execute(() -> {
                player.getComponent(VCComponents.COSMETIC).readFromNbt(nbt);
                VCComponents.COSMETIC.sync(player);
            });
        });
    }

}
