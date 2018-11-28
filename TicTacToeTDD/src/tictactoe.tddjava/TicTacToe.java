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
        checkAxis(row);
        checkAxis(col);
        setBox(row,col);

    }

    private void setBox(int row, int col){
        if (board[row-1][col-1] != '\0'){
            throw new RuntimeException("Box is occupied");
        }else{
            board[row-1][col-1] = 'X';
        }
    }

    private void  checkAxis(int x){
        if (x < 1 || x > 3 ){
            throw new RuntimeException("X is outside the bounds of the board");
        }
    }

}
