package voideventhub.voidcore;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetPatronsTask extends Thread {

    @Override
    public void run() {
        String connectionString = "mongodb+srv://user:JLJI9iSQuYi6rvhG@cluster0.t7aelwd.mongodb.net/?retryWrites=true&w=majority";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("voideventhub-1076531218830610502");

        var patrons = database.getCollection("patrons").find();

        List<UUID> patronUuids = patrons.map(patron -> (long) patron.get("user_id"))
                .map(
                        patronUserId -> (String) database.getCollection("minecraft-profiles")
                                .find(new Document("user_id", patronUserId))
                                .first()
                                .get("uuid")
                )
                .map(UUID::fromString)
                .into(new ArrayList<>());

        Patrons.setPatrons(patronUuids);
    }
}
