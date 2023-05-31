package de.marchinrichs.sspkata.sspbot.round;

import de.marchinrichs.sspkata.sspapi.round.Call;
import de.marchinrichs.sspkata.sspapi.round.CallResponse;
import de.marchinrichs.sspkata.sspapi.round.RoundResult;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Path("/round")
@Component
@RequiredArgsConstructor
public class RoundResource {

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
