package cat.xlagunas.calculator;

import org.junit.Before;
import org.junit.Test;

import cat.xlagunas.calculator.utils.Calculator;
import cat.xlagunas.calculator.utils.Validator;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    private Calculable calculable;
    private Validator validator;


    @Before
    public void setUp(){
        calculable = new Calculator();
        validator = new Validator();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertTrue(4d == calculable.doCalculation("2+2"));
    }

    @Test
    public void validate_disableDecimalSign() throws Exception {
        assertTrue(validator.shouldDisableDecimalSign("123.5"));
    }

    @Test
    public void validate_disableOperatorSigns() throws Exception {
        assertTrue(validator.shouldDisableOperators("123+434+"));
    }
}