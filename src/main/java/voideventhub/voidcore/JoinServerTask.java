package voideventhub.voidcore;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import net.minecraft.server.network.ServerPlayerEntity;
import org.bson.Document;

public class JoinServerTask extends Thread {

    private final ServerPlayerEntity entity;
    private final String task;

    public JoinServerTask(ServerPlayerEntity entity, String task) {
        this.entity = entity;
        this.task = task;
    }

    public void run() {
        String connectionString = "";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("voideventhub-1076531218830610502");

        database.getCollection("server-activity")
                .insertOne(
                        new Document("player_id", entity.getUuidAsString())
                                .append("action", this.task)
                                .append("timestamp", System.currentTimeMillis())
                );
    }
}
