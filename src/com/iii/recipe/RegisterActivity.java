package com.iii.recipe;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iii.recipe.webservice.WSRegister;

public class RegisterActivity extends Activity implements OnClickListener {
	private Spinner spnSex;
	private ArrayAdapter<CharSequence> adaptersex;
	private Button btnRegister;
	private EditText edtUsername, edtPassword, edtConfirm, edtEmail,
			edtBirthday, edtPhone, edtAddress;
	// Ham kiem tra dinh dang Mail
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
					+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout_1);
		//loadUI();
	}

	private void loadUI() {
		spnSex = (Spinner) findViewById(R.id.spnSex);
		adaptersex = ArrayAdapter.createFromResource(RegisterActivity.this,
				R.array.sex, android.R.layout.simple_spinner_item);
		adaptersex
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnSex.setAdapter(adaptersex);

		edtUsername = (EditText) findViewById(R.id.edtUsername);
		edtPassword = (EditText) findViewById(R.id.edtPassword);
		edtConfirm = (EditText) findViewById(R.id.edtConfirmPass);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtBirthday = (EditText) findViewById(R.id.edtBirthday);
		edtPhone = (EditText) findViewById(R.id.edtPhone);
		edtAddress = (EditText) findViewById(R.id.edtAddress);
		// ------------------------Register-----------------------//
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnRegister:
			// Intent intentResult = new Intent();
			// intentResult.putExtra("Username",
			// edtUsername.getText().toString());
			// intentResult.putExtra("Password",
			// edtPassword.getText().toString());

			loadData();
			break;

		default:
			break;
		}

	}

	public void loadData() {
		String username = edtUsername.getText().toString().trim();
		String password = edtPassword.getText().toString().trim();
		String confirm = edtConfirm.getText().toString().trim();
		String email = edtEmail.getText().toString().trim();
		String birthday = edtBirthday.getText().toString().trim();
		String phone = edtPhone.getText().toString().trim();
		String address = edtAddress.getText().toString().trim();

		boolean information = true;

		// ------------------Check Full Data---------------------//
		if ((username.equalsIgnoreCase("")) || (password.equalsIgnoreCase(""))
				|| (confirm.equalsIgnoreCase(""))
				|| (email.equalsIgnoreCase(""))
				|| (birthday).equalsIgnoreCase("")
				|| (phone.equalsIgnoreCase(""))
				|| (address.equalsIgnoreCase(""))) {
			Toast.makeText(RegisterActivity.this,
					"not enought information, please type again",
					Toast.LENGTH_SHORT).show();
			information = false;

		}
		if (information) {
			if ((!password.equals(confirm))) {
				// Toast.makeText(RegisterActivity.this, "Phai khop 2 mat khau",
				// Toast.LENGTH_SHORT).show();
				edtConfirm.setTextColor(Color.RED);

			}

			// --------------Check Email----------------------------//
			if (!checkEmail(email)) {
				Toast.makeText(RegisterActivity.this,
						"Khong dung dinh dang Email", Toast.LENGTH_SHORT)
						.show();

			}

			// ---------------Check Phone-----------------------------//
			if (String.valueOf(phone).length() < 10) {
				Toast.makeText(RegisterActivity.this, "So dien thoai ngan qua",
						Toast.LENGTH_SHORT).show();

			}
			if (String.valueOf(phone).length() > 12) {
				Toast.makeText(RegisterActivity.this, "So dien thoai dai qua",
						Toast.LENGTH_SHORT).show();

			}

			// ----------------Du lieu nhap vao dung-----------------//
			else {
				new WSRegister(RegisterActivity.this, username, password,
						address, "", email, phone, 1).execute();
				resul();
			}
		}
	}

	private boolean checkEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	private void resul() {
		Intent resul = new Intent();
		resul.putExtra("pass", edtPassword.getText().toString());
		resul.putExtra("Username", edtUsername.getText().toString());
		setResult(RESULT_OK, resul);
		finish();
	}

}
