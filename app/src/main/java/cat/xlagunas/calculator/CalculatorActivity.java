package cat.xlagunas.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cat.xlagunas.calculator.presenter.CalculatorPresenter;
import cat.xlagunas.calculator.view.CalculatorView;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    @BindView(R.id.result_text_view)
    TextView displayTextView;

    @BindViews({R.id.button_add, R.id.button_substract, R.id.button_result})
    List<Button> operators;

    public CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        presenter = new CalculatorPresenter(this);
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
        displayTextView.setText(result);
    }

    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };
}
