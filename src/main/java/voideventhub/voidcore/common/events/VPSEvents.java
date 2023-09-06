package voideventhub.voidcore.common.events;

import voideventhub.voidcore.common.events.player.AssignToTeamOnJoinEvent;
import voideventhub.voidcore.common.events.player.PlayerBanOnDeathEvent;
import voideventhub.voidcore.common.events.player.TrackPlayerDisconnectEvent;
import voideventhub.voidcore.common.events.player.TrackPlayerJoinEvent;
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
    }

}
