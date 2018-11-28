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
}
