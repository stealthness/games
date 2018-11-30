/**
 * Created by Stephen West on 29/11/2018.
 */
public class SnakeGame {
    private final int[] boardSize;
    BLOCK board[][] ;

    public SnakeGame(int[] boardSize) {
        this.boardSize = boardSize;
        board = new BLOCK[boardSize[0]][boardSize[1]];
        for (int row = 0; row < boardSize[0]; row++){
            for (int col= 0; col < boardSize[1]; col++){
            board[row][col] = BLOCK.IS_EMPTY;
            }
        }
    }



    public String getBoardString() {

        StringBuilder sb = new StringBuilder();

        for (int col = 0;col < boardSize[1];col++){
            sb.append(".");
        }
        sb.append(System.lineSeparator());
        String line = sb.toString();
        sb = new StringBuilder();
        for (int row = 0; row <boardSize[0];row++){
            sb.append(line);
        }
        return sb.toString();
    }

    public BLOCK at(int[] pos) {
        return board[pos[0]][pos[1]];
    }
}

enum BLOCK {
    IS_EMPTY  // IS_WALL,IS_SNAKE
}
