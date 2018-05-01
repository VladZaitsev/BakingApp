package com.baikaleg.v3.baking.dagger;

import android.app.Application;
import android.content.Context;


import com.baikaleg.v3.baking.dagger.modules.RecipeDetailsModule;
import com.baikaleg.v3.baking.dagger.modules.RecipeListModule;
import com.baikaleg.v3.baking.dagger.modules.StepDetailsModule;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.ui.recipedetails.RecipeDetailsActivity;
import com.baikaleg.v3.baking.ui.recipedetails.StepDetailsActivity;
import com.baikaleg.v3.baking.ui.recipelist.RecipeListActivity;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public interface AppModule {
    @Binds
    Context bindContext(Application application);

    @ActivityScoped
    @ContributesAndroidInjector(modules = {RecipeListModule.class})
    RecipeListActivity moviesActivityInjector();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {RecipeDetailsModule.class})
    RecipeDetailsActivity detailsActivityInjector();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {StepDetailsModule.class})
    StepDetailsActivity stepDetailsActivityInjector();
} 