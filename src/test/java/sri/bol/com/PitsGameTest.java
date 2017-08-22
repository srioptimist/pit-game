package sri.bol.com;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static sri.bol.com.PitsGameTestUtil.verifyStateWithoutCheck;
import static sri.bol.com.PitsGameTestUtil.verifyState;
import static sri.bol.com.PitsGameTestUtil.initializePitsWithGivenStones;

public class PitsGameTest {

    @Test
    public void simpleTest_Player1() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        PitsGame.startPlayerOne(1, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 0, 7, 7, 7, 7, 7, 1 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
        assertFalse(PitsGame.isPlayerWon());
    }

    @Test
    public void simpleTest_Player2() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        PitsGame.startPlayerTwo(1, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 7, 7, 7, 7, 7, 1 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
        assertFalse(PitsGame.isPlayerWon());
    }

    @Test
    public void testPlayer1_SowingStonesInOtherPalyerPit() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        PitsGame.startPlayerOne(3, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 6, 0, 7, 7, 7, 1 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 7, 7, 6, 6, 6, 6, 0 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer2_SowingStonesInOtherPalyerPit() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 0 });
        PitsGame.startPlayerTwo(5, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 7, 7, 7, 6, 6, 0 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 0, 7, 1 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer1_SowingStonesInOtherPalyerPit_EndsInHisOwnPit() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 1, 2, 3, 4, 10, 6, 5 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 10, 10, 11, 2, 3, 2, 3 });
        PitsGame.startPlayerOne(5, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 2, 2, 3, 4, 0, 7, 6 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 11, 11, 12, 3, 4, 3, 4 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer2_SowingStonesInOtherPalyerPit_EndsInHisOwnPit() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 2, 3, 2, 3, 5, 5, 8 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 4, 13, 2, 7, 9, 1, 8 });
        PitsGame.startPlayerTwo(2, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 3, 4, 3, 4, 6, 6, 9 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 5, 0, 3, 8, 10, 2, 9 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer1_SowingStonesInOtherPalyerPit_EndsInOtherPlayerPit() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 21, 1, 3, 1, 4, 3, 2 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 5, 0, 3, 8, 10, 2, 9 });
        PitsGame.startPlayerOne(1, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 1, 3, 5, 3, 6, 5, 4 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 7, 1, 4, 9, 11, 3, 10 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer2_SowingStonesInOtherPalyerPit_EndsInOtherPlayerPit() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 6, 6, 6, 6, 6, 2 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 1, 2, 1, 2, 1, 25, 2 });
        PitsGame.startPlayerTwo(6, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 8, 8, 8, 8, 8, 8, 4 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 3, 4, 3, 3, 2, 1, 4 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer1_SimpleCapture() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 2, 20, 2, 4, 0, 5 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 0, 10, 0, 0, 12, 10 });
        PitsGame.startPlayerOne(4, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 2, 20, 0, 5, 13, 5 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 0, 10, 0, 0, 0, 10 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer2_SimpleCapture() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 0, 6, 0, 0, 0, 30 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 2, 5, 0, 5, 5, 0, 13 });
        PitsGame.startPlayerTwo(1, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 0, 0, 0, 0, 0, 30 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 6, 7, 5, 5, 0, 13 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer1_ComplexCapture() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 1, 2, 3, 4, 14, 6, 5 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 10, 10, 7, 2, 3, 2, 3 });
        PitsGame.startPlayerOne(5, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 2, 3, 4, 5, 5, 7, 6 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 11, 11, 8, 3, 0, 3, 4 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer2_ComplexCapture() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 2, 3, 2, 3, 5, 5, 8 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 3, 14, 2, 7, 9, 1, 8 });
        PitsGame.startPlayerTwo(2, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 3, 0, 3, 4, 6, 6, 9 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 4, 5, 3, 8, 10, 2, 9 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

    @Test
    public void testPlayer1_Won() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 2, 40, 2, 4, 0, 5 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 0, 0, 0, 0, 2, 10 });
        PitsGame.startPlayerOne(4, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 2, 40, 0, 5, 3, 5 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 0, 0, 0, 0, 0, 10 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isPlayerWon());
    }

    @Test
    public void testPlayer2_Won() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 0, 0, 12, 0, 0, 0, 30 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 2, 5, 0, 5, 5, 0, 13 });
        PitsGame.startPlayerTwo(1, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 0, 0, 0, 0, 0, 0, 30 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 6, 13, 5, 5, 0, 13 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isPlayerWon());
    }

    @Test
    public void testPlayer1_ExtraMove_Simple() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 4, 3, 2, 2, 0, 10 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 5, 5, 2, 3, 2, 3, 24 });
        PitsGame.startPlayerOne(5, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 4, 3, 2, 0, 1, 11 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 5, 5, 2, 3, 2, 3, 24 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
    }

    @Test
    public void testPlayer2_ExtraMove_Simple() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 4, 3, 2, 2, 0, 10 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 5, 5, 4, 1, 2, 3, 24 });
        PitsGame.startPlayerTwo(3, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 4, 3, 2, 2, 0, 10 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 5, 5, 0, 2, 3, 4, 25 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
    }

    @Test
    public void testPlayer1_ExtraMove_Complex() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 1, 2, 3, 4, 16, 4, 5 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 10, 10, 7, 2, 3, 2, 3 });
        PitsGame.startPlayerOne(5, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 2, 3, 4, 5, 1, 6, 7 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 11, 11, 8, 3, 4, 3, 4 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
    }

    @Test
    public void testPlayer2_ExtraMove_Complex() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 2, 3, 2, 3, 5, 5, 8 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 3, 19, 2, 7, 9, 1, 3 });
        PitsGame.startPlayerTwo(2, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 3, 4, 3, 4, 6, 6, 9 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 4, 1, 4, 9, 11, 3, 5 });
        verifyStateWithoutCheck(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        assertTrue(PitsGame.isExtraMove());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlayer_Exception() {
        final List<Integer> givenStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 7, 0, 6, 0, 0, 0, 30 });
        final List<Integer> givenStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 2, 5, 0, 5, 5, 0, 13 });
        PitsGame.startPlayerTwo(1, givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits);
        final List<Integer> expectedStateOfPlayerOnePits = initializePitsWithGivenStones(new int[] { 6, 0, 0, 0, 0, 0, 30 });
        final List<Integer> expectedStateOfPlayerTwoPits = initializePitsWithGivenStones(new int[] { 0, 6, 7, 5, 5, 0, 13 });
        verifyState(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
    }

}
