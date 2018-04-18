package com.baikaleg.v3.baking.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.viewmodel.RecipeListViewModel;
import com.baikaleg.v3.baking.viewmodel.RecipeListViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

@ActivityScoped
public class RecipesActivity extends DaggerAppCompatActivity {

    @Inject
    RecipeListViewModelFactory viewModelFactory;

    private RecipeListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(RecipeListViewModel.class);
        viewModel.getRecipes().observe(this, recipes -> {
            if (recipes != null) {
                for (int i = 0; i < recipes.size(); i++) {
                    Log.i("recipe", recipes.get(i).getName());
                }
            }
        });

    }

}
