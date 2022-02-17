package com.sky.workbook.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sky.workbook.Activity.LearningTracksActivity;
import com.sky.workbook.Adapter.CourseAdapter;
import com.sky.workbook.Adapter.LearningAdapter;
import com.sky.workbook.Adapter.TrandingAdapter;
import com.sky.workbook.MainActivity;
import com.sky.workbook.R;

import java.util.ArrayList;

/**
 *
 */
public class HomeFragment extends Fragment {
	
	private FrameLayout fragmentContainer;
	private RecyclerView popular_recycler,learning_track_recycler,trending_recycler;
	TextView tvTrack;
	private RecyclerView.LayoutManager layoutManager;
	
	/**
	 * Create a new instance of the fragment
	 */
	public static HomeFragment newInstance(int index) {
		HomeFragment fragment = new HomeFragment();
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = null;

		switch (getArguments().getInt("index", 0) )
		{
			case 0:
				view = inflater.inflate(R.layout.fragment_explore, container, false);
				initExplore(view);
				break;

			case 1:
				view = inflater.inflate(R.layout.fragment_mycourses, container, false);
				initMyCourses(view);
				break;

			case 2:
				view = inflater.inflate(R.layout.fragment_dashboard, container, false);
				initDashboard(view);
				break;

			case 3:
				view = inflater.inflate(R.layout.fragment_doubts, container, false);
				initDoubts(view);
				break;

			case 4:
				view = inflater.inflate(R.layout.fragment_library, container, false);
				initLibrary(view);
				break;

			default:
				view = inflater.inflate(R.layout.fragment_explore, container, false);
				initExplore(view);
				break;
		}

		return view;
	}
	
	/**
	 * Init Explore
	 */
	private void initExplore(View view) {
		final MainActivity homeActivity = (MainActivity) getActivity();
		homeActivity.setToolbarTitle("Welcome to "+homeActivity.getString(R.string.app_name));

		tvTrack = view.findViewById(R.id.tvTrack);
		tvTrack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(homeActivity, LearningTracksActivity.class));
			}
		});

// POPULAR RECYCLER
		popular_recycler = view.findViewById(R.id.popular_recycler_view);
		popular_recycler.setHasFixedSize(true);

		popular_recycler.setLayoutManager(new LinearLayoutManager(homeActivity, LinearLayoutManager.HORIZONTAL, false));
		popular_recycler.setNestedScrollingEnabled(false);

		ArrayList<String> itemsData = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			itemsData.add( "Popular Course " + i);
		}

		CourseAdapter courseAdapter = new CourseAdapter(itemsData);
		popular_recycler.setAdapter(courseAdapter);

// LEARNING TRACK RECYCLER
		learning_track_recycler = view.findViewById(R.id.learning_track_recycler);
		learning_track_recycler.setHasFixedSize(true);

		learning_track_recycler.setLayoutManager(new LinearLayoutManager(homeActivity, LinearLayoutManager.HORIZONTAL, false));
		learning_track_recycler.setNestedScrollingEnabled(false);

		ArrayList<String> itemsData1 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			itemsData1.add( "Course " + i);
		}

		LearningAdapter learningAdapter = new LearningAdapter(itemsData1);
		learning_track_recycler.setAdapter(learningAdapter);


// TRENDING RECYCLER
		trending_recycler = view.findViewById(R.id.trending_recycler);
		trending_recycler.setHasFixedSize(true);

		trending_recycler.setLayoutManager(new LinearLayoutManager(homeActivity, LinearLayoutManager.HORIZONTAL, false));
		trending_recycler.setNestedScrollingEnabled(false);

		ArrayList<String> itemsData2 = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			itemsData2.add( "Trending Course " + i);
		}

		TrandingAdapter trandingAdapter = new TrandingAdapter(itemsData2);
		trending_recycler.setAdapter(trandingAdapter);

	}

	/**
	 * Init My Courses
	 */
	private void initMyCourses(View view) {
		final MainActivity homeActivity = (MainActivity) getActivity();

	}

	/**
	 * Init Dashboard
	 */
	private void initDashboard(View view) {
		final MainActivity homeActivity = (MainActivity) getActivity();

	}

	/**
	 * Init Doubts
	 */
	private void initDoubts(View view) {
		final MainActivity homeActivity = (MainActivity) getActivity();


	}

	/**
	 * Init Library
	 */
	private void initLibrary(View view) {
		final MainActivity homeActivity = (MainActivity) getActivity();

	}
	

	/**
	 * Refresh
	 */
	public void refresh() {
		if (getArguments().getInt("index", 0) > 0 && popular_recycler != null) {
			popular_recycler.smoothScrollToPosition(0);
		}
	}
	
	/**
	 * Called when a fragment will be displayed
	 */
	public void willBeDisplayed() {
		// Do what you want here, for example animate the content
		if (fragmentContainer != null) {
			Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.icon_anim_fade_in);
			fragmentContainer.startAnimation(fadeIn);
		}
	}
	
	/**
	 * Called when a fragment will be hidden
	 */
	public void willBeHidden() {
		if (fragmentContainer != null) {
			Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.icon_anim_fade_out);
			fragmentContainer.startAnimation(fadeOut);
		}
	}
}
