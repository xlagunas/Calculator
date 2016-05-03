package cat.xlagunas.calculator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class CalculatorTest {

    private final static String EXPRESSION_STRING = "2+2";
    private final static String EXPECTED_RESULT = "4.0";


    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityRule = new ActivityTestRule<>(
            CalculatorActivity.class);


    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.button_two)).perform(click());
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.button_two)).perform(click());

        onView(withId(R.id.result_text_view)).check(matches(withText(EXPRESSION_STRING)));

        onView(withId(R.id.button_result)).perform(click());

        onView(withId(R.id.result_text_view)).check(matches(withText(EXPECTED_RESULT)));

    }

}