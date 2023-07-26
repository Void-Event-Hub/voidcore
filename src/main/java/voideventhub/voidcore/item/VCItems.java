package voideventhub.voidcore.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import voideventhub.voidcore.VoidCore;

public class VCItems {

    public static final Item AMETHYST_HELMET = registerItem("amethyst_helmet",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final Item AMETHYST_CHESTPLATE = registerItem("amethyst_chestplate",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final Item AMETHYST_LEGGINGS = registerItem("amethyst_leggings",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));

    public static final Item AMETHYST_BOOTS = registerItem("amethyst_boots",
            new AmethystArmorItem(VCArmorMaterials.AMETHYST, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VoidCore.MOD_ID, name), item);
    }

    public static void register() {
        VoidCore.LOGGER.info("Registering items");
    }

}
