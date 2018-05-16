package com.baikaleg.v3.baking.ui.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.baikaleg.v3.baking.R;
import com.baikaleg.v3.baking.data.model.Recipe;
import com.baikaleg.v3.baking.databinding.ActivityStepDetailsBinding;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.StepDetailsViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class StepDetailsActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_RECIPE = "RECIPE";
    public static final String EXTRA_SELECTED = "SELECTED";
    public static final String STEP_COUNT = "count";

    @Inject
    Recipe recipe;

    @Inject
    int selected;

    private int currentStepNum;
    private StepDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(STEP_COUNT)) {
            currentStepNum = savedInstanceState.getInt(STEP_COUNT);
        } else {
            currentStepNum = selected;
        }
        ActivityStepDetailsBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_step_details);
        binding.setSize(recipe.getSteps().size());

        viewModel = ViewModelProviders.of(this).get(StepDetailsViewModel.class);
        viewModel.getStep().observe(this, step -> {
            StepDetailsFragment stepDetailsFragment = (StepDetailsFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.activity_step_content_layout);
            if (stepDetailsFragment == null) {
                stepDetailsFragment = StepDetailsFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_step_content_layout, stepDetailsFragment)
                        .commit();
            }
            binding.setCount(currentStepNum);
        });
        viewModel.setStep(recipe.getSteps().get(currentStepNum));
        getSupportActionBar().setTitle(recipe.getSteps().get(currentStepNum).getShortDescription());

        binding.activityStepContentLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            void onSwipeLeft() {
                increaseStepCount();
            }

            @Override
            void onSwipeRight() {
                decreaseStepCount();
            }
        });

        binding.setCallback(new StepsNavigationCallback() {
            @Override
            public void onPrevClick() {
                decreaseStepCount();
            }

            @Override
            public void onNextClick() {
                increaseStepCount();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STEP_COUNT, currentStepNum);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface StepsNavigationCallback {
        void onPrevClick();

        void onNextClick();
    }

    private void decreaseStepCount() {
        if (currentStepNum > 0) {
            currentStepNum = currentStepNum - 1;
            viewModel.changeStep(recipe.getSteps().get(currentStepNum));

            getSupportActionBar().setTitle(recipe.getSteps().get(currentStepNum).getShortDescription());
        }
    }

    private void increaseStepCount() {
        if (currentStepNum < recipe.getSteps().size()) {
            currentStepNum = currentStepNum + 1;
            viewModel.changeStep(recipe.getSteps().get(currentStepNum));

            getSupportActionBar().setTitle(recipe.getSteps().get(currentStepNum).getShortDescription());
        }
    }

    //https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures/12938787#12938787
    private class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        OnSwipeTouchListener(Context ctx) {
            gestureDetector = new GestureDetector(ctx, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.performClick();
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int THRESHOLD = 100;
            private static final int VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > THRESHOLD && Math.abs(velocityX) >
                                VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        void onSwipeRight() {
        }

        void onSwipeLeft() {
        }
    }
}
