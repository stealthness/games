package tictactoe.tddjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Stephen West on 28/11/2018.
 */
public class TicTacToeSpec {

    private final char X = 'X';
    private final char O = 'O';

    private TicTacToe ticTacToe;

    @BeforeEach
    public final void before() {
        ticTacToe = new TicTacToe();
    }
    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        Assertions.assertThrows(RuntimeException.class,()-> ticTacToe.play(5, 2));
        Assertions.assertThrows(RuntimeException.class,()-> ticTacToe.play(0, 2));
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        Assertions.assertThrows(RuntimeException.class,()-> ticTacToe.play(2, 5));
        Assertions.assertThrows(RuntimeException.class,()-> ticTacToe.play(2, 0));
    }

    @Test
    public void whenOccupiedThenRuntimeException() {
        ticTacToe.play(2, 1);
        Assertions.assertThrows(RuntimeException.class,()-> ticTacToe.play(2, 1));
    }

    @Test
    public void giveFirstTurnWhenNextPlayerShouldBeX(){
        assertEquals(X,ticTacToe.nextPlayer());
    }

    @Test
    public void giveLastTurnWasXTheNextTurnShouldBeO(){
        ticTacToe.play(2,2);
        assertEquals(O,ticTacToe.nextPlayer());
    }


    @Test
    public void givenLastTurnWasOTheNextTurnShouldBeX(){
        ticTacToe.play(2,2);
        ticTacToe.play(1,1);
        assertEquals(X,ticTacToe.nextPlayer());
    }

    @Test
    public void givenEightMoveThatWillNotLeadToAWinningConditions(){
        int[][] moves = {{2,2},{1,2},{2,3},{2,1},{1,1},{3,3},{3,1},{1,3}};
        for (int[] move : moves){
            assertEquals("No Winner", ticTacToe.play(move[0],move[1]));
        }
    }

    @Test
    public void giveAWinningHorizontalLineShouldBeAXWin(){

        int[][] moves = {{2,2},{1,2},{2,3},{1,1},{2,1}};
        for (int[] move : moves){
            if (move[0] == 2 && move[1] == 3){
                assertEquals("X Wins", ticTacToe.play(move[0],move[1]));
            }else{
                assertEquals("No Winner", ticTacToe.play(move[0],move[1]));
            }
        }

    }
    @Test

    public void giveAWinningHVerticalLineShouldBeAXWin(){
        int[][] moves = {{1,1},{2,2},{1,2},{2,1},{3,3},{2,3}};
        for (int[] move : moves){
            if (move[0] == 2 && move[1]==3){
                assertEquals("O Wins", ticTacToe.play(move[0],move[1]));
            }else{
                assertEquals("No Winner", ticTacToe.play(move[0],move[1]));
            }

        }
    }

    @Test
    public void giveAWinningDiagonalLineShouldBeAXWin(){
        int[][] moves = {{2,2},{1,2},{1,1},{1,3},{3,3}};
        for (int[] move : moves){
            if (move[0] == 3 && move[1]== 3){
                assertEquals("X Wins", ticTacToe.play(move[0],move[1]));
            }else{
                assertEquals("No Winner", ticTacToe.play(move[0],move[1]));
            }
        }
    }
}
