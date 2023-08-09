package voideventhub.voidcore.networking.packets;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import voideventhub.voidcore.VoidCore;
import voideventhub.voidcore.client.data.ClientCosmeticData;
import voideventhub.voidcore.data.cosmetic.Cosmetic;

import java.util.HashMap;
import java.util.UUID;

public class UpdateCosmeticsS2CPacket {

    public static final Identifier ID = new Identifier(VoidCore.MOD_ID, "update_cosmetics");

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(ID, (client, handler, buf, responseSender) -> {
            NbtCompound nbt = buf.readNbt();
            HashMap<UUID, Cosmetic> playerCosmetics = new HashMap<>();

            for(String uuid : nbt.getKeys()) {
                playerCosmetics.put(UUID.fromString(uuid), new Cosmetic(nbt.getCompound(uuid)));
            }

            client.execute(() -> {
                ClientCosmeticData.setPlayerCosmetics(playerCosmetics);
                System.out.println("Received cosmetic packet");
            });
        });
    }

}
