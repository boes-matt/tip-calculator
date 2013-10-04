package com.boes.tipcalculator;

import java.text.NumberFormat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BillFragment extends Fragment implements OnClickListener {

	private static final String KEY_TIP = "tip";

	private Bill mBill;
	
	private EditText mBillAmount;
	private Button m10Percent;
	private Button m15Percent;
	private Button m20Percent;
	private TextView mTipAmount;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_bill, container, false);
		
		mBill = new Bill(0);
		mBillAmount = (EditText) v.findViewById(R.id.etAmount);
		
		m10Percent = (Button) v.findViewById(R.id.btn10Percent);
		m15Percent = (Button) v.findViewById(R.id.btn15Percent);
		m20Percent = (Button) v.findViewById(R.id.btn20Percent);
		
		m10Percent.setOnClickListener(this);
		m15Percent.setOnClickListener(this);
		m20Percent.setOnClickListener(this);
		
		mTipAmount = (TextView) v.findViewById(R.id.tvTipAmt);
		if (savedInstanceState != null) {
			mTipAmount.setText(savedInstanceState.getString(KEY_TIP));
		}
				
		return v;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
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
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
