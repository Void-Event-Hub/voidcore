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
import voideventhub.voidcore.repository.models.event.Event;
import voideventhub.voidcore.repository.models.member.MemberPOJO;
import voideventhub.voidcore.repository.models.member.Member;
import voideventhub.voidcore.repository.models.playerAction.PlayerActionType;

import java.util.UUID;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class MongoDbRepository implements Repository {

    //TODO: make repository use one instance of mongoClient

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
    public boolean writePlayerAction(@NotNull PlayerActionType playerActionType, @NotNull UUID uuid) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);

            database.getCollection("server-activity")
                    .insertOne(
                            new Document("player_id", uuid.toString())
                                    .append("action", playerActionType.toString())
                                    .append("timestamp", System.currentTimeMillis())
                    );
            return true;
        } catch (Exception e) {
            VoidCore.LOGGER.info("Failed to write player action to database. player: {}, action: {}", uuid, playerActionType);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Nullable
    public Member getMember(@NotNull UUID uuid) {
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase database = mongoClient.getDatabase(voidDbName);
            MongoCollection<MemberPOJO> members = database.getCollection("members", MemberPOJO.class);
            return members.find(new Document("minecraft.uuid", uuid.toString())).first();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public @Nullable Event getCurrentEvent() {
        // TODO: implement
        return null;
    }

    @Override
    public boolean wasAccepted(@NotNull UUID uuid, @NotNull String eventId) {
        // TODO: implement
        return false;
    }

}
