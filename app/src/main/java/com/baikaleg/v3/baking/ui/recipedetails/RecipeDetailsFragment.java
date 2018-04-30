package com.baikaleg.v3.baking.ui.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.FragmentRecipeDetailsBinding;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModel;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
@ActivityScoped
public class RecipeDetailsFragment extends DaggerFragment {

    private FragmentRecipeDetailsBinding binding;

    @Inject
    Recipe recipe;

    @Inject
    RecipeDetailsViewModelFactory viewModelFactory;

    @Inject
    public RecipeDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentRecipeDetailsBinding.inflate(inflater, container, false);

     /* binding.recipeDetailsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recipeDetailsRecycler);*/
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RecipeDetailsViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeDetailsViewModel.class);
        viewModel.setRecipe(recipe);
        binding.setViewModel(viewModel);
    }
}
