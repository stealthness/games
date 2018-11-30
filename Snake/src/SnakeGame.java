import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen West on 29/11/2018.
 */
public class SnakeGame {
    private final int[] boardSize;
    List<int[]> snake;
    BLOCK board[][] ;

    public SnakeGame(int[] boardSize) {
        this.boardSize = boardSize;
        snake = new ArrayList<>();
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

    public void startSnakeAt(int[] pos) {
        snake.add(pos);
        board[pos[0]][pos[1]] = BLOCK.IS_SNAKE;
    }

    public void moveSnake(MOVE direction) {
        int[] pos = snake.get(0);
        int[] newPos = pos;
        switch (direction){
            case UP:
                newPos = new int[] {pos[0]+1,pos[1]};
                break;
            case DOWN:
                newPos = new int[] {pos[0]-1,pos[1]};
                break;
            case LEFT:
                newPos = new int[] {pos[0],pos[1]-1};
                break;
            case RIGHT:
                newPos = new int[] {pos[0],pos[1]+1};
                break;
        }
        snake.set(0,newPos);
        board[pos[0]][pos[1]] = BLOCK.IS_EMPTY;
        board[newPos[0]][newPos[1]] = BLOCK.IS_SNAKE;
    }
}

enum BLOCK {
    IS_EMPTY, IS_SNAKE // IS_WALL,IS_SNAKE
}

enum MOVE{
    UP,DOWN,LEFT,RIGHT;
}
