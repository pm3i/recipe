package com.iii.recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeDasBroadScreen extends Activity {

	private ImageButton imgDasScreSubmit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dasbroad_screen);
		init();
	}
	private void init() {
		imgDasScreSubmit = (ImageButton) findViewById(R.id.imgDasScreSubmit);
		imgDasScreSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomeDasBroadScreen.this, SubmitActivity.class));
			}
		});
	}
}
