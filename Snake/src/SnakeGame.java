import java.util.*;

/**
 * Created by Stephen West on 29/11/2018.
 *
 * Is java based game
 */
public class SnakeGame {
    private final int[] boardSize;
    Deque<int[]> snake;

    BLOCK board[][] ;
    boolean hasBorderWall = false;


    public SnakeGame(int[] boardSize) {
        this.boardSize = boardSize;
        snake = new LinkedList<>();
        board = new BLOCK[boardSize[0]][boardSize[1]];
        initializeEmptyBoard(boardSize);
    }

    /**
     * Iniatializes an empty board
     * @param boardSize is the given size of the board.
     */
    private void initializeEmptyBoard(int[] boardSize) {
        for (int row = 0; row < boardSize[0]; row++){
            for (int col= 0; col < boardSize[1]; col++){
                board[row][col] = BLOCK.IS_EMPTY;
            }
        }
    }

    /**
     * Converts the board into a String representation
     * @return
     */
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

    public void setSnakeAt(int[] pos) {
        snake.add(pos);
        board[pos[0]][pos[1]] = BLOCK.IS_SNAKE;
    }

    /**
     * Returns that BLOCK value of the adjacent box in the given direction
     * @param pos current position of Box
     * @param direction direction of the adjacent box interested in
     * @return value of the adjacent box
     */
    private BLOCK getAdjacentBox(int[] pos, MOVE direction){
        switch (direction){
            case UP:
                if (pos[0]+1>=boardSize[0]){
                    return BLOCK.IS_OUT_OF_BOUNDS;
                }
            case DOWN:
                if (pos[0]-1<0){
                    return BLOCK.IS_OUT_OF_BOUNDS;
                }
            case LEFT:
                if (pos[1]+1>=boardSize[1]){
                    return BLOCK.IS_OUT_OF_BOUNDS;
                }
            case RIGHT:
                if (pos[1]-1<0){
                    return BLOCK.IS_OUT_OF_BOUNDS;
                }
        }
        return at(getAdjacentPos(pos, direction));
    }

    /**
     * Moves the head of the snake in the given direction
     * @param direction is the direction that the head of the snake will move to
     */
    public void moveSnake(MOVE direction) {
        int[] pos = snake.peek();
        int[] newPos;
        switch (getAdjacentBox(pos,direction)){
            case IS_EMPTY:
                newPos = getAdjacentPos(pos, direction);
                int[] snakeTailPos = snake.pollLast();
                board[snakeTailPos[0]][snakeTailPos[1]] = BLOCK.IS_EMPTY;
                snake.addFirst(newPos);
                board[newPos[0]][newPos[1]] = BLOCK.IS_SNAKE;
                break;
            case IS_WALL:
                board[pos[0]][pos[1]] = BLOCK.IS_DEAD;
                return;
            case IS_MUSHROOM:
                newPos = getAdjacentPos(pos, direction);
                snake.addFirst(newPos);
                board[newPos[0]][newPos[1]] = BLOCK.IS_SNAKE;
            case IS_OUT_OF_BOUNDS:
            default: // is null
                return;
        }

    }

    /**
     * Returns tha position of a box adjacent to position of the given box in the given direction
     * @param pos is the given position
     * @param direction is the direction to find the position of the adjacent box
     * @return returns the position of the adjacent box in the given direction
     */
    int[] getAdjacentPos(int[] pos,MOVE direction) {
        int[] newPos;
        switch (direction){
            case UP:
                newPos = new int[] {pos[0]+1,pos[1]};
                break;
            case DOWN:
                newPos = new int[] {pos[0]-1,pos[1]};
                break;
            case LEFT:
                newPos = new int[] {pos[0],pos[1]+1};
                break;
            case RIGHT:
                newPos = new int[] {pos[0],pos[1]-1};
                break;
            default:
                newPos= pos;
        }
        return newPos;
    }

    // Overriding

    /**
     * To string me
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int row = boardSize[0] -1;row >= 0; row--){
            for (int col= 0; col < boardSize[1]; col++){
                switch (board[row][col]){
                    case IS_SNAKE:
                        sb.append('S');
                        break;
                    case IS_WALL:
                        sb.append('W');
                        break;
                    case IS_DEAD:
                        sb.append('X');
                        break;
                    case IS_MUSHROOM:
                        sb.append('M');
                        break;
                    default: // IS_EMPTY:
                        sb.append('.');
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    // Setters and Getters

    public void setHasBorderWall(boolean hasBorderWall) {
        this.hasBorderWall = hasBorderWall;
        if(hasBorderWall){
            setBorderWall();
        }
    }

    /**
     * Sets all of the Border Boxes as walls
     */
    private void setBorderWall() {
        for (int row = 0;row <boardSize[0];row++){
            if (row == 0 || row == boardSize[0]-1) {
                    for(int col = 0;col<boardSize[1];col++){
                        board[row][col] = BLOCK.IS_WALL;
                    }
            } else{
                board[row][0] = BLOCK.IS_WALL;
                board[row][boardSize[1]-1] = BLOCK.IS_WALL;
            }
        }
    }

    /**
     * Sets a Mushroom at position pos
     * @param pos
     */
    public void setMushroomAt(int[] pos) {
        if (board[pos[0]][pos[1]] == BLOCK.IS_EMPTY){
            board[pos[0]][pos[1]] = BLOCK.IS_MUSHROOM;
        }
    }


    public BLOCK[][] getBoard() {
        return board;
    }

}

enum BLOCK {
    IS_EMPTY, IS_WALL, IS_SNAKE, IS_DEAD, IS_OUT_OF_BOUNDS, IS_MUSHROOM
}

enum MOVE{
    UP,DOWN,LEFT,RIGHT
}
