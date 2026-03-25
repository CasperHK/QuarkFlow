package org.acme.controllers;

import java.util.Map;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.acme.inertia.Inertia;

@Path("/")
public class HomeController {

    @Inject
    Inertia inertia;

    @GET
    public Response index(@Context UriInfo uriInfo, @HeaderParam("X-Inertia") String inertiaHeader) {
        return inertia.render("Home", Map.of("greeting", "Hello from QuarkFlow!"), uriInfo, inertiaHeader);
    }
}
