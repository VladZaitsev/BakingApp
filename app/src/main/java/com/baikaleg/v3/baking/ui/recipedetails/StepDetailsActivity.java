package com.baikaleg.v3.baking.ui.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.ActivityStepDetailsBinding;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.StepDetailsViewModel;

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
    StepDetailsViewModel viewModel;

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
        ActivityStepDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_step_details);

        viewModel = ViewModelProviders.of(this).get(StepDetailsViewModel.class);
        viewModel.getStep().observe(this, step -> {
            StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(step);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_step_content_layout, stepDetailsFragment)
                    .commit();
            if (currentStepNum == 0) {
                binding.activityStepPrevBtn.setVisibility(View.GONE);
            } else if (currentStepNum == recipe.getSteps().size()-1) {
                binding.activityStepNextBtn.setVisibility(View.GONE);
            } else {
                binding.activityStepPrevBtn.setVisibility(View.VISIBLE);
                binding.activityStepNextBtn.setVisibility(View.VISIBLE);
            }
        });
        viewModel.setStep(recipe.getSteps().get(currentStepNum));

        binding.setCallback(new StepsNavigationCallback() {
            @Override
            public void onPrevClick() {
                currentStepNum = currentStepNum - 1;
                viewModel.setStep(recipe.getSteps().get(currentStepNum));
            }

            @Override
            public void onNextClick() {
                currentStepNum = currentStepNum + 1;
                viewModel.setStep(recipe.getSteps().get(currentStepNum));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(selected_key, currentStepNum);
        super.onSaveInstanceState(outState);
    }

    public interface StepsNavigationCallback {
        void onPrevClick();

        void onNextClick();
    }
}
