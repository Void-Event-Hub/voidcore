package voideventhub.voidcore.repository.models.member;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.List;

public class Discord {

    @BsonProperty("id")
    private long id;

    @BsonProperty("username")
    private String username;

    @BsonProperty("joined_at")
    private Date joinedAt;

    @BsonProperty("roles")
    private List<Role> roles;

    public Discord() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Discord{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", joinedAt=" + joinedAt +
                ", roles=" + roles +
                '}';
    }
}
