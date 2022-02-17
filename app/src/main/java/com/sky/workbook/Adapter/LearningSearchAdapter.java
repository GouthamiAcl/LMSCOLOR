package com.sky.workbook.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sky.workbook.R;

import java.util.ArrayList;

/**
 *
 */
public class LearningSearchAdapter extends RecyclerView.Adapter<LearningSearchAdapter.ViewHolder> {

	private ArrayList<String> mDataset = new ArrayList<>();
	int[] images = {R.drawable.pngegg,R.drawable.pngegg_a,R.drawable.pngegg_b,R.drawable.pngegg_c,R.drawable.pngegg_d,R.drawable.pngegg,R.drawable.pngegg_a,R.drawable.pngegg_b,R.drawable.pngegg_c,R.drawable.pngegg_d};


	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView mTextView;
		public ImageView imageView;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.layout_item_demo_title);
			imageView = (ImageView) v.findViewById(R.id.ivImage);
		}
	}

	public LearningSearchAdapter(ArrayList<String> dataset) {
		mDataset.clear();
		mDataset.addAll(dataset);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_search_item, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.mTextView.setText(mDataset.get(position));
		holder.imageView.setImageResource(images[position]);

	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}

}
