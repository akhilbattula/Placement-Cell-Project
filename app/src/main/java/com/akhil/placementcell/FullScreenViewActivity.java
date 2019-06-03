package com.akhil.placementcell;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.akhil.placementcell.adapters.GridViewImageAdapter;
import com.akhil.placementcell.helper.Utils;

public class FullScreenViewActivity extends Activity{

	private Utils utils;

	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen_view);

		// get intent data
		Intent i = getIntent();

		// Selected image id
		int position = i.getExtras().getInt("id");
		GridViewImageAdapter imageAdapter = new GridViewImageAdapter(this);

		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageResource(imageAdapter.mThumbIds[position]);
	}

}