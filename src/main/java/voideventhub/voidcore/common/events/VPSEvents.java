package voideventhub.voidcore.common.events;

public class VPSEvents {

    public static void register() {
        CheckDBConnectionOnStartupEvent.register();
        SpawnPlayerParticleTrailEvent.register();
        TrackPlayerDisconnectEvent.register();
        TrackPlayerJoinEvent.register();
        PlayerBanOnDeathEvent.register();
    }

}
