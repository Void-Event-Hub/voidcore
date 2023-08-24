package voideventhub.voidcore.repository.models.member;

import net.minecraft.nbt.NbtCompound;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Role {

    public Role() {
    }

    public Role(NbtCompound nbt) {
        this.id = nbt.getLong("id");
        this.name = nbt.getString("name");
    }

    @BsonProperty("id")
    private long id;

    @BsonProperty("name")
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NbtCompound write(NbtCompound nbt) {
        nbt.putLong("id", this.id);
        nbt.putString("name", this.name);
        return nbt;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
