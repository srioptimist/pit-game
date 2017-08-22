package sri.bol.com;

import java.util.ArrayList;
import java.util.List;

public class PitsGameModel {

    private List<Integer> pitsOfPlayer1;
    private List<Integer> pitsOfPlayer2;
    private boolean playerInActionWin;
    private boolean extraMove;

    public PitsGameModel(final List<Integer> pitsOfPlayer1, final List<Integer> pitsOfPlayer2, final boolean playerInActionWin, final boolean extraMove) {
        this.pitsOfPlayer1 = pitsOfPlayer1;
        this.pitsOfPlayer2 = pitsOfPlayer2;
        this.playerInActionWin = playerInActionWin;
        this.extraMove = extraMove;
    }

    public List<Integer> getPitsOfPlayer1() {
        return pitsOfPlayer1;
    }

    public List<Integer> getPitsOfPlayer2() {
        return pitsOfPlayer2;
    }

    public boolean isPlayerInActionWin() {
        return playerInActionWin;
    }

    public boolean isExtraMove() {
        return extraMove;
    }
}
