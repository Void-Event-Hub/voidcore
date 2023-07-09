package voideventhub.voidcore.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import voideventhub.voidcore.VoidCore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private MongoDbRepository() {
        this.connectionString = "mongodb+srv://" +
                VoidCore.CONFIG.mongodbUsername() +
                ":" +
                VoidCore.CONFIG.mongodbPassword() +
                "@cluster0.t7aelwd.mongodb.net/?retryWrites=true&w=majority";
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
            return false;
        }
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

    @Override
    public List<UUID> getAcceptedApplicants() {
        // TODO: Implement
        return null;
    }


}
