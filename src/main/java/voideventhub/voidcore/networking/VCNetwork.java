package voideventhub.voidcore.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import voideventhub.voidcore.data.cosmetic.CosmeticManager;
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

    public static void registerC2SPackets() {
    }

    public static void registerS2CPackets() {
        UpdateCosmeticsS2CPacket.register();
    }
}
