package spooky;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class CatalogRestController {

    @Autowired
    SpookyFeatureConfig spookyFeatureConfig;


    @RequestMapping("/message")
    String getMessage() {
        return spookyFeatureConfig.getMessage();
    }

    @RequestMapping("/execute/action1")
    @JsonSerialize(using = LocalDateSerializer.class)
    Item executeActionOne() {
        if (!spookyFeatureConfig.isAction1Enabled()) {
            throw new UnsupportedOperationException();
        }

        return new Item("1", "action ONE is enabled.", LocalDateTime.MAX);
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
