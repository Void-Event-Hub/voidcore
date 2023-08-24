package voideventhub.voidcore.repository.models.event;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.List;

public class EventPOJO implements Event {

    @BsonProperty("id")
    private String id;

    @BsonProperty("name")
    private String name;

    @BsonProperty("description")
    private String description;

    @BsonProperty("dates")
    private List<Date> dates;

    @BsonProperty("participation_role_id")
    private long participationRoleId;

    @BsonProperty("is_applications_open")
    private boolean isApplicationsOpen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public long getParticipationRoleId() {
        return participationRoleId;
    }

    public void setParticipationRoleId(long participationRoleId) {
        this.participationRoleId = participationRoleId;
    }

    public boolean isApplicationsOpen() {
        return isApplicationsOpen;
    }

    public void setApplicationsOpen(boolean applicationsOpen) {
        isApplicationsOpen = applicationsOpen;
    }

    @Override
    public String toString() {
        return "EventPOJO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dates=" + dates +
                ", participationRoleId=" + participationRoleId +
                ", isApplicationsOpen=" + isApplicationsOpen +
                '}';
    }
}
