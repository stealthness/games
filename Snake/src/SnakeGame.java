/**
 * Created by Stephen West on 29/11/2018.
 */
public class SnakeGame {
    private final int[] boardSize;

    public SnakeGame(int[] boardSize) {
        this.boardSize = boardSize;
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
}
