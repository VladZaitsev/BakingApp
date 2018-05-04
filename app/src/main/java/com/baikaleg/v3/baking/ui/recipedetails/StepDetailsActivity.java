package com.baikaleg.v3.baking.ui.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.ActivityStepDetailsBinding;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModel;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class StepDetailsActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_RECIPE = "RECIPE";
    public static final String EXTRA_SELECTED = "SELECTED";
    public static final String selected_key = "selected_key";

    @Inject
    Recipe recipe;

    @Inject
    int selected;

    private int currentStepNum;
    private RecipeDetailsViewModel viewModel;

    @Inject
    RecipeDetailsViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState != null && savedInstanceState.containsKey(selected_key)) {
            currentStepNum = savedInstanceState.getInt(selected_key);
        } else {
            currentStepNum = selected;
        }

        StepDetailsFragment stepDetailsFragment = (StepDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_step_content_layout);
        if(stepDetailsFragment==null){
            stepDetailsFragment = StepDetailsFragment.newInstance(recipe.getSteps().get(currentStepNum));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_step_content_layout, stepDetailsFragment)
                    .commit();
        }

        ActivityStepDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_step_details);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeDetailsViewModel.class);
        viewModel.setSelected(currentStepNum);
        viewModel.setRecipe(recipe);
        binding.setViewModel(viewModel);
        binding.setCallback(new StepsNavigationCallback() {
            @Override
            public void onPrevClick() {
                currentStepNum = currentStepNum - 1;
                viewModel.setSelected(currentStepNum);
                changeStepFragment();
            }

            @Override
            public void onNextClick() {
                currentStepNum = currentStepNum + 1;
                viewModel.setSelected(currentStepNum);
                changeStepFragment();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(selected_key, viewModel.getSelected());
        super.onSaveInstanceState(outState);
    }

    private void changeStepFragment() {
        StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(recipe.getSteps().get(currentStepNum));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_step_content_layout, stepDetailsFragment)
                    .commit();
    }

    public interface StepsNavigationCallback {
        void onPrevClick();

        void onNextClick();
    }
}
