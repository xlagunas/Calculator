package cat.xlagunas.calculator.view;

/**
 * Created by xlagunas on 26/04/16.
 */
public interface CalculatorView {
    void disableOperators();
    void enableOperators();
    void onResult(String result);
    void onClearCalculation();
}
