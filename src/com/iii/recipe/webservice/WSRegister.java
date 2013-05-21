package com.iii.recipe.webservice;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.iii.recipe.R;
import com.iii.recipe.configuration.ConfigurationWS;

public class WSRegister extends AsyncTask<Void, Void, Void>{
	
	private String username;
	private String password;
	private String address;
	private String country;
	private String email;
	private String phone;
	private int sex;
	private ConfigurationWS mWS;
	private Context context;


	public WSRegister(Context mcontext, String username, String password, String address,
			String country, String email, String phone, int sex) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.sex = sex;
		this.context= mcontext;
		
		mWS = new ConfigurationWS(mcontext);
	}


	@Override
	protected Void doInBackground(Void... params) {
		try {

			String URLAddNewUser = context.getString(R.string.wsregister);
			JSONObject json = new JSONObject();
			json.put("username", username);
			json.put("password", password);
			json.put("address", address);
			json.put("country", country);
			json.put("email", email);
			json.put("phone", phone);
			json.put("sex", sex);
			
			JSONArray arrItem = new JSONArray();
			arrItem = mWS.connectWSPut_Get_Data(URLAddNewUser,
					json, "a");
			if (arrItem != null) {
				JSONObject results = arrItem.getJSONObject(0);
				Log.i("Log : ", "Thanh cong: " + results.getString("result"));
			}
		} catch (Exception e) {
			Log.i("Log : ", "Insert INV Detail : " + e.getMessage());
		}
		return null;
	}

}
