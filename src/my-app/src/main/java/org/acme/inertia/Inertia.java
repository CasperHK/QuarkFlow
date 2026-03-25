package org.acme.inertia;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import io.quarkus.qute.Location;

@ApplicationScoped
public class Inertia {

    @Inject
    ObjectMapper objectMapper;

    @Location("inertia")
    Template inertiaTemplate;

    @ConfigProperty(name = "quarkflow.inertia.version", defaultValue = "1")
    String assetVersion;

    @ConfigProperty(name = "quarkflow.vite.dev-server", defaultValue = "http://localhost:5173")
    String viteDevServer;

    @ConfigProperty(name = "quarkflow.vite.dev", defaultValue = "true")
    boolean viteDev;

    public Response render(String component, Map<String, Object> props, UriInfo uriInfo, String inertiaHeader) {
        String url = uriInfo.getRequestUri().getPath();

        InertiaPage page = new InertiaPage(component, props, url, assetVersion);

        // If the request has X-Inertia header, return JSON (XHR partial reload)
        if (inertiaHeader != null) {
            return Response.ok(page, MediaType.APPLICATION_JSON_TYPE)
                    .header("X-Inertia", "true")
                    .header("Vary", "X-Inertia")
                    .build();
        }

        // Otherwise, return full HTML page
        String pageJson;
        try {
            pageJson = objectMapper.writeValueAsString(page);
        } catch (JsonProcessingException e) {
            return Response.serverError().entity("Failed to serialize page data").build();
        }

        String entryScript;
        if (viteDev) {
            entryScript = viteDevServer + "/src/app.js";
        } else {
            entryScript = "/assets/src/app.js";
        }

        TemplateInstance html = inertiaTemplate
                .data("pageJson", pageJson)
                .data("entryScript", entryScript)
                .data("viteDev", viteDev)
                .data("viteDevServer", viteDevServer);

        return Response.ok(html.render(), MediaType.TEXT_HTML_TYPE)
                .header("Vary", "X-Inertia")
                .build();
    }
}
