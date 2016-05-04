package cat.xlagunas.calculator;

import android.support.test.espresso.ViewAction;
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

    @Before
    public void baseTest(){
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

    @Test
    public void testDescriptionUseCase(){
        //Check that after the setup operations the result meet whatever we expect
        onView(withId(R.id.result_text_view)).check(matches(withText("70.0")));
    }

    @Test
    public void testAnswerCarryOnUseCase(){
        //Check that after an operation, if + or - sign is required, we carry on the previous result
//        + 1 6 - 2
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.button_one)).perform(click());
        onView(withId(R.id.button_six)).perform(click());
        onView(withId(R.id.button_substract)).perform(click());
        onView(withId(R.id.button_two)).perform(click());
        onView(withId(R.id.result_text_view)).check(matches(withText("70.0+16-2")));

        onView(withId(R.id.button_result)).perform(click());

        onView(withId(R.id.result_text_view)).check(matches(withText("84.0")));
    }

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