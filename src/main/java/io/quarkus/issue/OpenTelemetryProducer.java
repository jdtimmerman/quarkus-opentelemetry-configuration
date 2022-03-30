package io.quarkus.issue;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;

@Dependent
public class OpenTelemetryProducer {

    /**
     * <p>
     * Provides the same implementation as {@link io.quarkus.opentelemetry.runtime.OpenTelemetryProducer#getOpenTelemetry} but somehow in the default
     * implementation the bean is disabled when OpenTelemetry is disabled, leading to an
     * {@link javax.enterprise.inject.UnsatisfiedResolutionException}
     * </p>
     * <p>
     * With this override for the producer a NOOP OpenTelemetry is returned when OpenTelemetry is disabled through {@code application.properties}
     * </p>
     * <p>
     * Not tested, but i expect the same behaviour for injecting an {@link Tracer} instance
     * </p>
     */
    @Produces
    @Singleton
    public OpenTelemetry getOpenTelemetry() {
        return GlobalOpenTelemetry.get();
    }
}
