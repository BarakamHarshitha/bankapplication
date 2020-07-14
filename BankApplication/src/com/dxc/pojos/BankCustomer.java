package com.dxc.pojos;

public class BankCustomer {
	int account_no;
	String name;
	double account_balance;
	private String uname;
	private String password;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BankCustomer() {
		
	}
	
	public BankCustomer(int account_no, String name, double account_balance) {
		super();
		this.account_no = account_no;
		this.name = name;
		this.account_balance = account_balance;
	
	}
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}
	@Override
	public String toString() {
		return "BankCustomer [account_no=" + account_no + ", name=" + name + ", account_balance=" + account_balance
				+ "]";
	}
	
	

}
