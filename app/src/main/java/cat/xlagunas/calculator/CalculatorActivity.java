package cat.xlagunas.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.xlagunas.calculator.presenter.CalculatorPresenter;
import cat.xlagunas.calculator.utils.Calculator;
import cat.xlagunas.calculator.utils.Validator;
import cat.xlagunas.calculator.view.CalculatorView;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private final static String DISPLAY_TEXT = "display_text";

    @BindView(R.id.result_text_view)
    TextView displayTextView;

    @BindView(R.id.button_decimal)
    Button decimalButton;

    @BindViews({R.id.button_add, R.id.button_substract, R.id.button_result})
    List<Button> operators;

    public CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        presenter = new CalculatorPresenter(this, new Calculator(), new Validator());
    }

    @OnClick({R.id.button_zero, R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four,
            R.id.button_five, R.id.button_six, R.id.button_seven, R.id.button_eight, R.id.button_nine,
            R.id.button_decimal, R.id.button_add, R.id.button_substract})
    public void onClick(Button button){
        StringBuilder builder = new StringBuilder(displayTextView.getText().toString());
        builder.append(button.getText());

        presenter.validate(builder.toString());
    }

    @OnClick(R.id.button_result)
    public void requestCalculation(){
        presenter.calculate(displayTextView.getText().toString());
    }

    @Override
    public void disableOperators() {
        ButterKnife.apply(operators, ENABLED, false);
    }

    @Override
    public void enableOperators() {
        ButterKnife.apply(operators, ENABLED, true);
    }

    @Override
    public void onResult(String result) {
        displayTextView.setText(String.valueOf(result));
    }

    @Override
    public void onError() {
        displayTextView.setText("Error!");
        disableOperators();
    }

    @Override
    public void onClearCalculation() {
        displayTextView.setText("");
    }

    @Override
    public void enableDecimalOperator() {
        decimalButton.setEnabled(true);
    }

    @Override
    public void disableDecimalOperator() {
        decimalButton.setEnabled(false);
    }

    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DISPLAY_TEXT, displayTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String outputState = savedInstanceState.getString(DISPLAY_TEXT);
        if (outputState != null) {
            displayTextView.setText(outputState);
            presenter.onRestoreInstance(outputState);
        }
    }

    @Override
    public void onBackPressed() {
        //initially delegate the backpress action to the presenter, if it has nothing to do,
        // then handle as system would
        if (!presenter.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
