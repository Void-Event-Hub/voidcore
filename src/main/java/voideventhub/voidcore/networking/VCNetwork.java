package voideventhub.voidcore.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import voideventhub.voidcore.cardinal.SyncedCosmeticComponent;
import voideventhub.voidcore.cardinal.VCComponents;
import voideventhub.voidcore.networking.packets.UpdateCosmeticsComponentC2SPacket;

public class VCNetwork {

    /**
     * Communicates to the server that an update to the player's cosmetics has been made client-side.
     */
    public static void updatePlayerCosmetics(PlayerEntity player) {
        NbtCompound nbt = new NbtCompound();
        SyncedCosmeticComponent component = (SyncedCosmeticComponent) player.getComponent(VCComponents.COSMETIC);
        component.writeToNbt(nbt);
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeNbt(nbt);
        ClientPlayNetworking.send(UpdateCosmeticsComponentC2SPacket.ID, buf);
    }

    public static void registerC2SPackets() {
        UpdateCosmeticsComponentC2SPacket.register();
    }

    public static void registerS2CPackets() {
    }

}
