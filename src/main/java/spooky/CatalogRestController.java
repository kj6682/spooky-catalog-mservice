package spooky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class CatalogRestController {

    @Autowired
    SpookyFeatureConfig spookyFeatureConfig;


    @RequestMapping("/message")
    String getMessage() {
        return spookyFeatureConfig.getMessage();
    }

    @RequestMapping("/execute/action1")
    String executeActionOne() {
        if(spookyFeatureConfig.isAction1Enabled()){
            return "action ONE is enabled.";
        }
        return "sorry mate, no party for you.";
    }

    @RequestMapping("/execute/action2")
    String executeActionTwo() {
        if(spookyFeatureConfig.isAction2Enabled()){
            return "action TWO is enabled.";
        }
        return "sorry mate, no party for you.";
    }
}
