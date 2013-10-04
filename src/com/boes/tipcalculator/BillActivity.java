package com.boes.tipcalculator;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BillActivity extends Activity implements OnClickListener {

	private static final String KEY_TIP = "tip";

	private Bill mBill;
	
	private EditText mBillAmount;
	private Button m10Percent;
	private Button m15Percent;
	private Button m20Percent;
	private TextView mTipAmount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill);
		
		mBill = new Bill(0);
		mBillAmount = (EditText) findViewById(R.id.etAmount);
		
		m10Percent = (Button) findViewById(R.id.btn10Percent);
		m15Percent = (Button) findViewById(R.id.btn15Percent);
		m20Percent = (Button) findViewById(R.id.btn20Percent);
		
		m10Percent.setOnClickListener(this);
		m15Percent.setOnClickListener(this);
		m20Percent.setOnClickListener(this);
		
		mTipAmount = (TextView) findViewById(R.id.tvTipAmt);
		if (savedInstanceState != null) {
			mTipAmount.setText(savedInstanceState.getString(KEY_TIP));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bill, menu);
		return true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_TIP, mTipAmount.getText().toString());
	}

	public void showTip(double percent) {
		double amount = Double.parseDouble(mBillAmount.getText().toString());
		mBill.setAmount(amount);
		double tip = mBill.calculateTip(percent);
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();		
		mTipAmount.setText(formatter.format(tip));
		
		hideSoftKeyboard(mTipAmount);
	}
	
	private void hideSoftKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn10Percent:
				showTip(Bill.TEN_PERCENT);
				break;
			case R.id.btn15Percent:
				showTip(Bill.FIFTEEN_PERCENT);
				break;
			case R.id.btn20Percent:
				showTip(Bill.TWENTY_PERCENT);
				break;
		}
	}

}
