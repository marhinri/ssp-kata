package de.marchinrichs.sspkata.bot;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Path("/bots")
@Component
@RequiredArgsConstructor
public class BotController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBot(Bot bot) {
        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(
                        BotId.builder().id(UUID.randomUUID()).build())
                .build();
    }
}
