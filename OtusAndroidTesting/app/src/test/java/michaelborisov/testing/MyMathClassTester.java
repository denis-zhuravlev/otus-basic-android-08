package michaelborisov.testing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyMathClassTester {

    private static MyMathClass myMathClass;

    @BeforeClass
    public static void setUp() {
        myMathClass = new MyMathClass();
    }

    @Test
    public void isAdditionCorrect() {

        assertEquals(4, myMathClass.sum(2, 2));
    }


    @Test(expected = IllegalArgumentException.class)
    public void isDevisionCorrect() {

        //assertEquals(4, myMathClass.sum(2, 2));
        assertEquals(0, myMathClass.devise(4, 0));
    }


    @AfterClass
    public static void tearDown() {
        myMathClass = null;
    }
}
