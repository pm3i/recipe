package com.iii.recipe;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Main_tab_Activity extends TabActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab_activity);
		TabHost tabHost = getTabHost();

		TabHost.TabSpec homespec = tabHost.newTabSpec("");
		homespec.setIndicator("",
				getResources().getDrawable(R.drawable.home));
		Intent homeIntent = new Intent(Main_tab_Activity.this, SubmitActivity.class);
		homespec.setContent(homeIntent);
		tabHost.addTab(homespec);

		// Tab 1
		TabSpec tab1 = tabHost.newTabSpec("tab1");
		tab1.setIndicator("", getResources()
				.getDrawable(R.drawable.launch));
		tab1.setContent(new Intent(Main_tab_Activity.this, SubmitActivity.class));
		tabHost.addTab(tab1);

		// Tab 2
		TabSpec tab2 = tabHost.newTabSpec("tab2");
		tab2.setIndicator("", getResources()
				.getDrawable(R.drawable.acong));
		tab2.setContent(new Intent(Main_tab_Activity.this, SubmitActivity.class));
		tabHost.addTab(tab2);

		// tab 3
		TabSpec tab3 = tabHost.newTabSpec("tab3");
		tab3.setIndicator("", getResources()
				.getDrawable(R.drawable.fsatfood));
		tab3.setContent(new Intent(Main_tab_Activity.this, SubmitActivity.class));
		tabHost.addTab(tab3);
	}

}
