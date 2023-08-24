package voideventhub.voidcore.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.repository.models.member.DiscordMember;
import voideventhub.voidcore.repository.models.EventApplication;
import voideventhub.voidcore.repository.models.member.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class MongoDbRepository implements Repository {

    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new MongoDbRepository();
        }
        return instance;
    }

    private final String voidDbName = "voideventhub-1076531218830610502";
    private final String connectionString;
    private final MongoClientSettings clientSettings;

    private MongoDbRepository() {
        this.connectionString = "mongodb+srv://" +
                VoidCore.CONFIG.mongodbUsername() +
                ":" +
                VoidCore.CONFIG.mongodbPassword() +
                "@cluster0.t7aelwd.mongodb.net/?retryWrites=true&w=majority";
        CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        CodecRegistry pojoCodecRegistry = fromProviders(
                PojoCodecProvider.builder().automatic(true).build(),
                new LongCodecProvider()
        );
        CodecRegistry combinedRegistry = CodecRegistries.fromRegistries(codecRegistry, pojoCodecRegistry);
        this.clientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .codecRegistry(combinedRegistry)
                .build();
    }

    public boolean validConnection() {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);
            database.getCollection("server-activity").find().first();
            return true;
        } catch (Exception e) {
            VoidCore.LOGGER.info("Failed to connect to MongoDB");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean writePlayerAction(PlayerAction playerAction, UUID uuid) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);

            database.getCollection("server-activity")
                    .insertOne(
                            new Document("player_id", uuid.toString())
                                    .append("action", playerAction.toString())
                                    .append("timestamp", System.currentTimeMillis())
                    );
            return true;
        } catch (Exception e) {
            VoidCore.LOGGER.info("Failed to write player action to database. player: {}, action: {}", uuid, playerAction);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UUID> getPatrons() {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);

            var patrons = database.getCollection("patrons").find();

            return patrons.map(patron -> (long) patron.get("user_id"))
                    .map(
                            patronUserId -> (String) database.getCollection("minecraft-profiles")
                                    .find(new Document("user_id", patronUserId))
                                    .first()
                                    .get("uuid")
                    )
                    .map(UUID::fromString)
                    .into(new ArrayList<>());
        } catch (Exception e) {
            VoidCore.LOGGER.info("Failed to get patrons from database");
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Member> getDiscordMembers() {
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);
            MongoCollection<DiscordMember> members = database.getCollection("members", DiscordMember.class);
            return members.find().into(new ArrayList<>());
        } catch (Exception e) {
            VoidCore.LOGGER.info("Failed to get Discord Members from database");
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    @Nullable
    public Member getMember(@NotNull UUID uuid) {
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);
            MongoCollection<DiscordMember> members = database.getCollection("members", DiscordMember.class);
            return members.find(new Document("minecraft.uuid", uuid.toString())).first();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UUID> getAcceptedApplicants() {
        // TODO: Implement
        return null;
    }

    @Override
    public List<EventApplication> getApplications() {
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);
            MongoCollection<EventApplication> applications = database.getCollection("applications", EventApplication.class);
//            applications.find().forEach(app -> System.out.println(app.get("user_id").getClass()));
            return applications.find().into(new ArrayList<>());
        } catch (Exception e) {
            VoidCore.LOGGER.info("Failed to get Event Applications from database");
            e.printStackTrace();
            return List.of();
        }
    }
}
