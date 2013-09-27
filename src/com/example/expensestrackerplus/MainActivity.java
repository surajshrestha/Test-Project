package com.example.expensestrackerplus;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabHost = getTabHost();
		

		TabSpec incomeTab = tabHost.newTabSpec("Income");
		incomeTab.setIndicator("Income", null);
		Intent incomeintent = new Intent(MainActivity.this, Income.class);
		incomeTab.setContent(incomeintent);

		TabSpec expensesTab = tabHost.newTabSpec("Expenses");
		expensesTab.setIndicator("Expenses", null);
		Intent expensesintent = new Intent(MainActivity.this, Expenses.class);
		expensesTab.setContent(expensesintent);

		tabHost.addTab(incomeTab);
		tabHost.addTab(expensesTab);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
