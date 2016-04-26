package cat.xlagunas.calculator.presenter;

import cat.xlagunas.calculator.utils.Calculator;
import cat.xlagunas.calculator.utils.Validator;
import cat.xlagunas.calculator.view.CalculatorView;

/**
 * Created by xlagunas on 26/04/16.
 */
public class CalculatorPresenter {

    private CalculatorView view;
    private Validator validator;
    private Calculator calculator;


    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
        validator = new Validator();
        calculator = new Calculator();
    }

    public void validate(String expression){
        view.onResult(expression);

        if (validator.validate(expression)){
            view.disableOperators();
        } else {
            view.enableOperators();
        }
    }

    public void calculate(String expression){
        String calculation = String.valueOf(calculator.calculate(expression));
        view.onResult(calculation);
    }
}
