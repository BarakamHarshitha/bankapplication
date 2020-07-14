package com.dxc.pojos;

import java.sql.Date;

public class Mini 
{
	private Date date;
	private int accno;
	private String operation;
	private double amount;
	
	
	public Mini()
	{
		
	}


	public Mini(Date date, int accno, String operation, double amount) {
		super();
		this.date = date;
		this.accno = accno;
		this.operation = operation;
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getAccno() {
		return accno;
	}


	public void setAccno(int accno) {
		this.accno = accno;
	}


	public String getOperation() {
		return operation;
	}


	public void setOperation(String operation) {
		this.operation = operation;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
