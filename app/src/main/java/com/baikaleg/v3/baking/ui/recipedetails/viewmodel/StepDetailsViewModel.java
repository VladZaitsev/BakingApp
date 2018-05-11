package com.baikaleg.v3.baking.ui.recipedetails.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.baikaleg.v3.baking.data.model.Step;

public class StepDetailsViewModel extends ViewModel {

    private MutableLiveData<Step> stepObservable = new MutableLiveData<>();

    public void setStep(Step step) {
        stepObservable.setValue(step);
    }

    public MutableLiveData<Step> getStep() {
        return stepObservable;
    }
}