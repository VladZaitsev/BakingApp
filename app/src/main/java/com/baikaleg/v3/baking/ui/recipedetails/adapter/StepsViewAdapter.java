package com.baikaleg.v3.baking.ui.recipedetails.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Step;
import com.baikaleg.v3.baking.databinding.ItemStepViewBinding;
import com.baikaleg.v3.baking.ui.recipedetails.StepNavigator;

import java.util.ArrayList;
import java.util.List;

public class StepsViewAdapter extends RecyclerView.Adapter<StepsViewAdapter.StepsViewHolder> {

    private List<Step> stepList = new ArrayList<>();
    @Nullable
    private StepNavigator callback;

    public StepsViewAdapter(@Nullable StepNavigator callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStepViewBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_step_view,
                        parent, false);
        binding.setCallback(callback);
        return new StepsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        holder.binding.setStep(stepList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public void refreshAdapter(List<Step> list) {
        stepList.clear();
        stepList.addAll(list);
        notifyDataSetChanged();
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        final ItemStepViewBinding binding;

        StepsViewHolder(ItemStepViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
