package de.marchinrichs.sspkata.sspbot.controller;

import de.marchinrichs.sspkata.sspapi.model.round.Call;
import de.marchinrichs.sspkata.sspapi.model.round.CallResponse;
import de.marchinrichs.sspkata.sspapi.model.round.RoundResult;
import de.marchinrichs.sspkata.sspbot.service.RoundService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Path("/round")
@Component
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;

    @POST
    @Path("call")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response call(Call call) {

        CallResponse callResponse = roundService.call(call);

        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(callResponse)
                .build();
    }

    @POST
    @Path("{roundId}/result")
    public Response addRoundResult(
            @PathParam("roundId") UUID uuid, RoundResult result) {

        roundService.addRoundResult(uuid, result);

        return Response
                .status(Response.Status.OK.getStatusCode())
                .build();
    }

}
