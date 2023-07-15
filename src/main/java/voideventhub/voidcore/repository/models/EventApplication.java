package voideventhub.voidcore.repository.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

public class EventApplication {

    public EventApplication() {
    }

    public EventApplication(ObjectId id, long userId, String eventId, String reasonToPlay, Date date, String status, long reviewerUserId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.reasonToPlay = reasonToPlay;
        this.date = date;
        this.status = status;
        this.reviewerUserId = reviewerUserId;
    }

    @BsonProperty("id")
    private ObjectId id;

    @BsonProperty(value = "user_id")
    private long userId;

    @BsonProperty(value = "event_id")
    private String eventId;

    @BsonProperty(value = "reason_to_play")
    private String reasonToPlay;

    @BsonProperty(value = "date")
    private Date date;

    @BsonProperty(value = "status")
    private String status;

    @BsonProperty(value = "reviewer_user_id")
    private long reviewerUserId;

    public final ObjectId getId() {
        return id;
    }

    public final long getUserId() {
        return userId;
    }

    public final String getEventId() {
        return eventId;
    }

    public final String getReasonToPlay() {
        return reasonToPlay;
    }

    public final String getStatus() {
        return status;
    }

    public final Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setReasonToPlay(String reasonToPlay) {
        this.reasonToPlay = reasonToPlay;
    }

    public void setReviewerUserId(long reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EventApplication{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", eventId='" + eventId + '\'' +
                ", reasonToPlay='" + reasonToPlay + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", reviewerUserId=" + reviewerUserId +
                '}';
    }
}
