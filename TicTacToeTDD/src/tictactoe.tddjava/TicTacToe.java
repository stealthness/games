package tictactoe.tddjava;

/**
 * Created by Stephen West on 28/11/2018.
 */
public class TicTacToe {

    private Character[][] board = {
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'}
    };

    public void play(int row, int col) {
        if (row < 1 || row > 3 ){
            throw new RuntimeException("Y is outside the bounds of the board");
        }else if (col < 1 || col > 3  ){
            throw new RuntimeException("Y is outside the bounds of the board");
        }
        if (board[row-1][col-1] != '\0'){
            throw new RuntimeException("Y is outside the bounds of the board");
        }else{
            board[row-1][col-1] = 'X';
        }
    }

}
