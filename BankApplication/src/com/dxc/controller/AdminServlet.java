package com.dxc.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.BankCustomer;

import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IadminService;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	
	IadminService adminservice=new AdminServiceImpl();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String message="";
		String action="";
		String abc="";
		String temp=request.getParameter("btn");
		if(temp!=null)
		{
			action=request.getParameter("btn");
		}
			HttpSession session=request.getSession();
	    if(action.equals("loginasadmin"))
	    {
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		boolean b=adminservice.logindetails(username, password);
		
	
		if(b==true)
		{
			
			message="login sucessful";
			System.out.println(message);
			session.setAttribute("login",message);
			response.sendRedirect("login.jsp");
		}
		else
		{
			message="username or password incorrect";
			session.setAttribute("abc", message);
			response.sendRedirect("view.jsp");
		}
	}
	else if(action.equals("addcustomer"))
			{
				
				int account_no=Integer.parseInt(request.getParameter("account_no"));
				String name=request.getParameter("name");
				double account_balance=Double.parseDouble(request.getParameter("account_balance"));
				String uname=request.getParameter("uname");
				String password=request.getParameter("password");
				
				
				adminservice.addcustomer(account_no, name, account_balance,uname,password);
				abc="customer added successfully!";
				session.setAttribute("abc", abc);
				response.sendRedirect("view.jsp");
			}
			
			else if(action.equals("SearchCustomer"))
			{
				int accno=Integer.parseInt(request.getParameter("account_no"));
				List<BankCustomer> findStatus=adminservice.searchcustomer(accno);
				if(findStatus.isEmpty())
				{
					message="customer not found!";
					session.setAttribute("abc",message);
					response.sendRedirect("view.jsp");
				}
				else
				{

					session.setAttribute("abc", findStatus);
					response.sendRedirect("view.jsp");
					
				}
			}
			else if(action.equals("removeaccount"))
			{
				int accno=Integer.parseInt(request.getParameter("account_no"));
				boolean removeStatus=adminservice.removeaccount(accno);
				if(removeStatus==true)
				{
					message="Account Removed!";
					
				}
				else
				{
					message="Account is not present!";
					
				}
				session.setAttribute("abc", message);
				response.sendRedirect("view.jsp");
			}
			else if(action.equals("update"))
			{
				int accno=Integer.parseInt(request.getParameter("account_no"));
				session.setAttribute("accno", accno);
				String name=request.getParameter("name");
				adminservice.updatecustomerdetails(accno, name);
				abc="customer details updated!";
				session.setAttribute("abc",abc);
				response.sendRedirect("view.jsp");
			}
			else if(action.equals("showbalance"))
			{
				System.out.println("hello everyone");
				int accno=(int)Integer.parseInt(request.getParameter("account_no"));
				System.out.println(accno);
				double d=adminservice.showbalance(accno);
				System.out.println(d);
				session.setAttribute("abc", d);
				response.sendRedirect("view.jsp");
			}
			
		  else  
	      {
		  List<BankCustomer> getall=adminservice.displayallcustomers();
		   System.out.println("hi");
		   session.setAttribute("details", getall);
	       response.sendRedirect("showallcustomers.jsp");
		
	      }

	}

}
