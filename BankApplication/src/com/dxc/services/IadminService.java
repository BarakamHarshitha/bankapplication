package com.dxc.services;

import java.util.List;

import com.dxc.pojos.BankCustomer;

public interface IadminService {
	public boolean logindetails(String user,String pass);
	public void addcustomer(int account_no,String name, double account_balance,String uname,String password);
	public List<BankCustomer> searchcustomer(int accno);
	public boolean removeaccount(int accno);
	public void updatecustomerdetails(int account_no,String name);
	public double showbalance(int accno);
	public List<BankCustomer> displayallcustomers();

}
