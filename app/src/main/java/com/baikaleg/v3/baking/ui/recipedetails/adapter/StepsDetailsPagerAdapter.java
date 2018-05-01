package com.baikaleg.v3.baking.ui.recipedetails.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Step;
import com.baikaleg.v3.baking.databinding.ItemStepDetailsViewBinding;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.StepDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class StepsDetailsPagerAdapter extends PagerAdapter {
    private static final String TAG = StepsDetailsPagerAdapter.class.getSimpleName();

    private List<Step> steps = new ArrayList<>();

    @Override
    public int getCount() {
        return steps.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        ItemStepDetailsViewBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_step_details_view, container, false);

        final StepDetailsViewModel viewModel = new StepDetailsViewModel();
        viewModel.setStep(steps.get(position));
        binding.setViewModel(viewModel);

        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView((View) object);
        } catch (Exception e) {
            Log.i(TAG, "failed to destroy reviewItem");
        }
    }

    public void refreshAdapter(List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }
}