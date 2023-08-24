package voideventhub.voidcore.repository.models.event;

import java.util.Date;
import java.util.List;

/**
 * Represents an event organized by the Void Event Hub
 */
public interface Event {

    /**
     * @return id of the event
     */
    String getId();

    /**
     * @return name of the event
     */
    String getName();

    /**
     * @return description of the event
     */
    String getDescription();

    /**
     * @return dates of the event
     */
    List<Date> getDates();

    /**
     * @return id given to members who will participate in the event
     */
    long getParticipationRoleId();

    /**
     * @return whether applications are still being accepted
     */
    boolean isApplicationsOpen();

}
