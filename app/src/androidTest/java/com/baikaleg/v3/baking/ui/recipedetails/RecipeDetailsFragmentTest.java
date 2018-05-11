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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsFragmentTest {
    private static final String TEST_EXTRA_RECIPE = "RECIPE";
    private Recipe testRecipe;

    @Rule
    public final ActivityTestRule<RecipeDetailsActivity> activityTestRule =
            new ActivityTestRule<RecipeDetailsActivity>(RecipeDetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    testRecipe = TestUtils.createRecipe();
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent intent = new Intent(targetContext, RecipeDetailsActivity.class);
                    intent.putExtra(TEST_EXTRA_RECIPE, testRecipe);
                    return intent;
                }
            };

    @Before
    public void setupFragment() {

        RecipeDetailsFragment detailsFragment =
                (RecipeDetailsFragment) activityTestRule.getActivity()
                        .getSupportFragmentManager().findFragmentById(R.id.fragment);
        FragmentTransaction transaction = activityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, detailsFragment);
        transaction.commit();
    }

    @Test
    public void verifyIngredientsList() {
        onView(withText(testRecipe.getIngredients().get(0).getIngredient())).check(matches(isDisplayed()));
        onView(withText(testRecipe.getIngredients().get(1).getIngredient())).check(matches(isDisplayed()));
        onView(withText(testRecipe.getIngredients().get(2).getIngredient())).check(matches(isDisplayed()));
    }
}