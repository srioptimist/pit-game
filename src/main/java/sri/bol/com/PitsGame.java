package sri.bol.com;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class PitsGame {

    private static final int NUMBER_OF_PITS = 6;
    private static final int NUMBER_OF_STONES = 6;
    private static final int START_INDEX = 0;
    private static final int STONE_IN_BIG_PIT = 0;
    private static final int NUMBER_OF_BIG_PIT = 1;
    private static final int TOTAL_NUMBER_PITS_FOR_A_PLAYER = NUMBER_OF_PITS + NUMBER_OF_BIG_PIT;

    private static final List<Integer> pitsOfPlayer1 = new ArrayList<>();
    private static final List<Integer> pitsOfPlayer2 = new ArrayList<>();
    private static boolean playerInActionWin;
    private static boolean extraMove;

    static {
        initializePits(pitsOfPlayer1);
        initializePits(pitsOfPlayer2);
    }

    static void startPlayerOne(final int startPositionOfPit) {
        start(startPositionOfPit, pitsOfPlayer1, pitsOfPlayer2);
    }

    static void startPlayerTwo(final int startPositionOfPit) {
        start(startPositionOfPit, pitsOfPlayer2, pitsOfPlayer1);
    }

    static void startPlayerOne(final int startPositionOfPit, final List<Integer> pitsOfPlayer1, final List<Integer> pitsOfPlayer2) {
        start(startPositionOfPit, pitsOfPlayer1, pitsOfPlayer2);
    }

    static void startPlayerTwo(final int startPositionOfPit, final List<Integer> pitsOfPlayer1, final List<Integer> pitsOfPlayer2) {
        start(startPositionOfPit, pitsOfPlayer2, pitsOfPlayer1);
    }

    private static void start(final int startPositionOfPit, final List<Integer> pitsOfPlayerInAction, final List<Integer> pitsOfOtherPlayer) {
        resetFlags();
        int startIndexOfPit = startPositionOfPit - 1;
        int noOfStonesInStartPit = pitsOfPlayerInAction.get(startIndexOfPit);
        int lastPostionOfPitToSow = startPositionOfPit + noOfStonesInStartPit;
        boolean isMorePitsThanHisOwn = lastPostionOfPitToSow > TOTAL_NUMBER_PITS_FOR_A_PLAYER;
        int endPositionOfHisPit = isMorePitsThanHisOwn ? TOTAL_NUMBER_PITS_FOR_A_PLAYER : lastPostionOfPitToSow;
        int endIndex = endPositionOfHisPit - 1;
        boolean capture = false;
        if (isCapturePossible(pitsOfPlayerInAction, endIndex)) {
            capture = true;
        }
        determineIsExtraMove(!isMorePitsThanHisOwn, endPositionOfHisPit);
        sowStoneInHisPit(pitsOfPlayerInAction, startIndexOfPit, endPositionOfHisPit, true);
        if (capture) {
            capture(pitsOfPlayerInAction, pitsOfOtherPlayer, endIndex);
        }
        determineIfStonesToBeSowedInOtherPlayersPit(startPositionOfPit, pitsOfPlayerInAction, pitsOfOtherPlayer, noOfStonesInStartPit, isMorePitsThanHisOwn);
        determineIfPlayerWon(pitsOfOtherPlayer);
    }

    private static void determineIfStonesToBeSowedInOtherPlayersPit(final int startPositionOfPit, final List<Integer> pitsOfPlayerInAction, final List<Integer> pitsOfOtherPlayer,
            final int noOfStonesInStartPit, final boolean isMorePitsThanHisOwn) {
        if (isMorePitsThanHisOwn) {
            int noOfPitsToBeSowedInOtherPlayer = noOfStonesInStartPit - (TOTAL_NUMBER_PITS_FOR_A_PLAYER - startPositionOfPit);
            if (noOfPitsToBeSowedInOtherPlayer > TOTAL_NUMBER_PITS_FOR_A_PLAYER) {
                sowStoneInOtherPlayerPit(pitsOfOtherPlayer, TOTAL_NUMBER_PITS_FOR_A_PLAYER);
                excessStones(noOfPitsToBeSowedInOtherPlayer - TOTAL_NUMBER_PITS_FOR_A_PLAYER, pitsOfPlayerInAction, pitsOfOtherPlayer);
            } else {
                sowStoneInOtherPlayerPit(pitsOfOtherPlayer, noOfPitsToBeSowedInOtherPlayer);
            }
        }
    }

    private static void excessStones(int noOfSones, final List<Integer> pitsOfPlayerInAction, final List<Integer> pitsOfOtherPlayer) {
        final int maxStonesForOneRound = TOTAL_NUMBER_PITS_FOR_A_PLAYER * 2;
        boolean capture = false;
        do {
            int endPositionOfPit = noOfSones > TOTAL_NUMBER_PITS_FOR_A_PLAYER ? TOTAL_NUMBER_PITS_FOR_A_PLAYER : noOfSones;
            int endIndex = endPositionOfPit - 1;
            if (isCapturePossible(pitsOfPlayerInAction, endIndex)) {
                capture = true;
            }
            sowStoneInHisPit(pitsOfPlayerInAction, 0, endPositionOfPit, false);
            if (capture) {
                capture(pitsOfPlayerInAction, pitsOfOtherPlayer, endIndex);
            }
            determineIsExtraMove(noOfSones == TOTAL_NUMBER_PITS_FOR_A_PLAYER, endPositionOfPit);
            if (noOfSones > TOTAL_NUMBER_PITS_FOR_A_PLAYER) {
                endPositionOfPit =
                        noOfSones - TOTAL_NUMBER_PITS_FOR_A_PLAYER > TOTAL_NUMBER_PITS_FOR_A_PLAYER ? TOTAL_NUMBER_PITS_FOR_A_PLAYER : noOfSones - TOTAL_NUMBER_PITS_FOR_A_PLAYER;
                sowStoneInOtherPlayerPit(pitsOfOtherPlayer, endPositionOfPit);
            }
            noOfSones -= maxStonesForOneRound;
        } while (noOfSones > 0);
    }

    private static void sowStoneInHisPit(final List<Integer> pitsOfPlayerInAction, final int startIndexOfPit, final int endPositionOfPit, final boolean isStartMove) {
        IntStream.range(startIndexOfPit, endPositionOfPit).forEach(i -> {
            if (i == startIndexOfPit && isStartMove) {
                pitsOfPlayerInAction.set(i, 0);
            } else {
                pitsOfPlayerInAction.set(i, pitsOfPlayerInAction.get(i) + 1);
            }
        });

    }

    private static void sowStoneInOtherPlayerPit(final List<Integer> pitsOfOtherPlayer, final int noOfPitsToBeSowedInOtherPlayer) {
        IntStream.range(0, noOfPitsToBeSowedInOtherPlayer).forEach(i -> pitsOfOtherPlayer.set(i, pitsOfOtherPlayer.get(i) + 1));
    }

    private static void initializePits(final List<Integer> pitsOfPlayer) {
        for (int i = START_INDEX; i < NUMBER_OF_PITS; i++) {
            pitsOfPlayer.add(NUMBER_OF_STONES);
        }
        pitsOfPlayer.add(STONE_IN_BIG_PIT);
    }

    private static boolean isCapturePossible(final List<Integer> pitsOfPlayerInAction, final int endIndex) {
        return endIndex < 6 && pitsOfPlayerInAction.get(endIndex) == 0;
    }

    private static void resetFlags() {
        playerInActionWin = false;
        extraMove = false;
    }

    private static void determineIsExtraMove(final boolean isLastStoneEndsInHisOwn, final int endPostionOfPit) {
        if (endPostionOfPit == TOTAL_NUMBER_PITS_FOR_A_PLAYER && isLastStoneEndsInHisOwn) {
            extraMove = true;
        }
    }

    private static void determineIfPlayerWon(final List<Integer> pitsOfOtherPlayer) {
        int sumOfStonesInOtherPlayerPits = pitsOfOtherPlayer.stream().mapToInt(Integer::intValue).sum() - pitsOfOtherPlayer.get(TOTAL_NUMBER_PITS_FOR_A_PLAYER - 1);
        if (sumOfStonesInOtherPlayerPits == 0) {
            playerInActionWin = true;
        }
    }

    private static void capture(final List<Integer> pitsOfPlayerInAction, final List<Integer> pitsOfOtherPlayer, final int endPosition) {
        pitsOfPlayerInAction.set(endPosition, pitsOfOtherPlayer.get(endPosition) + 1);
        pitsOfOtherPlayer.set(endPosition, 0);
    }

    static List<Integer> getPitsOfPlayer1() {
        return pitsOfPlayer1;
    }

    static List<Integer> getPitsOfPlayer2() {
        return pitsOfPlayer2;
    }

    static boolean isPlayerWon() {
        return playerInActionWin;
    }

    static boolean isExtraMove() {
        return extraMove;
    }
}
