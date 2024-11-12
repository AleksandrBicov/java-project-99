package hexlet.code.sentry;

import io.sentry.spring.jakarta.EnableSentry;
import org.springframework.context.annotation.Configuration;

@EnableSentry(dsn = "https://8fd07534b55de681f8796b78edc2dde8@o4508285739794432.ingest.de.sentry.io/4508285751853137")
@Configuration
class SentryConfiguration {
}