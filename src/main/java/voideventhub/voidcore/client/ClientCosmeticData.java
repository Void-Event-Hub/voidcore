package voideventhub.voidcore.client;

import voideventhub.voidcore.data.cosmetic.Cosmetic;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class ClientCosmeticData {

    private static HashMap<UUID, Cosmetic> playerCosmetics = new HashMap<>();

    public static void setPlayerCosmetics(HashMap<UUID, Cosmetic> playerCosmetics) {
        ClientCosmeticData.playerCosmetics = playerCosmetics;
    }

    public static HashMap<UUID, Cosmetic> getPlayerCosmetics() {
        return playerCosmetics;
    }

    public static Optional<Cosmetic> getPlayerCosmetic(UUID uuid) {
        return Optional.ofNullable(playerCosmetics.get(uuid));
    }
}
