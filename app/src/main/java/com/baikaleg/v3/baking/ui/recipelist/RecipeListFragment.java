package com.baikaleg.v3.baking.ui.recipelist;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.RecipeIdlingResource;
import com.baikaleg.v3.baking.dagger.scopes.ActivityScoped;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.FragmentRecipeListBinding;
import com.baikaleg.v3.baking.ui.recipedetails.RecipeDetailsActivity;
import com.baikaleg.v3.baking.ui.recipelist.adapter.RecipesViewAdapter;
import com.baikaleg.v3.baking.ui.recipelist.viewmodel.RecipeListViewModel;
import com.baikaleg.v3.baking.ui.recipelist.viewmodel.RecipeListViewModelFactory;
import com.baikaleg.v3.baking.utils.Constants;
import com.baikaleg.v3.baking.widget.RecipeWidgetProvider;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class RecipeListFragment extends DaggerFragment implements RecipeNavigator {

    private FragmentRecipeListBinding binding;
    private RecipesViewAdapter adapter;
    private int rows, columns;
    private RecipeListViewModel viewModel;
    private RecipeIdlingResource idlingResource;

    @Inject
    RecipeListViewModelFactory viewModelFactory;

    @Inject
    public RecipeListFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeListViewModel.class);
        binding.setViewModel(viewModel);
        subscribeUI();
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(getActivity(), RecipeDetailsActivity.class);
        intent.putExtra(RecipeDetailsActivity.EXTRA_RECIPE, recipe);

        saveToPref(recipe);

        startActivity(intent);
    }

    private void subscribeUI() {
        binding.moviesRefresh.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        viewModel.getRecipes().observe(this, recipes -> {
            if (recipes != null) {
                binding.setIsLoading(false);
                adapter.refreshAdapter(recipes);
            } else {
                binding.setIsLoading(true);
            }
            binding.executePendingBindings();
        });

        viewModel.isDataLoadingError.observe(this, a -> {
            if (a) {
                binding.recipesErrorTxt.setVisibility(View.VISIBLE);
                binding.recipesList.setVisibility(View.GONE);
            } else {
                binding.recipesErrorTxt.setVisibility(View.GONE);
                binding.recipesList.setVisibility(View.VISIBLE);
            }
        });
    }

    private RecipesViewAdapter createAdapter() {
        TypedValue tv = new TypedValue();
        int actionBarHeight = getActivity().getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)
                ? TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics()) : 0;

        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;
        int imageHeight = (getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rows;
        return new RecipesViewAdapter(this, imageWidth, imageHeight);
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

    private void saveToPref(Recipe recipe) {
        SharedPreferences sp = getActivity().getSharedPreferences(
                Constants.SP, getContext().MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        sp.edit().putString(Constants.RECIPE_PREF, json).apply();
        ComponentName widget = new ComponentName(getActivity(), RecipeWidgetProvider.class);
        int[] appWidgetIds = AppWidgetManager.getInstance(getActivity().getApplicationContext()).getAppWidgetIds(widget);
        for (int id : appWidgetIds) {
            RecipeWidgetProvider.updateWidget(getContext(), AppWidgetManager.getInstance(getActivity()), sp, id);
        }
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new RecipeIdlingResource();
            viewModel.setIdlingResource(idlingResource);
        }
        return idlingResource;
    }
}
