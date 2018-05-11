package com.baikaleg.v3.baking.ui.recipelist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.RecipeIdlingResource;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.ui.recipelist.viewmodel.RecipeListViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@ActivityScoped
public class RecipeListActivity extends DaggerAppCompatActivity {

    @Inject
    RecipeListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecipeListFragment recipesFragment =
                (RecipeListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (recipesFragment == null) {
            recipesFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, recipesFragment);
            transaction.commit();
        }
    }
}
