package com.dxc.dao;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.BankCustomer;

import com.mysql.jdbc.Statement;
public class AdminDaoImpl implements IAdminDao {
private static Connection conn;
double d1;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded...");
			
		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank_application?autoReconnect=true&useSSL=false", "root", "root");
			System.out.println("connected to database....");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    @Override
	public boolean logindetails(String user, String pass) {
		try {
			java.sql.Statement stmt=conn.createStatement();
			ResultSet rset=stmt.executeQuery("select * from admin");
			while(rset.next())
			{
				if(rset.getString(1).equals(user) && rset.getString(2).equals(pass))
				{
					return true;
				}
				break;
			}
			
		} catch(Exception e)
		{
			System.out.println("athentication failed");
			e.printStackTrace();
		}
		return false;
	}
	public void addcustomer(int account_no,String name, double account_balance,String uname,String password){
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into banksystem1 values(?,?,?,?,?)");
			pstmt.setInt(1,account_no);
			pstmt.setString(2,name);
			pstmt.setDouble(3,account_balance);
			pstmt.setString(4, uname);
			pstmt.setString(5, password);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<BankCustomer> searchcustomer(int accno)
	{
		List<BankCustomer> list=new ArrayList<>();
		boolean status=false;
		try {
			Statement stmt=(Statement) conn.createStatement();
			ResultSet rs=stmt.executeQuery("select accno from banksystem1");
			while(rs.next())
			{
				if(accno==rs.getInt(1))
				{
					status=true;
				}
				
			}
			if(status==true)
			{
						PreparedStatement pstmt=conn.prepareStatement("select * from banksystem1 where accno=?");
						pstmt.setInt(1, accno);
						ResultSet rset=pstmt.executeQuery();
						while(rset.next())
						{
							list.add(new BankCustomer(rset.getInt(1),rset.getString(2), rset.getInt(3)));
						}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean removeaccount(int accno) {
		boolean status=false;
		try {
			PreparedStatement pstmt=conn.prepareStatement("delete from banksystem1 where accno=?");
			pstmt.setInt(1, accno);
			status=pstmt.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return !status;
		}
	public void updatecustomerdetails(int account_no,String name){
		try {
			PreparedStatement pstmt=conn.prepareStatement("update banksystem1 set name=? where accno=?");
			pstmt.setString(1, name);
			pstmt.setInt(2, account_no);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public double showbalance(int accno) {
		
		try {
		PreparedStatement pstmt=conn.prepareStatement("select accbal from banksystem1 where accno=?");
		pstmt.setInt(1, accno);
		ResultSet rset=pstmt.executeQuery();
		while(rset.next())
		{
			d1=rset.getDouble(1);
			System.out.println(d1);
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return d1;
		}
	   public List<BankCustomer> displayallcustomers() {
		  
		List<BankCustomer> list=new ArrayList<>();
		try {
			java.sql.Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from banksystem1");
			while(rs.next())
			{
				
				list.add(new BankCustomer(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
				System.out.println(list);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

}
