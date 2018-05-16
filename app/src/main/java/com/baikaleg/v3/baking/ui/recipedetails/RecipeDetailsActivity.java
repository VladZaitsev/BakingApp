package com.baikaleg.v3.baking.ui.recipedetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class RecipeDetailsActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_RECIPE = "RECIPE";

    @Inject
    RecipeDetailsFragment fragment;

    @Inject
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        RecipeDetailsFragment detailsFragment =
                (RecipeDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (detailsFragment == null) {
            detailsFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, detailsFragment);
            transaction.commit();
        }

        getSupportActionBar().setTitle(recipe.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
