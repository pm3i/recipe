package com.iii.recipe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SubmitActivity extends Activity{
	private Spinner spnMeal, spnTaste;
	private ArrayAdapter<CharSequence> adapterMeal, adapterTaste;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_activity_layout);
		spnMeal = (Spinner)findViewById(R.id.spnMealType);
		adapterMeal = ArrayAdapter.createFromResource(SubmitActivity.this, R.array.meal, android.R.layout.simple_spinner_item);
		adapterMeal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnMeal.setAdapter(adapterMeal);
		
		
		
		spnTaste = (Spinner)findViewById(R.id.spnTasteType);
		adapterTaste = ArrayAdapter.createFromResource(SubmitActivity.this, R.array.taste, android.R.layout.simple_spinner_item);
		adapterTaste.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTaste.setAdapter(adapterTaste);
	}

}
