# Minimal working example of a Quarkus configuration issue.

In this case I use manual instrumentation for OpenTelemetry because the `@WithSpan`-annotation does not seem to work for `private` or `abstract` methods.
To access the OpenTelemetry API I `@Inject` these beans in my project.

Injecting the `OpenTelemetry` object fails when configured with `quarkus.opentelemetry.enabled=false`. It will throw an `UnsatisfiedResolutionException`.

However, if I provide a new producer with the exact same implementation the issue solved. 
The produced `OpenTelemetry` instance contains some no-op classes so that seems to be a safe way to disable OpenTelemetry, in line with its official documentation:

> [OpenTelemetry - Tracing API](https://opentelemetry.io/docs/reference/specification/trace/api/#behavior-of-the-api-in-the-absence-of-an-installed-sdk)   
> In general, in the absence of an installed SDK, the Trace API is a “no-op” API