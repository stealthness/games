/**
 * Created by Stephen West on 29/11/2018.
 */
public class SnakeGame {
    public SnakeGame(int[] boardSize) {
    }

    public String getBoardString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <5;i++){
            sb.append("....." + System.lineSeparator());
        }
        return sb.toString();
    }
}
