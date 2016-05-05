package cat.xlagunas.calculator;

/**
 * Created by xlagunas on 5/05/16.
 */
public interface Validable {
    boolean shouldDisableOperators(String expression);
    boolean shouldDisableDecimalSign(String expression);
}