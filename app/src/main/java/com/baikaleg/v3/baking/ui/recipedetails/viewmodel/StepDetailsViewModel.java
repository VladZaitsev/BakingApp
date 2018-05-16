package com.baikaleg.v3.baking.ui.recipedetails.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.baikaleg.v3.baking.data.model.Step;

public class StepDetailsViewModel extends ViewModel {

    private MutableLiveData<Step> stepObservable = new MutableLiveData<>();

    private ObservableField<Boolean> isStepChanged = new ObservableField<>();

    public void setStep(Step step) {
        isStepChanged.set(false);
        stepObservable.setValue(step);
    }

    public void changeStep(Step step) {
        isStepChanged.set(true);
        stepObservable.setValue(step);
    }

    public MutableLiveData<Step> getStep() {
        return stepObservable;
    }

    /**
     * For detecting of device rotation
     */
    public Boolean getIsStepChanged() {
        return isStepChanged.get();
    }
}