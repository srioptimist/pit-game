package sri.bol.com;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static sri.bol.com.PitsGameTestUtil.verifyStateWithoutCheck;
import static sri.bol.com.PitsGameTestUtil.initializePitsWithGivenStones;

public class PitsGameRestTest {

    private PitsGameRest pitsGameRest;

    @Before
    public void setUp() {
        pitsGameRest = new PitsGameRest();
    }

    @Test
    public void testPlayerA() {
        Response response = pitsGameRest.playerMove("player1", 1);
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());
        assertTrue(response.getEntity() instanceof PitsGameModel);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 0, 7, 7, 7, 7, 7, 1 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        verifyStateWithoutCheck(((PitsGameModel) response.getEntity()).getPitsOfPlayer1(), ((PitsGameModel) response.getEntity()).getPitsOfPlayer2(), expectedStateOfPlayerOnePits,
                expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
        assertFalse(PitsGame.isPlayerWon());
    }

    @Test
    public void testPlayerB() {
        Response response = pitsGameRest.playerMove("player2", 1);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 0, 7, 7, 7, 7, 7, 1 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 7, 7, 7, 7, 7, 1 });
        verifyStateWithoutCheck(((PitsGameModel) response.getEntity()).getPitsOfPlayer1(), ((PitsGameModel) response.getEntity()).getPitsOfPlayer2(), expectedStateOfPlayerOnePits,
                expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
        assertFalse(PitsGame.isPlayerWon());
    }

    @Test
    public void testExceptionFlow1(){
        Response response = pitsGameRest.playerMove("abc", 1);
        assertEquals(400, response.getStatus());
    }

    @Test
    public void testExceptionFlow2(){
        Response response = pitsGameRest.playerMove("abc", 7);
        assertEquals(400, response.getStatus());
    }

    @Test
    public void testExceptionFlow3(){
        Response response = pitsGameRest.playerMove("abc", 0);
        assertEquals(400, response.getStatus());
    }

}
