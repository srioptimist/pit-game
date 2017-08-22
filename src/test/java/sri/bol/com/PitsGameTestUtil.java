package sri.bol.com;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

class PitsGameTestUtil {

    private static final int START_INDEX = 0;
    private static final int END_POSITION = 7;
    private static final int TOTAL_COUNT_OF_STONES = 72;

    static void verifyStateWithoutCheck(final List<Integer> givenStateOfPlayerOnePits, final List<Integer> givenStateOfPlayerTwoPits,
            final List<Integer> expectedStateOfPlayerOnePits, final List<Integer> expectedStateOfPlayerTwoPits) {
        verifyNumberOfStones(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        IntStream.range(START_INDEX, END_POSITION).forEach((int i) -> {
            assertEquals(expectedStateOfPlayerOnePits.get(i), givenStateOfPlayerOnePits.get(i));
            assertEquals(expectedStateOfPlayerTwoPits.get(i), givenStateOfPlayerTwoPits.get(i));
        });
    }

    private static void verifyNumberOfStones(final List<Integer> givenStonesInPlayerOnePits, final List<Integer> givenStonesInPlayerTwoPits,
            final List<Integer> expectedStonesInPlayerOnePits, final List<Integer> expectedStonesInPlayerTwoPits) {
        int givenStonesSum = givenStonesInPlayerOnePits.stream().mapToInt(Integer::intValue).sum() + givenStonesInPlayerTwoPits.stream().mapToInt(Integer::intValue).sum();
        int expectedStonesSum = expectedStonesInPlayerOnePits.stream().mapToInt(Integer::intValue).sum() + expectedStonesInPlayerTwoPits.stream().mapToInt(Integer::intValue).sum();
        if (givenStonesSum != TOTAL_COUNT_OF_STONES || expectedStonesSum != TOTAL_COUNT_OF_STONES) {
            throw new IllegalArgumentException();
        }
    }

    static void verifyState(final List<Integer> givenStateOfPlayerOnePits, final List<Integer> givenStateOfPlayerTwoPits, final List<Integer> expectedStateOfPlayerOnePits,
            final List<Integer> expectedStateOfPlayerTwoPits) {
        verifyNumberOfStones(givenStateOfPlayerOnePits, givenStateOfPlayerTwoPits, expectedStateOfPlayerOnePits, expectedStateOfPlayerTwoPits);
        IntStream.range(START_INDEX, END_POSITION).forEach((int i) -> {
            assertEquals(expectedStateOfPlayerOnePits.get(i), givenStateOfPlayerOnePits.get(i));
            assertEquals(expectedStateOfPlayerTwoPits.get(i), givenStateOfPlayerTwoPits.get(i));
        });
        assertFalse(PitsGame.isPlayerWon());
        assertFalse(PitsGame.isExtraMove());
    }

    static List<Integer> initializePitsWithGivenStones(int[] stones) {
        return Arrays.stream(stones).boxed().collect(Collectors.toList());
    }
}
