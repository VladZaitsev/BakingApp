package com.baikaleg.v3.baking.ui.recipedetails.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class RecipeDetailsViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    public RecipeDetailsViewModelFactory() {

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipeDetailsViewModel.class)) {
            return (T) new RecipeDetailsViewModel();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}