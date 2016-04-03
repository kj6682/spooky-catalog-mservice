package spooky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        if (!spookyFeatureConfig.isAction1Enabled()) {
            throw new UnsupportedOperationException();
        }

        return "action ONE is enabled.";
    }

    @RequestMapping("/execute/action2")
    String executeActionTwo() {
        if (!spookyFeatureConfig.isAction2Enabled()) {
            throw new UnsupportedOperationException();
        }

        return "action TWO is enabled.";
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public void unsupportedException(HttpServletResponse response)
            throws IOException {
        response.sendError(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "This feature is currently unavailable"
        );
    }

    @ExceptionHandler(Exception.class)
    public void handleGenericException(
            HttpServletResponse response,
            Exception e) throws IOException {
        String msg = "There was an error processing your request: " + e.getMessage();
        response.sendError(
                HttpStatus.BAD_REQUEST.value(),
                msg
        );
    }
}
