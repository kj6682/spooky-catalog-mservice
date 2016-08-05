package spooky;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by luigi on 17/04/16.
 */
public class ItemService {


    @Autowired
    MongoCollection<Document> catalogCollection;

    public ItemService(MongoCollection<Document> catalogCollection) {
        this.catalogCollection = catalogCollection;
    }

    public String addItem(String name) {

        Document item = new Document();

        item.append("name", name)
                .append("bestBefore", new Date());

        catalogCollection.insertOne(item);

        return item.toString();
    }

    public Item findOne() {
        Document document = catalogCollection.find().first();
        return new Item(document.get("_id").toString(),
                document.get("name").toString(),
                document.get("bestBefore").toString());
    }
}
