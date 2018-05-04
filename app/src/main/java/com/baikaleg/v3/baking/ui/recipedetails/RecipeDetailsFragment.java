package com.baikaleg.v3.baking.ui.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.data.model.Step;
import com.baikaleg.v3.baking.databinding.FragmentRecipeDetailsBinding;
import com.baikaleg.v3.baking.ui.recipedetails.adapter.StepsViewAdapter;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModel;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.RecipeDetailsViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
@ActivityScoped
public class RecipeDetailsFragment extends DaggerFragment implements StepNavigator {

    private FragmentRecipeDetailsBinding binding;
    private int count;
    private int ingredientLayoutHeight;
    private boolean twoPane;
    private FragmentManager fragmentManager;

    @Inject
    Recipe recipe;

    @Inject
    RecipeDetailsViewModelFactory viewModelFactory;

    @Inject
    public RecipeDetailsFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipeDetailsBinding.inflate(inflater, container, false);
        StepsViewAdapter stepsAdapter = new StepsViewAdapter(this);

        if (getResources().getBoolean(R.bool.isTablet)) {
            twoPane = true;
            StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(recipe.getSteps().get(0));
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.recipe_details_steps_layout, stepDetailsFragment);
            transaction.commit();
        } else {
            binding.recipeDetailsItemIngredientsContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.recipeDetailsItemIngredientsContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ingredientLayoutHeight = binding.recipeDetailsItemIngredientsContent.getHeight(); //height is ready
                }
            });
            binding.recipeDetailsItemStepsContainer.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    count = count + dy;
                    if (count == 0) {
                        binding.recipeDetailsItemIngredientsContent.setVisibility(View.VISIBLE);
                        binding.recipeDetailsItemIngredientsHeader.setVisibility(View.INVISIBLE);
                        if (ingredientLayoutHeight != 0) {
                            binding.recipeDetailsItemIngredientsContainer.setLayoutParams(
                                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ingredientLayoutHeight));
                        }
                    } else {
                        binding.recipeDetailsItemIngredientsContent.setVisibility(View.INVISIBLE);
                        binding.recipeDetailsItemIngredientsHeader.setVisibility(View.VISIBLE);

                        if (ingredientLayoutHeight - count > 60) {
                            binding.recipeDetailsItemIngredientsContainer.setLayoutParams(
                                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ingredientLayoutHeight - count));
                        }
                    }
                }

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                }
            });
        }
        binding.recipeDetailsItemStepsContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeDetailsItemStepsContainer.setAdapter(stepsAdapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RecipeDetailsViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeDetailsViewModel.class);
        viewModel.setRecipe(recipe);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onClick(Step step) {
        if (twoPane) {
            StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(step);
            fragmentManager.beginTransaction()
                    .replace(R.id.recipe_details_steps_layout, stepDetailsFragment)
                    .commit();
        } else {
            Intent intent = new Intent(getActivity(), StepDetailsActivity.class);
            intent.putExtra(StepDetailsActivity.EXTRA_RECIPE, recipe);
            intent.putExtra(StepDetailsActivity.EXTRA_SELECTED, step.getId());
            startActivity(intent);
        }
    }
}
