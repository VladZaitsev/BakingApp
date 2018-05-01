package com.baikaleg.v3.baking.ui.recipedetails.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.baikaleg.v3.baking.data.model.Step;

public class StepDetailsViewModel extends ViewModel {

    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableField<String> videoURL = new ObservableField<>();
    public final ObservableField<String> thumbnailURL = new ObservableField<>();

    public void setStep(Step step) {
        description.set(step.getDescription());
        videoURL.set(step.getVideoURL());
        thumbnailURL.set(step.getThumbnailURL());
    }
}