package tictactoe.tddjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Stephen West on 28/11/2018.
 */
public class TicTacToeSpec {

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
}
