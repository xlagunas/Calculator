package cat.xlagunas.calculator.utils;

import cat.xlagunas.calculator.Calculable;

/**
 * Created by xlagunas on 27/04/16.
 */
public class Calculator implements Calculable {
    // zero-width positive lookahead, that splits the string in substrings
    // whenever it finds an add or substract character. As it is a positive lookahead,
    // it includes the sign in the resulting substring
    private final static String SPLITTER_REGEX = "(?=[-+])";

    public Calculator() {
    }

    @Override
    public double doCalculation(String expression) throws NumberFormatException {
        String[] splittedOperation = expression.split(SPLITTER_REGEX);

        double addition = 0;
        for (String value : splittedOperation) {
            // for this implementation we accept "." as 0." value which should be computable
            // so a small transformation should be done just
            if (value.length() == 1 && ".".equals(value)){
                value = "0.";
            }

            addition += Double.parseDouble(value);
        }

        return addition;
    }
}
