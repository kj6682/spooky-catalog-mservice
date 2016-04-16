package spooky;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luigi on 16/04/16.
 */
@Configuration
public class MongoConfiguration {

    String mongoURIString = "mongodb://localhost";

    @Bean
    public MongoClient mongoClient(){
        return new MongoClient(new MongoClientURI(mongoURIString));
    }

    @Bean
    public MongoDatabase mongoDatabase(){
        return mongoClient().getDatabase("catalog");
    }

    @Bean
    public MongoCollection<Document> catalogCollection(){
        return mongoDatabase().getCollection("item");
    }

    @Bean
    public CatalogService catalogService(){
        return new CatalogService(catalogCollection());
    }

}
