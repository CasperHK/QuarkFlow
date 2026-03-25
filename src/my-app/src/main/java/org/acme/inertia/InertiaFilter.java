package org.acme.inertia;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Handles Inertia.js protocol requirements:
 * - Checks X-Inertia-Version header and returns 409 on mismatch (forces full page reload).
 */
@Provider
public class InertiaFilter implements ContainerRequestFilter {

    @ConfigProperty(name = "quarkflow.inertia.version", defaultValue = "1")
    String assetVersion;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String inertiaHeader = requestContext.getHeaderString("X-Inertia");
        if (inertiaHeader == null) {
            return;
        }

        String clientVersion = requestContext.getHeaderString("X-Inertia-Version");
        if (clientVersion != null && !clientVersion.equals(assetVersion)) {
            // Version mismatch: force full page reload per Inertia protocol
            String url = requestContext.getUriInfo().getRequestUri().toString();
            requestContext.abortWith(
                    Response.status(409)
                            .header("X-Inertia-Location", url)
                            .build());
        }
    }
}
