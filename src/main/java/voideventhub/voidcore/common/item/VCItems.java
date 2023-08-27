package voideventhub.voidcore.common.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import voideventhub.voidcore.common.VoidCore;

public class VCItems {

    public static final ArmorItem AMETHYST_HELMET = registerArmorCosmeticItem("amethyst_helmet",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem AMETHYST_CHESTPLATE = registerArmorCosmeticItem("amethyst_chestplate",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem AMETHYST_LEGGINGS = registerArmorCosmeticItem("amethyst_leggings",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem AMETHYST_BOOTS = registerArmorCosmeticItem("amethyst_boots",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    // Knight Armor

    public static final ArmorItem KNIGHT_HELMET = registerArmorCosmeticItem("knight_helmet",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem KNIGHT_CHESTPLATE = registerArmorCosmeticItem("knight_chestplate",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem KNIGHT_LEGGINGS = registerArmorCosmeticItem("knight_leggings",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem KNIGHT_BOOTS = registerArmorCosmeticItem("knight_boots",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    // Iron Knight Armor

    public static final ArmorItem IRON_KNIGHT_HELMET = registerArmorCosmeticItem("iron_knight_helmet",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem IRON_KNIGHT_CHESTPLATE = registerArmorCosmeticItem("iron_knight_chestplate",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem IRON_KNIGHT_LEGGINGS = registerArmorCosmeticItem("iron_knight_leggings",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem IRON_KNIGHT_BOOTS = registerArmorCosmeticItem("iron_knight_boots",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    // Gold Knight Armor

    public static final ArmorItem GOLD_KNIGHT_HELMET = registerArmorCosmeticItem("gold_knight_helmet",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem GOLD_KNIGHT_CHESTPLATE = registerArmorCosmeticItem("gold_knight_chestplate",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem GOLD_KNIGHT_LEGGINGS = registerArmorCosmeticItem("gold_knight_leggings",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem GOLD_KNIGHT_BOOTS = registerArmorCosmeticItem("gold_knight_boots",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    // Diamond Knight Armor

    public static final ArmorItem DIAMOND_KNIGHT_HELMET = registerArmorCosmeticItem("diamond_knight_helmet",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem DIAMOND_KNIGHT_CHESTPLATE = registerArmorCosmeticItem("diamond_knight_chestplate",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem DIAMOND_KNIGHT_LEGGINGS = registerArmorCosmeticItem("diamond_knight_leggings",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem DIAMOND_KNIGHT_BOOTS = registerArmorCosmeticItem("diamond_knight_boots",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    // Netherite Knight Armor

    public static final ArmorItem NETHERITE_KNIGHT_HELMET = registerArmorCosmeticItem("netherite_knight_helmet",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem NETHERITE_KNIGHT_CHESTPLATE = registerArmorCosmeticItem("netherite_knight_chestplate",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem NETHERITE_KNIGHT_LEGGINGS = registerArmorCosmeticItem("netherite_knight_leggings",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem NETHERITE_KNIGHT_BOOTS = registerArmorCosmeticItem("netherite_knight_boots",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    // Leather Knight Armor

    public static final ArmorItem LEATHER_KNIGHT_HELMET = registerArmorCosmeticItem("leather_knight_helmet",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem LEATHER_KNIGHT_CHESTPLATE = registerArmorCosmeticItem("leather_knight_chestplate",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem LEATHER_KNIGHT_LEGGINGS = registerArmorCosmeticItem("leather_knight_leggings",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final ArmorItem LEATHER_KNIGHT_BOOTS = registerArmorCosmeticItem("leather_knight_boots",
            new KnightArmorItem(VCArmorMaterials.KNIGHT, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VoidCore.MOD_ID, name), item);
    }

    private static ArmorItem registerArmorCosmeticItem(String name, ArmorItem item) {
        ArmorItem registered = Registry.register(Registry.ITEM, new Identifier(VoidCore.MOD_ID, name), item);

        ArmorMaterial armorMaterial = item.getMaterial();

        if (name.contains("diamond")) {
            armorMaterial = ArmorMaterials.DIAMOND;
        }
        if (name.contains("gold")) {
            armorMaterial = ArmorMaterials.GOLD;
        }
        if (name.contains("iron")) {
            armorMaterial = ArmorMaterials.IRON;
        }
        if (name.contains("leather")) {
            armorMaterial = ArmorMaterials.LEATHER;
        }
        if (name.contains("netherite")) {
            armorMaterial = ArmorMaterials.NETHERITE;
        }

        ArmorCosmeticRepository provider = CosmeticRepository.getInstance();
        provider.setArmorCosmetic(item.getSlotType(), item.getMaterial(), armorMaterial, item);

        return registered;
    }

    public static void register() {
        VoidCore.LOGGER.info("Registering items");
    }

}
