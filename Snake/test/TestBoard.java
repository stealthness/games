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

    private static final int[] SIZE_5x5 = {5, 5};
    private static final int[] SIZE_3x5 = {3, 5};
    private static final int[] SIZE_4x4 = {4, 4};


    @BeforeEach
    void setUp() {
        sg = new SnakeGame(SIZE_5x5);
    }


    @Test
    void testEmptyBoardOFSize5x5isCreatedCorrectly() {
        sg = new SnakeGame(SIZE_5x5);
        String expBoardString = TestUtils.getTestCase("data01.txt", "e5x5");
        assertEquals(expBoardString, sg.getBoardString());
    }

    @Test
    void testEmptyBoardOFSize3x5isCreatedCorrectly() {
        sg = new SnakeGame(SIZE_3x5);
        assertEquals(TestUtils.getTestCase("data01.txt", "e3x5"), sg.getBoardString());
    }

    @Test
    void testEmptyBoardOFSize4x4isCreatedCorrectly() {
        sg = new SnakeGame(SIZE_4x4);
        assertEquals(TestUtils.getTestCase("data01.txt", "e4x4"), sg.getBoardString());
    }

    @Test
    void testThatAllGetPosDetailIsEmptyOnAnEmptyBoard() {
        for (int row = 0; row < SIZE_5x5[0]; row++) {
            for (int col = 0; col < SIZE_5x5[1]; col++) {
                assertEquals(BLOCK.IS_EMPTY, sg.at(new int[]{row, col}));
            }
        }
    }

    @Test
    void thatSnakePlaceAtGivenPosReturnsIS_SNAKE() {
        sg.setSnakeAt(POS_0_0);
        assertEquals(BLOCK.IS_SNAKE, sg.at(POS_0_0));
    }

    @Test
    void thatSnakeCanMoveInUPDirections() {

        sg.setSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.UP);
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos(3, 2)));
    }

    @Test
    void thatSnakeCanMoveInDOWNDirections() {

        sg.setSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.DOWN);
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos(1, 2)));
    }

    @Test
    void thatSnakeCanMoveInRIGHTDirections() {
        sg.setSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.RIGHT);
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos(2, 1)));
    }

    @Test
    void thatSnakeCanMoveInLEFTDirections() {
        sg.setSnakeAt(POS_2_2);
        sg.moveSnake(MOVE.LEFT);
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos(2, 3)));
    }

    @Test
    void theSnakeCannotMoveUPOfTheBoard() {
        sg.setSnakeAt(POS_4_4);
        sg.moveSnake(MOVE.UP);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE, sg.at(POS_4_4));
    }

    @Test
    void theSnakeCannotMoveDOWNOfTheBoard() {
        sg.setSnakeAt(POS_0_0);
        sg.moveSnake(MOVE.DOWN);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE, sg.at(POS_0_0));
    }

    @Test
    void theSnakeCannotMoveLEFTOfTheBoard() {
        sg.setSnakeAt(POS_4_4);
        sg.moveSnake(MOVE.LEFT);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE, sg.at(POS_4_4));
    }

    @Test
    void theSnakeCannotMoveRIGHTOfTheBoard() {
        sg.setSnakeAt(POS_0_0);
        sg.moveSnake(MOVE.RIGHT);
        // snake must not have moved
        assertEquals(BLOCK.IS_SNAKE, sg.at(POS_0_0));
    }

    @Test
    void testToStringOnSnakeGame() {
        sg.setSnakeAt(POS_0_0);
        assertEquals(TestUtils.getTestCase("data01.txt", "e5x5s0_0"), sg.toString());
    }

    @Test
    void thatByDefaultPos0_0IS_WALL() {
        sg.setSnakeAt(POS_2_2);
        sg.setHasBorderWall(true);
        assertEquals(BLOCK.IS_WALL, sg.at(POS_0_0));
    }

    @Test
    void thatByDefaultPos4_4IS_WALL() {
        sg.setSnakeAt(POS_2_2);
        sg.setHasBorderWall(true);
        assertEquals(BLOCK.IS_WALL, sg.at(POS_4_4));
    }

    @Test
    void testThatByDefaultEdgeOFABoardIS_WALL() {
        sg.setHasBorderWall(true);
        sg.setSnakeAt(POS_2_2);
        assertEquals(BLOCK.IS_WALL, sg.at(POS_0_0));
        assertEquals(BLOCK.IS_WALL, sg.at(POS_4_4));
        assertEquals(TestUtils.getTestCase("data01.txt", "e5x5test01"), sg.toString());
    }

    @Test
    void testThatIfSnakeMovesUPIntoWallItBecomesIS_DEAD() {
        testHittingWallKillsSnake(pos(3,3),MOVE.UP);
        assertEquals(TestUtils.getTestCase("data01.txt", "e5x5test03"), sg.toString());
    }

    @Test
    void testThatIfSnakeMovesLEFTIntoWallItBecomesIS_DEAD() {
        testHittingWallKillsSnake(pos(3,3),MOVE.LEFT);
    }

    @Test
    void testThatIfSnakeMovesDOWNIntoWallItBecomesIS_DEAD() {
        testHittingWallKillsSnake(pos(1,1),MOVE.DOWN);
    }

    @Test
    void testThatIfSnakeMovesRIGHTIntoWallItBecomesIS_DEAD() {
        testHittingWallKillsSnake(pos(1,1),MOVE.RIGHT);
    }

    private void testHittingWallKillsSnake(int[] initialPos, MOVE direction){
        sg.setHasBorderWall(true);
        sg.setSnakeAt(initialPos);
        sg.moveSnake(direction);
        assertEquals(BLOCK.IS_DEAD, sg.at(initialPos));
    }

    @Test
    public void testPlacingOfAMushroomATGivenPos(){
        sg.setMushroomAt(pos(1,1));
        assertEquals(BLOCK.IS_MUSHROOM, sg.at(pos(1,1)));
    }

    @Test
    void testPlacingOfMushroomOnWallFails(){
        sg.setHasBorderWall(true);
        int[] pos = pos(0,0);
        sg.setMushroomAt(pos);
        assertEquals(BLOCK.IS_WALL, sg.at(pos));
    }

    @Test
    void testPlacingOfMushroomOnSnakeFails(){
        sg.setHasBorderWall(true);
        int[] pos = pos(1,1);
        sg.setSnakeAt(pos);
        sg.setMushroomAt(pos);
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos));
    }

    @Test
    void testMovingSnakeHeadUPOnAMushroomIncreaseSnakesSizeBy1(){
        sg.setSnakeAt(POS_2_2);
        sg.setMushroomAt(pos(3,2));
        sg.moveSnake(MOVE.UP);
        assertEquals(BLOCK.IS_SNAKE, sg.at(POS_2_2));
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos(3,2)));

    }

    @Test
    void testMovingSnakeHeadLEFTOnAMushroomIncreaseSnakesSizeBy1(){
        sg.setSnakeAt(POS_2_2);
        sg.setMushroomAt(pos(2,3));

        System.out.println(sg.toString());
        sg.moveSnake(MOVE.LEFT);
        assertEquals(BLOCK.IS_SNAKE, sg.at(POS_2_2));
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos(2,3)));

        System.out.println(sg.toString());
    }

    @Test
    void testMovingSnakeHeadRIGHTOnAMushroomIncreaseSnakesSizeBy1(){
        testSnakeEatingMushrooms(POS_2_2, MOVE.RIGHT);
    }

    @Test
    void testMovingSnakeHeadDOWNOnAMushroomIncreaseSnakesSizeBy1(){
        testSnakeEatingMushrooms(POS_2_2, MOVE.RIGHT);
    }

    private void testSnakeEatingMushrooms(int[] pos, MOVE direction){
        int[] adjPos = sg.getAdjacentPos(pos,direction);
        sg.setSnakeAt(pos);
        sg.setMushroomAt(adjPos);
        sg.moveSnake(direction);
        assertEquals(BLOCK.IS_SNAKE, sg.at(pos));
        assertEquals(BLOCK.IS_SNAKE, sg.at(adjPos));
    }

 /*   private void testEatingMushroom(){

    }*/




    // helper methods

    private int[] pos(int row, int col) {
        return new int[]{row, col};
    }
}
