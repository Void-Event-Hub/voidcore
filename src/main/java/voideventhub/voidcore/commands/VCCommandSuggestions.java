package voideventhub.voidcore.commands;

import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.EquipmentSlot;
import voideventhub.voidcore.data.cosmetic.CosmeticType;

import java.util.concurrent.CompletableFuture;

public class VCCommandSuggestions {

    public static CompletableFuture<Suggestions> cosmetics(SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(enumToStringArray(CosmeticType.values()), builder);
    }

    public static CompletableFuture<Suggestions> equipmentSlots(SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(enumToStringArray(EquipmentSlot.values()), builder);
    }

    private static String[] enumToStringArray(Enum<?>[] enums) {
        String[] strings = new String[enums.length];
        for (int i = 0; i < enums.length; i++) {
            strings[i] = enums[i].name();
        }
        return strings;
    }

}
