package de.marchinrichs.sspkata.sspservice.bot;

import de.marchinrichs.sspkata.sspapi.model.bot.BotId;
import de.marchinrichs.sspkata.sspapi.model.bot.Bot;
import de.marchinrichs.sspkata.sspapi.model.bot.BotWrite;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Path("/bots")
@Component
@RequiredArgsConstructor
public class BotResource {

    private final BotService botService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBot(BotWrite botWrite) {

        BotId botId = botService.addBot(botWrite);

        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(botId)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBots() {
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(botService.getBots()).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBot(@PathParam("id") UUID uuid) {
        Bot bot;

        try {
            bot = botService.getBot(uuid);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(bot).build();
    }
}
