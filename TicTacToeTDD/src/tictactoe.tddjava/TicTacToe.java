package tictactoe.tddjava;

/**
 * Created by Stephen West on 28/11/2018.
 */
public class TicTacToe {
    public void play(int row, int col) {
        if (row < 1 || row > 3 || col < 1 || col > 3 ){
            throw new RuntimeException("Out of bounds");
        }
    }
}
