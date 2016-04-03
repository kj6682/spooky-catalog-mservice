package spooky;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by luigi on 03/04/16.
 */
@Configuration
public class SpookyFeatureConfig {

    @Value("${features.action1}")
    Boolean action1;

    @Value("${features.action2}")
    Boolean action2;

    @Value("${message}")
    private String message;

    public boolean isAction1Enabled(){
        return action1;
    }

    public boolean isAction2Enabled(){
        return action2;
    }

    public String getMessage(){
        return message;
    }

}
