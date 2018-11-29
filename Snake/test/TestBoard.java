import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 29/11/2018.
 */
public class TestBoard {

    SnakeGame sg;



    @Test
    public void testEmptyBoardOFSize5x5isCreatedCorrectly(){
        int[] boardSize = {5,5};
        sg = new SnakeGame(boardSize);

        String expBoardString = TestUtils.getTestCase("data01.txt","e5x5");

        assertEquals(expBoardString,sg.getBoardString());


    }

    @Test
    public void testEmptyBoardOFSize3x5isCreatedCorrectly(){
        sg = new SnakeGame(new int[] {3,5});

        String expBoardString = TestUtils.getTestCase("data01.txt","e3x5");

        assertEquals(expBoardString,sg.getBoardString());


    }

    @Test
    public void testEmptyBoardOFSize4x4isCreatedCorrectly(){
        sg = new SnakeGame(new int[] {4,4});

        String expBoardString = TestUtils.getTestCase("data01.txt","e4x4");

        assertEquals(expBoardString,sg.getBoardString());


    }
}
