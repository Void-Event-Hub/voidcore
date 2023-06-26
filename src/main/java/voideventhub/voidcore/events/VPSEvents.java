package voideventhub.voidcore.events;

public class VPSEvents {

    public static void register() {
        ServerTickEvent.register();
        ServerLoadEvent.register();
        EntityUnloadEvent.register();
        EntityLoadEvent.register();
    }

}
