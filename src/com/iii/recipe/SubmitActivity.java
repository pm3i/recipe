package com.iii.recipe;

import java.util.ArrayList;

import com.iii.recipe.webservice.WSPutRecipe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SubmitActivity extends Activity {
	private Spinner spnMeal, spnTaste;
	private ArrayAdapter<CharSequence> adapterMeal, adapterTaste;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.submit_activity_layout);

		spnMeal = (Spinner) findViewById(R.id.spnMealType);
		adapterMeal = ArrayAdapter.createFromResource(SubmitActivity.this,
				R.array.meal, android.R.layout.simple_spinner_item);
		adapterMeal
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnMeal.setAdapter(adapterMeal);

		spnTaste = (Spinner) findViewById(R.id.spnTasteType);
		adapterTaste = ArrayAdapter.createFromResource(SubmitActivity.this,
				R.array.taste, android.R.layout.simple_spinner_item);
		adapterTaste
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnTaste.setAdapter(adapterTaste);

		Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initialize();
			}
		});
	}

	private void initialize() {
		String title = ((EditText) findViewById(R.id.edtTitle)).getText()
				.toString();
		String mealType = spnMeal.getSelectedItem().toString();
		String tasteType = spnTaste.getSelectedItem().toString();
		String editDescription = ((EditText) findViewById(R.id.editDescription))
				.getText().toString();
		String edithtmake = ((EditText) findViewById(R.id.edithtmake))
				.getText().toString();

		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Ingredient1");
		arr.add("Ingredient2");
		arr.add("Ingredient3");
		arr.add("Ingredient4");
		arr.add("Ingredient5");
		arr.add("Ingredient6");

		new WSPutRecipe(SubmitActivity.this, title, mealType, tasteType,
				editDescription, arr, edithtmake).execute();

	}

}
