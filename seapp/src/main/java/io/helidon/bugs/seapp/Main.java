package io.helidon.bugs.seapp;

import java.io.IOException;
import java.util.logging.LogManager;
import java.net.InetAddress;
import javax.json.Json;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.json.JsonSupport;

/**
 * 
 * @author rgrecour
 */
public final class Main {

    private Main() { }

    private static void handler(final ServerRequest req, final ServerResponse res) {
        res.send(Json.createObjectBuilder()
                .add("message", "hello")
                .build());
    }

    public static void main(final String[] args) throws IOException {

        LogManager.getLogManager().readConfiguration(
                Main.class.getResourceAsStream("/logging.properties"));


        Routing routing = Routing.builder()
                .register(JsonSupport.get())
                .get("/", Main::handler)
                .build();

        ServerConfiguration serverConfig = ServerConfiguration.builder()
                .bindAddress(InetAddress.getByName("0.0.0.0"))
                .port(9090)
                .build();

        WebServer server = WebServer.create(serverConfig, routing);

        server.start().thenAccept(ws -> {
            System.out.println(
                    "WEB server is up! http://localhost:" + ws.port());
        });

        server.whenShutdown().thenRun(()
                -> System.out.println("WEB server is DOWN. Good bye!"));
    }
}
