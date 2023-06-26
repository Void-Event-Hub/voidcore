package voideventhub.voidcore;

import java.util.List;
import java.util.UUID;

public class Patrons {

    private static List<UUID> patrons;

    public static void setPatrons(List<UUID> patrons) {
        Patrons.patrons = patrons;
    }

    public static boolean isPatron(UUID uuid) {
        return patrons.contains(uuid);
    }

}
