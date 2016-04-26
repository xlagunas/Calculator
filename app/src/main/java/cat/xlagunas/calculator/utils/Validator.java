package cat.xlagunas.calculator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xlagunas on 27/04/16.
 */
public class Validator {
    private final static String REGEX_VALIDATOR = "[+-]$";

    public boolean validate(String expression){
        Pattern p = Pattern.compile(REGEX_VALIDATOR);
        Matcher m = p.matcher(expression);
        return m.find();
    }
}
