import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 29/11/2018.
 */
class TestBoard {

    private SnakeGame sg;

    private final int[] SIZE_5x5 = {5,5};
    private final int[] SIZE_3x5 = {3,5};
    private final int[] SIZE_4x4 = {4,4};


    @BeforeEach
    void setUp(){
        sg = new SnakeGame(SIZE_5x5);
    }



    @Test
    void testEmptyBoardOFSize5x5isCreatedCorrectly(){
        sg = new SnakeGame(SIZE_5x5);
        String expBoardString = TestUtils.getTestCase("data01.txt","e5x5");

        assertEquals(expBoardString,sg.getBoardString());


    }

    @Test
    void testEmptyBoardOFSize3x5isCreatedCorrectly(){
        sg = new SnakeGame(SIZE_3x5);
        assertEquals(TestUtils.getTestCase("data01.txt","e3x5"), sg.getBoardString());


    }

    @Test
    void testEmptyBoardOFSize4x4isCreatedCorrectly(){
        sg = new SnakeGame(SIZE_4x4);
        assertEquals(TestUtils.getTestCase("data01.txt","e4x4"), sg.getBoardString());
    }

    @Test
    void testThatAllGetPosDetailIsEmptyOnAnEmptyBoard(){
        for (int row = 0;row <SIZE_5x5[0] ;row++){
            for (int col = 0; col <SIZE_5x5[1];col++){
                assertEquals(BLOCK.IS_EMPTY,sg.at(new int[]{row,col}));
            }
        }
    }

    @Test
    void thatSnakePlaceAtGivenPosReturnsIS_SNAKE(){
        sg.startSnakeAt(new int[] {0,0});
        assertEquals(BLOCK.IS_SNAKE,sg.at(new int[]{0,0}));
    }

}
