package voideventhub.voidcore.repository.models.member;


import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;
import java.util.UUID;

public class MemberPOJO implements Member {

    public MemberPOJO() {
    }

    @BsonProperty("discord")
    private Discord discord;

    @BsonProperty("minecraft")
    private Minecraft minecraft;

    public Discord getDiscord() {
        return discord;
    }

    public Minecraft getMinecraft() {
        return minecraft;
    }

    public void setDiscord(Discord discord) {
        this.discord = discord;
    }

    public void setMinecraft(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @Override
    public String toString() {
        return "DiscordMember{" +
                "discord=" + discord +
                ", minecraft=" + minecraft +
                '}';
    }

    @Override
    public UUID getMinecraftUuid() {
        return UUID.fromString(this.minecraft.getUuid());
    }

    @Override
    public long getDiscordId() {
        return this.discord.getId();
    }

    @Override
    public String getDiscordUsername() {
        return this.discord.getUsername();
    }

    @Override
    public boolean hasConfirmedMinecraft() {
        return this.minecraft.isConfirmed();
    }

    @Override
    public List<Role> getRoles() {
        return this.discord.getRoles();
    }

    @Override
    public boolean isPatron() {
        return this.discord.getRoles().stream().anyMatch(role -> role.getName().equals("Patron"));
    }

    @Override
    public boolean isServerBooster() {
        return this.discord.getRoles().stream().anyMatch(role -> role.getName().equals("Server Booster"));
    }
}
