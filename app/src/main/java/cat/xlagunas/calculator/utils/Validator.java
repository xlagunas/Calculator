package cat.xlagunas.calculator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xlagunas on 27/04/16.
 */
public class Validator {
    private final static String REGEX_VALIDATOR = "[+-]$";

    private final static String LAST_NUMBER_VALIDATOR = ".([^+-]*)$";

    /**
     * Checks that the last character on the mathematical expression is a + or - sign, so the user
     * is not able to add to consecutive add or substract operations
     * @param expression mathematical expression to be evaluated
     * @return true if the last number in the operation is an operation (+ -) sign
     */
    public boolean shouldDisableOperators(String expression){
        Pattern p = Pattern.compile(REGEX_VALIDATOR);
        Matcher m = p.matcher(expression);
        return m.find();
    }

    /**
     * Check that the last character belongs to a number group (e.g. its not a + - sign) and if so,
     * checks whether the decimal point is already set or not.
     * @param expression mathematical expression to be evaluated
     * @return true if the last number in the operation is already a decimal number
     */
    public boolean shouldDisableDecimalSign(String expression) {
        Pattern p = Pattern.compile(LAST_NUMBER_VALIDATOR);
        Matcher m = p.matcher(expression);
        if (m.find()){
            return m.group().contains(".");
        }
        return false;
    }

}
