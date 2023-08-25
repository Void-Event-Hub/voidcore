package voideventhub.voidcore.common.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import voideventhub.voidcore.common.VoidCore;

import java.util.List;
import java.util.Map;

public class VCItems {

    public static final ArmorItem AMETHYST_HELMET = (ArmorItem) registerItem("amethyst_helmet",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem AMETHYST_CHESTPLATE = (ArmorItem) registerItem("amethyst_chestplate",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem AMETHYST_LEGGINGS = (ArmorItem) registerItem("amethyst_leggings",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem AMETHYST_BOOTS = (ArmorItem) registerItem("amethyst_boots",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem KNIGHT_HELMET = (ArmorItem) registerItem("knight_helmet",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem KNIGHT_CHESTPLATE = (ArmorItem) registerItem("knight_chestplate",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem KNIGHT_LEGGINGS = (ArmorItem) registerItem("knight_leggings",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem KNIGHT_BOOTS = (ArmorItem) registerItem("knight_boots",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VoidCore.MOD_ID, name), item);
    }

    public static void register() {
        VoidCore.LOGGER.info("Registering items");
    }

    public static final Map<CosmeticType, List<ArmorItem>> COSMETICS = Map.of(
            CosmeticType.NONE, List.of(),
            CosmeticType.KNIGHT, List.of(
                    KNIGHT_HELMET,
                    KNIGHT_CHESTPLATE,
                    KNIGHT_LEGGINGS,
                    KNIGHT_BOOTS
            ),
            CosmeticType.AMETHYST, List.of(
                    AMETHYST_HELMET,
                    AMETHYST_CHESTPLATE,
                    AMETHYST_LEGGINGS,
                    AMETHYST_BOOTS
            )
    );

    public enum CosmeticType {
        KNIGHT("knight"),
        AMETHYST("amethyst"),
        NONE("none");

        private final String id;

        CosmeticType(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
    }

}
