package voideventhub.voidcore.common.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CosmeticProvider implements ArmorCosmeticProvider {

    private static CosmeticProvider instance;

    public static CosmeticProvider getInstance() {
        if (instance == null) {
            instance = new CosmeticProvider();
        }
        return instance;
    }

    private final Map<ArmorMaterial, Map<ArmorMaterial, Map<EquipmentSlot, ArmorItem>>> cosmetics;

    @Override
    public @Nullable ArmorItem getArmorCosmetic(EquipmentSlot slot, ArmorMaterial cosmetic, ArmorMaterial currentlyEquipped) {
        Map<ArmorMaterial, Map<EquipmentSlot, ArmorItem>> materialVersions = cosmetics.getOrDefault(cosmetic, Map.of());
        Map<EquipmentSlot, ArmorItem> cosmetics = materialVersions.getOrDefault(currentlyEquipped, materialVersions.get(cosmetic));
        return cosmetics.get(slot);
    }

    @Override
    public void setArmorCosmetic(EquipmentSlot slot, ArmorMaterial cosmetic, ArmorMaterial currentlyEquipped, ArmorItem item) {
        this.cosmetics.computeIfAbsent(cosmetic, k -> new HashMap<>())
                .computeIfAbsent(currentlyEquipped, k -> new HashMap<>())
                .put(slot, item);
    }

    @Override
    public List<ArmorMaterial> getArmorCosmeticMaterials() {
        return this.cosmetics.keySet().stream().toList();
    }

    private CosmeticProvider() {
        this.cosmetics = new HashMap<>();
    }
}
