package cat.xlagunas.calculator;

import org.junit.Before;
import org.junit.Test;

import cat.xlagunas.calculator.utils.Validator;

import static org.junit.Assert.assertTrue;

/**
 * Created by xlagunas on 4/05/16.
 */
public class ValidatorUnitTest {
    private Validator validator;

    @Before
    public void setUp(){
        validator = new Validator();
    }

    @Test
    public void validate_disableDecimalSign() throws Exception {
        assertTrue(validator.shouldDisableDecimalSign("123.5"));
    }

    @Test
    public void validate_disableOperatorSigns() throws Exception {
        assertTrue(validator.shouldDisableOperators("123+434+"));
    }

    @Test(expected = NumberFormatException.class)
    public void checkIllegalNumberException() {
        validator.shouldDisableDecimalSign("132..");
    }
}
