package voideventhub.voidcore.common.events;

public class VPSEvents {

    public static void register() {
        ServerStartupEvent.register();
        ServerTickEvent.register();
        PlayerDisconnectEvent.register();
        PlayerJoinEvent.register();
        AfterDeathEvent.register();
    }

}
