package com.baikaleg.v3.baking.dagger.modules;

import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.dagger.scopes.FragmentScoped;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.ui.recipedetails.RecipeDetailsActivity;
import com.baikaleg.v3.baking.ui.recipedetails.RecipeDetailsFragment;
import com.baikaleg.v3.baking.ui.recipedetails.StepDetailsFragment;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModelFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public interface RecipeDetailsModule {

    @Provides
    @ActivityScoped
    static Recipe provideRecipe(RecipeDetailsActivity activity) {
        return activity.getIntent().getParcelableExtra(RecipeDetailsActivity.EXTRA_RECIPE);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    RecipeDetailsFragment recipeDetailsFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    StepDetailsFragment stepDetailsFragment();

    @ActivityScoped
    @Provides
    static RecipeDetailsViewModelFactory provideDetailsListViewModelFactory() {
        return new RecipeDetailsViewModelFactory();
    }
}
