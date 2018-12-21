package io.helidon.bugs.mpapp;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 
 * @author rgrecour
 */
@Path("/")
@RequestScoped
public class HelloResource {

    private final WebTarget target;

    public HelloResource() {
        Client client = ClientBuilder.newClient();
        target = client.target("http://localhost:9090");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getDefaultMessage() {
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        Message message = response.readEntity(Message.class);
        return Json.createObjectBuilder()
                .add("message", message.getMessage())
                .build();
    }
}
