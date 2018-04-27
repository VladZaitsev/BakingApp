package com.baikaleg.v3.baking.dagger.modules;

import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.dagger.scopes.FragmentScoped;
import com.baikaleg.v3.baking.data.Repository;
import com.baikaleg.v3.baking.rx.SchedulersFacade;
import com.baikaleg.v3.baking.ui.recipelist.RecipeListFragment;
import com.baikaleg.v3.baking.viewmodel.RecipeListViewModelFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public interface RecipesModule {

    @FragmentScoped
    @ContributesAndroidInjector
    RecipeListFragment recipesListFragment();

    @ActivityScoped
    @Provides
    static RecipeListViewModelFactory provideRecipeListViewModelFactory(Repository repository, SchedulersFacade schedulersFacade) {
        return new RecipeListViewModelFactory(repository, schedulersFacade);
    }
}