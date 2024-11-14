package hexlet.code.controller;

import io.sentry.Sentry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-error")
    public void testError() {
        try {
            throw new Exception("This is a test error.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
    }
}
