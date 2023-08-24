package voideventhub.voidcore.repository.models.application;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;

public class ApplicationPOJO implements Application {

    @BsonProperty("user_id")
    private long userId;

    @BsonProperty("event_id")
    private String eventId;

    @BsonProperty("reason_to_play")
    private String reasonToPlay;

    @BsonProperty("date")
    private Date date;

    @BsonProperty("status")
    private String status;

    @BsonProperty("reviewer_user_id")
    private long reviewerUserId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getReasonToPlay() {
        return reasonToPlay;
    }

    public void setReasonToPlay(String reasonToPlay) {
        this.reasonToPlay = reasonToPlay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(long reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    @Override
    public boolean isAccepted() {
        return this.status.equals("accepted");
    }

    @Override
    public String toString() {
        return "ApplicationPOJO{" +
                "userId=" + userId +
                ", eventId='" + eventId + '\'' +
                ", reasonToPlay='" + reasonToPlay + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", reviewerUserId=" + reviewerUserId +
                '}';
    }
}
