package com.boes.tipcalculator;

public class Bill {
	
	// standard tip amounts
	public static final double TEN_PERCENT = 0.10;
	public static final double FIFTEEN_PERCENT = 0.15;
	public static final double TWENTY_PERCENT = 0.20;
	
	private double amount;
	
	public Bill(double amount) {
		this.amount = amount;
	}
	
	/**
	 * 
	 * @param tip The tip percentage
	 * @return The calculated tip amount
	 */
	public double calculateTip(double tip) {
		return amount * tip;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
