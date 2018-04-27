package com.baikaleg.v3.baking.dagger;

import android.app.Application;
import android.content.Context;


import com.baikaleg.v3.baking.dagger.modules.RecipesModule;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.ui.recipelist.RecipesActivity;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public interface AppModule {
    @Binds
    Context bindContext(Application application);

    @ActivityScoped
    @ContributesAndroidInjector(modules = {RecipesModule.class})
    RecipesActivity moviesActivityInjector();
} 