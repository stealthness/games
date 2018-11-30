package tictactoe.tddjava;

/**
 * Created by Stephen West on 28/11/2018.
 */
public class TicTacToe {

    private static final String NO_WINNER_MSG = "No Winner";
    private final char X = 'X';
    private final char O = 'O';
    char nextPlayer = X;

    private Character[][] board = {
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'},
            {'\0', '\0', '\0'}
    };

    public String play(int row, int col) {
        checkAxis(row);
        checkAxis(col);
        setBox(row,col);
        checkWin(nextPlayer);
        return NO_WINNER_MSG;

    }

    private boolean checkWin(char player) {

        int[][][] winningLines = {
                {{1,1},{2,1},{3,1}},
                {{1,2},{2,2},{3,2}},
                {{1,3},{2,3},{3,3}}};

        char testPlayer = (nextPlayer()==X)?O:X;
        boolean isWinningLine;

        for (int[][] winningLine : winningLines){
            isWinningLine = true;
            for (int[] box : winningLine){
                if (board[box[0]-1][box[1]-1] != testPlayer){
                    isWinningLine = false;
                    break;
                }
            }
            if (isWinningLine) {
                return true;
            }
        }
        return false;


    }

    private void setBox(int row, int col){
        if (board[row-1][col-1] != '\0'){
            throw new RuntimeException("Box is occupied");
        }else{
            board[row-1][col-1] = 'X';
            switchPlayer();
        }
    }

    private void switchPlayer() {
        nextPlayer = (nextPlayer()==X)?O:X;
    }

    private void  checkAxis(int x){
        if (x < 1 || x > 3 ){
            throw new RuntimeException("X is outside the bounds of the board");
        }
    }

    public char nextPlayer() {
        return nextPlayer;
    }
}
