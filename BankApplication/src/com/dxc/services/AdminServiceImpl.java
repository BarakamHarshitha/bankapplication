package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.BankCustomer;

public class AdminServiceImpl implements IadminService {
	 IAdminDao dao=new AdminDaoImpl();

	@Override
	public boolean logindetails(String user, String pass) {
	return	dao.logindetails(user, pass);
     }
    @Override
	public void addcustomer(int account_no,String name, double account_balance,String uname,String password) {
		dao.addcustomer(account_no,name,account_balance,uname,password);
	}
    @Override
	public List<BankCustomer> searchcustomer(int accno) {
		return dao.searchcustomer(accno);
		}
    @Override
	public boolean removeaccount(int accno) {
		return dao.removeaccount(accno);
	}
    @Override
	public void updatecustomerdetails(int account_no, String name) {
		dao.updatecustomerdetails(account_no, name);
		}
    @Override
	public double showbalance(int accno) {
		 return dao.showbalance(accno);
		}
    @Override
	public List<BankCustomer> displayallcustomers() {
		
		return dao.displayallcustomers();
	}
}
