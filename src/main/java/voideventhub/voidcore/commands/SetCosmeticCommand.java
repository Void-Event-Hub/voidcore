package voideventhub.voidcore.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import voideventhub.voidcore.cardinal.CosmeticComponent;
import voideventhub.voidcore.cardinal.VCComponents;
import voideventhub.voidcore.data.cosmetic.CosmeticManager;
import voideventhub.voidcore.data.cosmetic.CosmeticType;
import voideventhub.voidcore.item.VCItems;

public class SetCosmeticCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("setcosmetic")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("slot", StringArgumentType.string())
                        .suggests((context, builder) ->
                                VCCommandSuggestions.equipmentSlots(builder)
                        )
                        .then(CommandManager.argument("cosmetic", StringArgumentType.string())
                                .suggests((context, builder) ->
                                        VCCommandSuggestions.cosmetics(builder)
                                )
                                .executes(SetCosmeticCommand::execute)
                        )
                )
        );
    }

    private static int execute(CommandContext<ServerCommandSource> context) {
        EquipmentSlot slot;
        CosmeticType cosmetic;

        try {
            slot = EquipmentSlot.valueOf(StringArgumentType.getString(context, "slot"));
        } catch (IllegalArgumentException e) {
            context.getSource().sendError(Text.of("Invalid slot"));
            return 0;
        }

        try {
            cosmetic = CosmeticType.valueOf(StringArgumentType.getString(context, "cosmetic"));
        } catch (IllegalArgumentException e) {
            context.getSource().sendError(Text.of("Invalid cosmetic"));
            return 0;
        }

        ServerWorld world = context.getSource().getWorld();
        CosmeticManager manager = CosmeticManager.get(world);

        ServerPlayerEntity player = context.getSource().getPlayer();

        if (player == null) {
            context.getSource().sendError(Text.of("Player not found"));
            return 0;
        }

        CosmeticComponent component = player.getComponent(VCComponents.COSMETIC);
        component.setArmorCosmetic(slot, VCItems.AMETHYST_BOOTS);

        context.getSource().sendFeedback(Text.of("Set " + slot.name() + " to " + cosmetic.name()), false);

        return 1;
    }


}
