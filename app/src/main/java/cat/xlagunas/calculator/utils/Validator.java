package cat.xlagunas.calculator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xlagunas on 27/04/16.
 */
public class Validator {
    private final static String REGEX_VALIDATOR = "[+-]$";

    private final static String LAST_NUMBER_VALIDATOR = ".([^+-]*)$";

    //Validates that the expression ends wether with a + or - sign (this enables/disables) operational buttons
    public boolean shouldDisableOperators(String expression){
        Pattern p = Pattern.compile(REGEX_VALIDATOR);
        Matcher m = p.matcher(expression);
        return m.find();
    }

    public boolean shouldDisableDecimalSign(String expression) {
        Pattern p = Pattern.compile(LAST_NUMBER_VALIDATOR);
        Matcher m = p.matcher(expression);
        if (m.find()){
            return m.group().contains(".");
        }
        // if there's not a match, a number was not found, and that means we can't have decimal value set
        return false;
    }

}
