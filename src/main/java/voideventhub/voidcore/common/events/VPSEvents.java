package voideventhub.voidcore.common.events;

import voideventhub.voidcore.common.events.player.*;
import voideventhub.voidcore.common.events.server.CheckDBConnectionOnStartupEvent;
import voideventhub.voidcore.common.events.server.SpawnPlayerParticleTrailEvent;

public class VPSEvents {

    public static void register() {
        CheckDBConnectionOnStartupEvent.register();
        SpawnPlayerParticleTrailEvent.register();
        TrackPlayerDisconnectEvent.register();
        TrackPlayerJoinEvent.register();
        PlayerBanOnDeathEvent.register();
        AssignToTeamOnJoinEvent.register();
        TpPlayerToTeamSpawnOnDeathEvent.register();
    }

}
