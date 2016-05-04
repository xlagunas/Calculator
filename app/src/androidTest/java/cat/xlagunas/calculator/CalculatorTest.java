package cat.xlagunas.calculator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
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

    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityRule = new ActivityTestRule<>(
            CalculatorActivity.class);

    /**
     * Initial setup for the tests. The user should input the initial operation proposed in the exercise
     */
    @Before
    public void setUp(){
        onView(withId(R.id.button_one)).perform(click());
        onView(withId(R.id.button_two)).perform(click());
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.button_six)).perform(click());
        onView(withId(R.id.button_five)).perform(click());
        onView(withId(R.id.button_substract)).perform(click());
        onView(withId(R.id.button_seven)).perform(click());

        onView(withId(R.id.result_text_view)).check(matches(withText("12+65-7")));

        onView(withId(R.id.button_result)).perform(click());
    }

    /**
     * This test verifies that the user sees the right result after the user has performed an
     * operation, this operation is the one stated in the written exercise
     * then the result should be carried on. This test, checks that rule
     */
    @Test
    public void testDescriptionUseCase(){
        //Check that after the setup operations the result meet whatever we expect
        onView(withId(R.id.result_text_view)).check(matches(withText("70.0")));
    }

    /**
     * As the written exercise states, after performing a computation, if + o - sign is pressed,
     * then the result should be carried on. This test, checks that rule
     */
    @Test
    public void testAnswerCarryOnUseCase(){

        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.button_one)).perform(click());
        onView(withId(R.id.button_six)).perform(click());
        onView(withId(R.id.button_substract)).perform(click());
        onView(withId(R.id.button_two)).perform(click());
        onView(withId(R.id.result_text_view)).check(matches(withText("70.0+16-2")));

        onView(withId(R.id.button_result)).perform(click());

        onView(withId(R.id.result_text_view)).check(matches(withText("84.0")));
    }

    /**
     * This test checks the opposite use case than the previous one. After a computation is made,
     * if the user doesn't carry on, should start a new operation
     */
    @Test
    public void testResetOperationCase() {
        onView(withId(R.id.button_one)).perform(click());
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.button_one)).perform(click());

        onView(withId(R.id.result_text_view)).check(matches(withText("1+1")));

        onView(withId(R.id.button_result)).perform(click());

        onView(withId(R.id.result_text_view)).check(matches(withText("2.0")));
    }
}