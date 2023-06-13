package de.marchinrichs.sspkata.sspservice.controller;

import de.marchinrichs.sspkata.sspapi.model.game.Game;
import de.marchinrichs.sspkata.sspservice.service.GameService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Path("/game")
@Component
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GET
    @Path("play/{rounds}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(@PathParam("rounds") int rounds) {

        List<Game> game = gameService.play(rounds);

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(game).build();
    }
}
