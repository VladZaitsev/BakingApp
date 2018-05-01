package com.baikaleg.v3.baking.ui.recipedetails;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.baikaleg.v3.baking.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class StepDetailsActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_RECIPE= "RECIPE";
    public static final String EXTRA_SELECTED= "SELECTED";

    @Inject
    StepDetailsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StepDetailsFragment stepDetailsFragment =
                (StepDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (stepDetailsFragment == null) {
            stepDetailsFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, stepDetailsFragment);
            transaction.commit();
        }
    }

}
