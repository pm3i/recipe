package com.iii.recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainRecipe extends Activity implements OnClickListener{
	private Button btnStarted;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_recipe);
		btnStarted=(Button)findViewById(R.id.btnStarted);
		btnStarted.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnStarted:
			Intent intentStart = new Intent(MainRecipe.this, LoginActivity.class);
			startActivity(intentStart);
			break;
		default:
			break;
		}
		
	}

}
