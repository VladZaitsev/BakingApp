package com.baikaleg.v3.baking.ui.recipedetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.baikaleg.v3.baking.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class RecipeDetailsActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_RECIPE = "RECIPE";

    @Inject
    RecipeDetailsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecipeDetailsFragment detailsFragment =
                (RecipeDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (detailsFragment == null) {
            detailsFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, detailsFragment);
            transaction.commit();
        }
    }

}
