package com.boes.tipcalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class BillActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

		if (fragment == null) {
			fragment = createFragment();
			fm.beginTransaction()
			.add(R.id.fragmentContainer, fragment)
			.commit();
		}
	}

	private Fragment createFragment() {
		return new BillFragment();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bill, menu);
		return true;
	}

}
