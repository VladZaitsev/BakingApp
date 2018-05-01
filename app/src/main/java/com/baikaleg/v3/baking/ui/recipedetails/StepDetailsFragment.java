package com.baikaleg.v3.baking.ui.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.FragmentStepDetailsBinding;
import com.baikaleg.v3.baking.ui.recipedetails.adapter.StepsDetailsPagerAdapter;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModel;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */

@ActivityScoped
public class StepDetailsFragment extends DaggerFragment {

    private FragmentStepDetailsBinding binding;

    @Inject
    RecipeDetailsViewModelFactory viewModelFactory;

    @Inject
    Recipe recipe;

    @Inject
    int selected;

    @Inject
    public StepDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStepDetailsBinding.inflate(inflater, container, false);
        StepsDetailsPagerAdapter adapter = new StepsDetailsPagerAdapter();
        binding.stepDetailsPager.setAdapter(adapter);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RecipeDetailsViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeDetailsViewModel.class);
        viewModel.setRecipe(recipe);
        viewModel.setSelected(selected);
        binding.setViewModel(viewModel);
        binding.stepDetailsPager.setCurrentItem(selected);
    }
}
