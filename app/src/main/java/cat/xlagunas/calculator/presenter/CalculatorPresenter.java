package cat.xlagunas.calculator.presenter;

import cat.xlagunas.calculator.Calculable;
import cat.xlagunas.calculator.utils.Calculator;
import cat.xlagunas.calculator.utils.Validator;
import cat.xlagunas.calculator.view.CalculatorView;

/**
 * Created by xlagunas on 26/04/16.
 */
public class CalculatorPresenter {

    private final CalculatorView view;
    private final Validator validator;
    private final Calculable calculable;

    private boolean isResultDisplayed = true;


    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
        validator = new Validator();
        calculable = new Calculator();
    }

    public void validate(String expression) {
        boolean disableOperators = validator.shouldDisableOperators(expression);
        //check if we should clear the display or keep appending the added value to the full expression
        expression = updateExpression(disableOperators, expression);
        //notify the view of the new expression
        view.onResult(expression);

        //Check if the sign operators must be disabled or not.
        if (disableOperators) {
            view.disableOperators();
            view.enableDecimalOperator();
        } else {
            view.enableOperators();
        }

        if (validator.shouldDisableDecimalSign(expression)){
            view.disableDecimalOperator();
        }

        isResultDisplayed = false;
    }

    public void calculate(String expression) {
        double calculation = calculable.doCalculation(expression);
        view.onResult(String.valueOf(calculation));
        isResultDisplayed = true;
    }

    private String updateExpression(boolean disableOperators, String expression){
        if (isResultDisplayed && !disableOperators) {
            view.onClearCalculation();
            expression = expression.substring(expression.length() - 1);
        }

        return expression;
    }
}
