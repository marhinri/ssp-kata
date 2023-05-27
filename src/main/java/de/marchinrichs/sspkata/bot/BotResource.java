package de.marchinrichs.sspkata.bot;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Path("/bots")
@Component
@RequiredArgsConstructor
public class BotResource {

    private final BotService botService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBot(Bot bot) {

        BotId botId = botService.addBot(bot);

        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(botId)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBots() {
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(new ArrayList<>()).build();
    }

    /*
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBots(UUID uuid) {
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(new BotInfo()).build();
    }*/
}
