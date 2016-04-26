package cat.xlagunas.calculator.presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cat.xlagunas.calculator.view.CalculatorView;

/**
 * Created by xlagunas on 26/04/16.
 */
public class CalculatorPresenter {
    private final static String REGEX_VALIDATOR = "[+-]$";
    private CalculatorView view;


    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
    }

    public void validate(String expression){
        view.onResult(expression);
        Pattern p = Pattern.compile(REGEX_VALIDATOR);
        Matcher m = p.matcher(expression);
        if (m.find()){
            view.disableOperators();
        } else {
            view.enableOperators();
        }
    }

    public void calculate(String expression){
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

        view.onResult("10");
    }
}
