import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Created by Stephen West on 28/11/2018.
 *
 * This is test class to test testing framework
 */
public class FooTest {

    private Foo foo;

    @BeforeEach
    public final void before(){
        foo = new Foo();
    }

    @Test
    //@DisabledWhen(UnexpectedException)
    public void whenDoFooThenThrowRuntimeException(){
        foo.doFoo();
    }
}
