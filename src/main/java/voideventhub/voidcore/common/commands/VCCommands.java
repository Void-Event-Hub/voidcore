package voideventhub.voidcore.common.commands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class VCCommands {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
        });
    }
}
