package sri.bol.com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
public class PitsGameRest {

    private static final String PLAYER1 = "player1";
    private static final String PLAYER2 = "player2";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{player}/{startPosition}")
    public Response playerMove(@PathParam("player") final String player, @PathParam("startPosition") final int startPosition) {
        if (!validateInput(player, startPosition)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (PLAYER1.equalsIgnoreCase(player)) {
            PitsGame.startPlayerOne(startPosition);
        } else {
            PitsGame.startPlayerTwo(startPosition);
        }
        return Response.status(Response.Status.OK)
                .entity(new PitsGameModel(PitsGame.getPitsOfPlayer1(), PitsGame.getPitsOfPlayer2(), PitsGame.isPlayerWon(), PitsGame.isExtraMove())).build();
    }

    private boolean validateInput(final String player, final int startPosition) {
        return !(player == null || !(PLAYER1.equalsIgnoreCase(player) || PLAYER2.equalsIgnoreCase(player))) && !(startPosition < 0 || startPosition > 6);
    }
}
