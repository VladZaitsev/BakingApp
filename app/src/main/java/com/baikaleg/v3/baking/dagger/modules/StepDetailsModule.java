package com.baikaleg.v3.baking.dagger.modules;

import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.dagger.scopes.FragmentScoped;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.ui.recipedetails.StepDetailsActivity;
import com.baikaleg.v3.baking.ui.recipedetails.StepDetailsFragment;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModelFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public interface StepDetailsModule {

    @Provides
    @ActivityScoped
    static Recipe provideRecipe(StepDetailsActivity activity) {
        return activity.getIntent().getParcelableExtra(StepDetailsActivity.EXTRA_RECIPE);
    }

    @Provides
    @ActivityScoped
    static int provideSelected(StepDetailsActivity activity) {
        return activity.getIntent().getIntExtra(StepDetailsActivity.EXTRA_SELECTED, 0);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    StepDetailsFragment stepDetailsFragment();
}
