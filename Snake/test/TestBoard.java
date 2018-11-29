import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 29/11/2018.
 */
public class TestBoard {

    SnakeGame sg;



    @Test
    public void testEmptyBoard(){
        int[] boardSize = {5,5};
        sg = new SnakeGame(boardSize);

        String expBoardString = TestUtils.getTestCase("data01.txt","e5x5");

        assertEquals(expBoardString,sg.getBoardString());


    }
}
