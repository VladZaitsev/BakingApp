package com.baikaleg.v3.baking.ui.recipelist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.FragmentRecipeListBinding;
import com.baikaleg.v3.baking.ui.recipedetails.RecipeDetailsActivity;
import com.baikaleg.v3.baking.ui.recipelist.adapter.RecipesViewAdapter;
import com.baikaleg.v3.baking.ui.recipelist.viewmodel.RecipeListViewModel;
import com.baikaleg.v3.baking.ui.recipelist.viewmodel.RecipeListViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
@ActivityScoped
public class RecipeListFragment extends DaggerFragment implements RecipeNavigator {

    private FragmentRecipeListBinding binding;
    private RecipesViewAdapter adapter;
    private int rows, columns;

    @Inject
    RecipeListViewModelFactory viewModelFactory;

    @Inject
    public RecipeListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false);
        setViewParameters();

        adapter = createAdapter();
        binding.recipesList.setAdapter(adapter);
        binding.recipesList.setLayoutManager(new GridLayoutManager(getActivity(), columns));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RecipeListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeListViewModel.class);
        subscribeUi(viewModel);
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
        intent.putExtra(RecipeDetailsActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }

    private void subscribeUi(RecipeListViewModel viewModel) {
        viewModel.getRecipes().observe(this, recipes -> {
            if (recipes != null) {
                binding.setIsLoading(false);
                adapter.refreshAdapter(recipes);
            } else {
                binding.setIsLoading(true);
            }
            binding.executePendingBindings();
        });
    }

    private RecipesViewAdapter createAdapter() {
        TypedValue tv = new TypedValue();
        int actionBarHeight = getActivity().getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)
                ? TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics()) : 0;

        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;
        int imageHeight = (getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rows;
        return new RecipesViewAdapter((RecipeNavigator) this, imageWidth, imageHeight);
    }

    private void setViewParameters() {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rows = getResources().getInteger(R.integer.rows_portrait);
            columns = getResources().getInteger(R.integer.columns_portrait);
        } else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rows = getResources().getInteger(R.integer.rows_landscape);
            columns = getResources().getInteger(R.integer.columns_landscape);
        }
    }
}
