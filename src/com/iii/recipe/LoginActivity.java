package com.iii.recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iii.recipe.configuration.ConfigurationWS;

public class LoginActivity extends Activity implements OnClickListener {
	private Button btnLogin;
	private TextView txtRegister;
	public static String username;
	public static int user_id = 0;

	private Context context = LoginActivity.this;
	private ConfigurationWS mWS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);

		String mystring = new String("Don't have an Account? Sign up");
		SpannableString content = new SpannableString(mystring);
		content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
		txtRegister = (TextView) findViewById(R.id.txtResister);
		txtRegister.setText(content);
		txtRegister.setOnClickListener(this);
		/*--------------------policy for connect to ws-------------*/
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// ---------------------------------------------------------//
		mWS = new ConfigurationWS(context);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
//			Intent intentLogin = new Intent(LoginActivity.this,
//					Main_tab_Activity.class);
//			startActivity(intentLogin);
			
			btnLogin.setEnabled(false);
			String username = ((EditText) findViewById(R.id.edtUsernameLogin))
					.getText().toString().trim();
			String password = ((EditText) findViewById(R.id.edtPasswordLogin))
					.getText().toString().trim();
			login(username, password);
			
			break;

		case R.id.txtResister:
			Intent intentRegister = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(intentRegister);

			break;

		default:
			break;
		}

	}

	private void login(String username, String password) {
		if (putDataLogin(username, password)) {
			Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
			Intent intentLogin = new Intent(LoginActivity.this,
					Main_tab_Activity.class);
			startActivity(intentLogin);
		} else
			btnLogin.setEnabled(true);
	}

	// ----------------use to post and get data ------------------//
	private boolean putDataLogin(String username, String password) {

		JSONObject json = new JSONObject();
		String urlLogin = context.getResources().getString(R.string.wslogin);
		try {
			json.put("username", username);
			json.put("pass", password);

			JSONArray jarr = mWS.connectWSPut_Get_Data(
					"http://117.6.131.222:8090/recipe/wslogin.php", json,
					"posts");
			Log.d("LOGIN: ", "  " + jarr);
			if (jarr != null) {
				for (int i = 0; i < jarr.length(); i++) {
					JSONObject element = jarr.getJSONObject(i);
					String result = element.getString("result");
					if (result.equals("success")) {
						LoginActivity.user_id = element.getInt("user_id");
						return true;
					} else {
						Toast.makeText(context, "Login Failed",
								Toast.LENGTH_SHORT).show();
						return false;
					}
				}
			} else {
				Toast.makeText(context,
						"Connect to server failed, please try again",
						Toast.LENGTH_SHORT).show();
			}

		} catch (JSONException e) {
		}
		return false;
	}
}
