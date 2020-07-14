package com.dxc.services;

import java.util.List;

import com.dxc.pojos.Mini;

public interface IUserService {
	public boolean logindetails(String user, String pass);
	public String deposite(int accno, double accbal,String s1);
	public double showbalance(int accno,String s1);
	public boolean search(int a);
	public String debitedTo(String Cid,int accno,double accbal);
	public String withdraw(int accno, double accbal,String s1);
	public boolean changePassword(String user, String n, String cp);
	//public List<Mini> ministatement(String s1);
	public List<Mini> printstatement(int accno,String s1);
}
