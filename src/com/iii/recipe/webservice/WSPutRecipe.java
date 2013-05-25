package com.iii.recipe.webservice;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.iii.recipe.R;
import com.iii.recipe.configuration.ConfigurationWS;

public class WSPutRecipe extends AsyncTask<Void, Void, Void> {

	private String title;
	private String mealType;
	private String tasteType;
	private String editDescription;
	private ArrayList<String> ingredients;
	private String edithtmake;
	private ConfigurationWS mWS;
	private Context context;

	private int recipe_id = 0;

	public WSPutRecipe(Context mcontext, String title, String mealType,
			String tasteType, String editDescription,
			ArrayList<String> ingredients, String edithtmake) {
		super();
		this.title = title;
		this.mealType = mealType;
		this.tasteType = tasteType;
		this.editDescription = editDescription;
		this.ingredients = ingredients;
		this.edithtmake = edithtmake;
		this.context = mcontext;
		mWS = new ConfigurationWS(mcontext);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			addRecipe();
			if (recipe_id != 0) {
				String URLAddNewUser = context
						.getString(R.string.wsputingredients);
				for (String ingredient : this.ingredients) {
					JSONObject json = new JSONObject();
					json.put("recipe_id", recipe_id);
					json.put("ingredient", ingredient);
					JSONArray arrItem = new JSONArray();
					arrItem = mWS.connectWSPut_Get_Data(URLAddNewUser, json,
							
							"ingredient");
					if (arrItem != null) {
						JSONObject results = arrItem.getJSONObject(0);
						Log.i("Log : ",
								"Thanh cong: " + results.getString("result"));
					}
				}

			}
		} catch (Exception e) {
			Log.i("Log : ", "Insert INV Detail : " + e.getMessage());
		}
		return null;
	}

	private void addRecipe() {
		try {
			String wsAddRecipe = context.getString(R.string.wsaddrecipe);
			JSONObject json = new JSONObject();
			json.put("title", title);
			json.put("mealType", mealType);
			json.put("tasteType", tasteType);
			json.put("editDescription", editDescription);
			json.put("edithtmake", edithtmake);
			JSONArray arrItem = new JSONArray();
			arrItem = mWS.connectWSPut_Get_Data(wsAddRecipe, json, "recipe");
			if (arrItem != null) {
				JSONObject results = arrItem.getJSONObject(0);
				
				if (results.getInt("result") != 0) {
					recipe_id = results.getInt("result");
				}
				Log.i("Log : ", "Thanh cong: " + results.getString("result"));
			}
		} catch (Exception e) {
			Log.i("Log : ", "Insert INV Detail : " + e.getMessage());
		}
	}

}
