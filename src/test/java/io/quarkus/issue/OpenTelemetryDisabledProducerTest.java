package io.quarkus.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import javax.inject.Inject;

import io.opentelemetry.api.OpenTelemetry;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(OpenTelemetryDisabledProducerTest.OpenTelemetryDisabledProfile.class)
class OpenTelemetryDisabledProducerTest {

    @Inject
    OpenTelemetry openTelemetry;

    @Test
    void getOpenTelemetry() {
        assertNotNull(openTelemetry);
        assertEquals("NoopTextMapPropagator", openTelemetry.getPropagators().getTextMapPropagator().getClass().getSimpleName());
    }

    public static class OpenTelemetryDisabledProfile implements QuarkusTestProfile {
        public Map<String, String> getConfigOverrides() {
            return Map.of("quarkus.opentelemetry.enabled", "false");
        }
    }
}