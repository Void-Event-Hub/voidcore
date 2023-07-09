package voideventhub.voidcore;

import voideventhub.voidcore.repository.MongoDbRepository;
import voideventhub.voidcore.repository.Repository;

public class GetPatronsTask extends Thread {

    @Override
    public void run() {
        Repository repository = MongoDbRepository.getInstance();
        Patrons.setPatrons(repository.getPatrons());
    }
}
