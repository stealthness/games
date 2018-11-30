import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 29/11/2018.
 */
public class TestBoard {

    SnakeGame sg;


    @BeforeEach
    public void setUp(){
        sg = new SnakeGame(new int[] {5,5});
    }



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

    @Test
    public void testThatAllGetPosDetailIsEmptyOnAnEmptyBoard(){

        assertEquals(BLOCK.IS_EMPTY,sg.at(new int[]{0,0}));

        for (int row = 0;row <5 ;row++){
            for (int col = 0; col <5;col++){
                assertEquals(BLOCK.IS_EMPTY,sg.at(new int[]{row,col}));
            }
        }




    }
}
