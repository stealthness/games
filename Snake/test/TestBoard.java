import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 29/11/2018.
 */
class TestBoard {

    private static final int[] POS_0_0 = {0, 0};
    private static final int[] POS_2_2 = {2, 2};
    private static final int[] POS_4_4 = {4, 4};
    private SnakeGame sg;

    private static final int[] SIZE_5x5 = {5,5};
    private static final int[] SIZE_3x5 = {3,5};
    private static final int[] SIZE_4x4 = {4,4};


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
        sg.startSnakeAt(POS_0_0);
        assertEquals(BLOCK.IS_SNAKE,sg.at(POS_0_0));
    }

    @Test
    void thatSnakeCanMoveInUPDirections(){

        sg.startSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.UP);
        assertEquals(BLOCK.IS_SNAKE,sg.at(new int[]{3,2}));

    }
    @Test
    void thatSnakeCanMoveInDOWNDirections(){

        sg.startSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.DOWN);
        assertEquals(BLOCK.IS_SNAKE,sg.at(new int[]{1,2}));

    }
    @Test
    void thatSnakeCanMoveInRIGHTDirections(){

        sg.startSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.RIGHT);
        assertEquals(BLOCK.IS_SNAKE,sg.at(new int[]{2,1}));

    }
    @Test
    void thatSnakeCanMoveInLEFTDirections(){

        sg.startSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.LEFT);
        assertEquals(BLOCK.IS_SNAKE,sg.at(new int[]{2,3}));
    }

    @Test
    void theSnakeCannotMoveUPOfTheBoard(){
        sg.startSnakeAt(POS_4_4);
        sg.moveSnake(MOVE.UP);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE,sg.at(POS_4_4));
    }

    @Test
    void theSnakeCannotMoveDOWNOfTheBoard(){
        sg.startSnakeAt(POS_0_0);
        sg.moveSnake(MOVE.DOWN);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE,sg.at(POS_0_0));
    }

    @Test
    void theSnakeCannotMoveLEFTOfTheBoard(){
        sg.startSnakeAt(POS_4_4);
        sg.moveSnake(MOVE.LEFT);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE,sg.at(POS_4_4));
    }

    @Test
    void theSnakeCannotMoveRIGHTOfTheBoard(){
        sg.startSnakeAt(POS_0_0);
        sg.moveSnake(MOVE.RIGHT);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE,sg.at(POS_0_0));
    }

    @Test
    void testToStringOnSnakeGame(){
        sg.startSnakeAt(POS_0_0);
        assertEquals(TestUtils.getTestCase("data01.txt","e5x5s0_0"),sg.toString());
    }
}
