package cat.xlagunas.calculator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xlagunas on 27/04/16.
 */
public class Calculator {

    public Calculator() {
    }

    public double calculate(String expression){
        double calculation = 0;
        //isPositive is the sign of the numeric value, by default first one is set to true
        boolean isPositive = true;

        Pattern p = Pattern.compile("[+-]");
        Matcher matcher = p.matcher(expression);

        while (matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end());
            System.out.println(" Found: " + matcher.group());
        }

        //Fake return value as from now
        return 10;
    }
}
