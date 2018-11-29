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
        for (int i = 0; i <5;i++){
            sb.append("....." + System.lineSeparator());
        }
        return sb.toString();
    }
}
