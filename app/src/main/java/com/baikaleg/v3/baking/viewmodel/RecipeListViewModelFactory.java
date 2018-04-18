package com.baikaleg.v3.baking.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.baikaleg.v3.baking.data.Repository;
import com.baikaleg.v3.baking.rx.SchedulersFacade;

import javax.inject.Inject;

public class RecipeListViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;

    private final SchedulersFacade schedulersFacade;

    @Inject
    public RecipeListViewModelFactory(Repository repository, SchedulersFacade schedulersFacade) {
        this.repository = repository;
        this.schedulersFacade = schedulersFacade;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipeListViewModel.class)) {
            return (T) new RecipeListViewModel(repository, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}