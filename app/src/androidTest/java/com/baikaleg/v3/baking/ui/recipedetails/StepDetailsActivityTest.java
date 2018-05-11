package com.baikaleg.v3.baking.ui.recipedetails;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.ui.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class StepDetailsActivityTest {
    private static final String TEST_EXTRA_RECIPE = "RECIPE";
    private static final String TEST_EXTRA_SELECTED = "SELECTED";
    private Recipe testRecipe;
    private int selected = 0;

    @Rule
    public ActivityTestRule<StepDetailsActivity> activityTestRule =
            new ActivityTestRule<StepDetailsActivity>(StepDetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    testRecipe = TestUtils.createRecipe();
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, StepDetailsActivity.class);
                    intent.putExtra(TEST_EXTRA_RECIPE, testRecipe);
                    intent.putExtra(TEST_EXTRA_SELECTED, selected);
                    return intent;
                }
            };

    @Before
    public void setup() {
        StepDetailsFragment fragment = (StepDetailsFragment) activityTestRule.getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.activity_step_content_layout);

        FragmentTransaction transaction = activityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_step_content_layout, fragment);
        transaction.commit();
    }

    @Test
    public void testDescriptionFieldCheck() {
        onView(withId(R.id.item_step_details_description)).check(matches(withText(testRecipe.getSteps().get(selected).getDescription())));
    }

    @Test
    public void testNavButtonsClick() {
        onView(withId(R.id.activity_step_next_btn)).perform(click());
        onView(withId(R.id.item_step_details_description)).check(matches(withText(testRecipe.getSteps().get(selected + 1).getDescription())));
        onView(withId(R.id.activity_step_prev_btn)).check(matches(isDisplayed()));

        onView(withId(R.id.activity_step_prev_btn)).perform(click());
        onView(withId(R.id.item_step_details_description)).check(matches(withText(testRecipe.getSteps().get(selected).getDescription())));
        onView(withId(R.id.activity_step_prev_btn)).check(matches(not(isDisplayed())));
    }
}