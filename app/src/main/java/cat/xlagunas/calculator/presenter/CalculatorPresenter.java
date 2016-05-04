package cat.xlagunas.calculator.presenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cat.xlagunas.calculator.Calculable;
import cat.xlagunas.calculator.Validable;
import cat.xlagunas.calculator.view.CalculatorView;

/**
 * Created by xlagunas on 26/04/16.
 */
public class CalculatorPresenter {

    private final CalculatorView view;
    private final Validable validatable;
    private final Calculable calculable;

    private boolean isResultDisplayed = true;
    private boolean isError = false;

    private List<String> operationsStack;

    public CalculatorPresenter(CalculatorView view, Calculable calculable, Validable validable) {
        this.view = view;
        this.calculable = calculable;
        this.validatable = validable;
    }

    public void onRestoreInstance(String persistedOperation, List<String> operationsStack){
        isResultDisplayed = false;
        isError = "Error!".equals(persistedOperation);

        this.operationsStack = operationsStack;
        validate(persistedOperation);
    }

    public void validate(String expression) {
        boolean disableOperators = validatable.shouldDisableOperators(expression);
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

        if (validatable.shouldDisableDecimalSign(expression)){
            view.disableDecimalOperator();
        }

        isResultDisplayed = false;
    }

    public void calculate(String expression) {
        try {
            double calculation = calculable.doCalculation(expression);
            addExpressionToStack(expression);
            view.onResult(String.valueOf(calculation));
        } catch (NumberFormatException e){
            view.onError();
        } finally {
            isResultDisplayed = true;
        }
    }

    public boolean onBackPressed(){
        if (operationsStack != null && !operationsStack.isEmpty()){
            //pop the last item in the array
            String lastOperation = operationsStack.remove(operationsStack.size()-1);
            view.onResult(lastOperation);
            return true;
        }

        return false;
    }

    private String updateExpression(boolean disableOperators, String expression){
        if (isResultDisplayed && !disableOperators || isError) {
            view.onClearCalculation();
            expression = isError ? "0.0" : expression.substring(expression.length() - 1);
        }

        return expression;
    }

    private void addExpressionToStack(String expression){
        if (operationsStack == null){
            operationsStack = new ArrayList<>();
            operationsStack.add("0.00");
        }
        operationsStack.add(expression);
    }

    public List<String> getOperationsStack() {
        return operationsStack;
    }
}
