package cat.xlagunas.calculator.utils;

import cat.xlagunas.calculator.Calculable;

/**
 * Created by xlagunas on 27/04/16.
 */
public class Calculator implements Calculable {
    // zero-width positive lookahead, that splits the string in substrings
    // whenever it finds add or substract character, upon that, as it is a positive lookahead,
    // it includes the sign in the resulting substring
    private final static String SPLITTER_REGEX = "(?=[-+])";

    public Calculator() {
    }

    @Override
    public double doCalculation(String expression) {
        String[] splittedOperation = expression.split(SPLITTER_REGEX);

        double addition = 0;
        for (String value : splittedOperation) {
            addition += Double.parseDouble(value);
        }

        return addition;
    }
}
