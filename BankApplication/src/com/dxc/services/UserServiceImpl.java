package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.dao.IuserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.Mini;

public class UserServiceImpl implements IUserService {
	 IuserDao dao=new UserDaoImpl();
	public boolean logindetails(String user, String pass) {
		return	dao.logindetails(user, pass);
			}
	@Override
	public String deposite(int accno, double accbal,String s1) {
		return dao.deposite(accno,accbal,s1);
		}
	@Override
	public double showbalance(int accno,String s1) {
		return dao.showbalance(accno,s1);
	}
	public boolean search(int a) {
		return dao.search(a);
	}
	@Override
	public String debitedTo(String Cid,int accno,double accbal) {
		return dao.debitedTo(Cid, accno,accbal);
	}
	@Override
	public String withdraw(int accno, double accbal,String s1) {
		return dao.withdraw(accno,accbal,s1);
	}
	@Override
	public boolean changePassword(String user, String n, String cp) {
		return dao.changePassword(user, n, cp);
	}
	@Override
	public List<Mini> printstatement(int accno,String s1) {
		// TODO Auto-generated method stub
		return dao.printstatement(accno,s1);
	}
	
	/*@Override
	public List<Mini> ministatement(String s1) {
		// TODO Auto-generated method stub
		return dao.ministatement(s1);
	}*/

}
