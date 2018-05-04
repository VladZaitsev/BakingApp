package com.baikaleg.v3.baking.ui.recipedetails;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baikaleg.v3.baking.data.model.Step;
import com.baikaleg.v3.baking.databinding.FragmentStepDetailsBinding;
import com.baikaleg.v3.baking.ui.recipedetails.viewmodel.StepDetailsViewModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepDetailsFragment extends Fragment implements ExoPlayer.EventListener {
    private static final String TAG = StepDetailsFragment.class.getSimpleName();
    public final static String arg_step = "STEP";
    public static final String minute_key = "minute_key";

    private SimpleExoPlayer exoPlayer;
    private FragmentStepDetailsBinding binding;
    private Step step;
    private long position;

    public static StepDetailsFragment newInstance(Step step) {
        Bundle args = new Bundle();
        args.putParcelable(arg_step, step);
        StepDetailsFragment fragment = new StepDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(minute_key)) {
            position=savedInstanceState.getLong(minute_key);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStepDetailsBinding.inflate(inflater, container, false);
        final StepDetailsViewModel viewModel = new StepDetailsViewModel();

        if (getArguments() != null) {
            step = getArguments().getParcelable(arg_step);
            if (step != null) {
                viewModel.setStep(step);
                binding.setViewModel(viewModel);
                initializePlayer();
            }
        }
        return binding.getRoot();
    }

    public void initializePlayer() {
            if (exoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            binding.itemStepDetailsPlayer.setPlayer(exoPlayer);

            exoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "ClassicalMusicQuiz");
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(step.getVideoURL()), new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.seekTo(position);
        }
    }

    private void releasePlayer() {
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            Log.d(TAG, "onPlayerStateChanged: PLAYING " + exoPlayer.getCurrentPosition());
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            Log.d(TAG, "onPlayerStateChanged: PAUSED" + exoPlayer.getCurrentPosition());
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong(minute_key, exoPlayer.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }
}
