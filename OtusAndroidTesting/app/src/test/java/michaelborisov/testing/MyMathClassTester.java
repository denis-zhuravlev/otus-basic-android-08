package michaelborisov.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyMathClassTester {

    private MyMathClass myMathClass;

    @Before
    public void setUp() {
        myMathClass = new MyMathClass();
    }

    @Test
    public void isAdditionCorrect() {

        assertEquals(4, myMathClass.sum(2, 2));
    }

    @After
    public void tearDown() {
        myMathClass = null;
    }
}
