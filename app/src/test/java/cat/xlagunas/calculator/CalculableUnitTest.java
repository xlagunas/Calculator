package cat.xlagunas.calculator;

import org.junit.Before;
import org.junit.Test;

import cat.xlagunas.calculator.utils.Calculator;
import cat.xlagunas.calculator.utils.Validator;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CalculableUnitTest {

    private Calculable calculable;

    @Before
    public void setUp(){
        calculable = new Calculator();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertTrue(4d == calculable.doCalculation("2+2"));
    }

    @Test(expected = NumberFormatException.class)
    public void checkInvalidExpression(){
        calculable.doCalculation("1+..");
    }
}