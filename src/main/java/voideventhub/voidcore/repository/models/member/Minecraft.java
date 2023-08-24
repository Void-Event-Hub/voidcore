package voideventhub.voidcore.repository.models.member;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Minecraft {

    public Minecraft() {
    }

    @BsonProperty("uuid")
    private String uuid;

    @BsonProperty("confirmed")
    private boolean confirmed;

    public String getUuid() {
        return uuid;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Minecraft{" +
                "uuid='" + uuid + '\'' +
                ", confirmed=" + confirmed +
                '}';
    }
}
