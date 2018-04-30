package com.baikaleg.v3.baking.ui.recipedetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.baikaleg.v3.baking.data.model.Step;
import com.baikaleg.v3.baking.databinding.ItemStepViewBinding;

public class StepView extends RelativeLayout {

    private ItemStepViewBinding binding;

    public StepView(Context context) {
        super(context);
    }

    public StepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        binding = ItemStepViewBinding.inflate(LayoutInflater.from(getContext()), this, false);
    }

    public void setStep(Step step) {

    }
}