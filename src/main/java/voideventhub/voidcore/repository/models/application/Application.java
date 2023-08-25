package voideventhub.voidcore.repository.models.application;

import java.util.Date;

/**
 * Represents an application of a member to play an event
 */
public interface Application {

    /**
     * @return the id of the user the application is for
     */
    long getUserId();

    /**
     * @return the id of the event the application is for
     */
    String getEventId();

    /**
     * @return the reason given by the user to play the event
     */
    String getReasonToPlay();

    /**
     * @return the date the application was submitted
     */
    Date getDate();

    /**
     * @return the status of the application
     */
    String getStatus();

    /**
     * @return whether the application was accepted or not
     */
    boolean isAccepted();

}
