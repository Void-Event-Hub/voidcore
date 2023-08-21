package voideventhub.voidcore.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import voideventhub.voidcore.cardinal.SyncedCosmeticComponent;
import voideventhub.voidcore.cardinal.VCComponents;
import voideventhub.voidcore.data.cosmetic.CosmeticManager;
import voideventhub.voidcore.networking.packets.UpdateCosmeticsComponentC2SPacket;
import voideventhub.voidcore.networking.packets.UpdateCosmeticsS2CPacket;

public class VCNetwork {

    /**
     * Updates all players' client cosmetic data.
     * This should be called everytime any player's cosmetic data is changed.
     * @param world server world
     */
    @Deprecated
    public static void updatePlayersClientCosmeticData(ServerWorld world) {
        CosmeticManager manager = CosmeticManager.get(world);
        PacketByteBuf buf = manager.toBuf();

        for (ServerPlayerEntity p : world.getPlayers()) {
            ServerPlayNetworking.send(p, UpdateCosmeticsS2CPacket.ID, buf);
        }
    }

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
        UpdateCosmeticsS2CPacket.register();
    }
}
