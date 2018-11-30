import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 29/11/2018.
 */
public class TestBoard {

    private SnakeGame sg;

    private final int[] SIZE_5x5 = {5,5};
    private final int[] SIZE_3x5 = {3,5};
    private final int[] SIZE_4x4 = {4,4};


    @BeforeEach
    public void setUp(){
        sg = new SnakeGame(SIZE_5x5);
    }



    @Test
    public void testEmptyBoardOFSize5x5isCreatedCorrectly(){
        sg = new SnakeGame(SIZE_5x5);
        String expBoardString = TestUtils.getTestCase("data01.txt","e5x5");

        assertEquals(expBoardString,sg.getBoardString());


    }

    @Test
    public void testEmptyBoardOFSize3x5isCreatedCorrectly(){
        sg = new SnakeGame(SIZE_3x5);
        assertEquals(TestUtils.getTestCase("data01.txt","e3x5"), sg.getBoardString());


    }

    @Test
    public void testEmptyBoardOFSize4x4isCreatedCorrectly(){
        sg = new SnakeGame(SIZE_4x4);
        assertEquals(TestUtils.getTestCase("data01.txt","e4x4"), sg.getBoardString());
    }

    @Test
    public void testThatAllGetPosDetailIsEmptyOnAnEmptyBoard(){
        for (int row = 0;row <SIZE_5x5[0] ;row++){
            for (int col = 0; col <SIZE_5x5[1];col++){
                assertEquals(BLOCK.IS_EMPTY,sg.at(new int[]{row,col}));
            }
        }
    }
}
