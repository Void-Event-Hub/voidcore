package voideventhub.voidcore.commands;

import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.EquipmentSlot;
import voideventhub.voidcore.data.cosmetic.CosmeticType;

import java.util.concurrent.CompletableFuture;

public class VCCommandSuggestions {

    /**
     * Suggests cosmetics
     */
    public static CompletableFuture<Suggestions> cosmetics(SuggestionsBuilder builder) {
        String[] options = enumToStringArray(CosmeticType.values());
        return CommandSource.suggestMatching(options, builder);
    }

    /**
     * Suggests equipment slots
     */
    public static CompletableFuture<Suggestions> equipmentSlots(SuggestionsBuilder builder) {
        String[] options = enumToStringArray(EquipmentSlot.values());
        return CommandSource.suggestMatching(options, builder);
    }

    private static String[] enumToStringArray(Enum<?>[] enums) {
        String[] strings = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            strings[i] = enums[i].name();
        }
        return strings;
    }

}
