package voideventhub.voidcore.client.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import voideventhub.voidcore.data.cosmetic.Cosmetic;

import java.util.HashMap;
import java.util.UUID;

@Environment(EnvType.CLIENT)
@Deprecated
public class ClientCosmeticData {

    private static HashMap<UUID, Cosmetic> playerCosmetics = new HashMap<>();

    public static void setPlayerCosmetics(HashMap<UUID, Cosmetic> playerCosmetics) {
        ClientCosmeticData.playerCosmetics = playerCosmetics;
    }

}
