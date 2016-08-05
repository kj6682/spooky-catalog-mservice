package spooky;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luigi on 16/04/16.
 */
@Configuration
public class MongoConfiguration {


    @Value("${mongodb.uri}")
    String mongoURIString;

    @Value("${mongodb.port}")
    String mongoPort;


    @Bean
    public MongoClient mongoClient(){
        String mongoUri = new StringBuilder(mongoURIString).append(":").append(mongoPort).toString();
        return new MongoClient(new MongoClientURI(mongoUri));
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
    public ItemService catalogService(){
        return new ItemService(catalogCollection());
    }

}
