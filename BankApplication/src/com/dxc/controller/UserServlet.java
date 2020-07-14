package com.dxc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.Mini;
import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IUserService;
import com.dxc.services.IadminService;
import com.dxc.services.UserServiceImpl;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	  String message="";
		String action="";
		String abc="";
	
	   List<Mini> l=new ArrayList<>();
	  IUserService userservice=new UserServiceImpl();
	
	  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String temp=request.getParameter("btn");
		
		if(temp!=null)
		{
			action=request.getParameter("btn");
			System.out.println(action);
		}
		System.out.println(action);
	
			HttpSession session=request.getSession();
	    if(action.equals("loginasuser")) 
	    {
	    	System.out.println(action);
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		session.setAttribute("cid", username);
		boolean b=userservice.logindetails(username, password);
		System.out.println(b);
	
		if(b)
		{
			
			message="logined sucessful";
			System.out.println(message);
			session.setAttribute("login1",message);
			response.sendRedirect("login1.jsp");
		}
		else
		{
			message="username or password incorrect";
			session.setAttribute("abc", message);
			response.sendRedirect("view.jsp");
		}
	    }
	
	    else if(action.equals("DepositeMoney"))
		{
	    	
			int accno=Integer.parseInt(request.getParameter("account_no"));
			double accbal=Double.parseDouble(request.getParameter("account_balance")); 
			String s1=(String)session.getAttribute("cid");
			String abc=userservice.deposite(accno,accbal,s1);
			
			session.setAttribute("abc",abc);
			response.sendRedirect("view.jsp");
		}
	    
	    else if(action.equals("searchaccount"))
	    {
	    	
	    	int a=Integer.parseInt(request.getParameter("account_no"));
	    	session.setAttribute("accno", a);
	    	
	    	boolean b=userservice.search(a);
	    	if(b==true)
	    	{
	    		response.sendRedirect("transfer.jsp");
	    	}
	    	else
	    	{
	    		message="account number not found";
	    		session.setAttribute("abc", message);
	    		response.sendRedirect("view.jsp");
	    	}
	    }
	    else if(action.equals("debitTo"))
        {
	    	int accno=Integer.parseInt(request.getParameter("account_no"));
	    	double accbal=Double.parseDouble(request.getParameter("account_balance")); 
            String Cid=(String)session.getAttribute("cid");
            System.out.println(Cid);
            String s=userservice.debitedTo(Cid,accno,accbal);
            session.setAttribute("abc", s);
            response.sendRedirect("view.jsp");
        }
	    else if(action.equals("WithdrawMoney"))
	    {
	    	int accno=Integer.parseInt(request.getParameter("account_no"));
			session.setAttribute("acco", accno);
			double accbal=Double.parseDouble(request.getParameter("account_balance")); 
			String s1=(String)session.getAttribute("cid");
			String abc = userservice.withdraw(accno,accbal,s1);
		
			if(abc=="TRUE")
			{
				abc=" amount is debited!";
				session.setAttribute("abc",abc);
				response.sendRedirect("view.jsp");
			}
			else
			{
				session.setAttribute("abc","Invaild  number");
				response.sendRedirect("view.jsp");

				
			}
	    }
	    else if(action.equals("CheckBalance"))
		{
	    	int accno=Integer.parseInt(request.getParameter("account_no"));
	    	
		  
		    System.out.println(accno);
		    String s1=(String)session.getAttribute("cid");
		    System.out.println(s1);
		    
			double d=userservice.showbalance(accno,s1);
			if(d!=0)
			{
			session.setAttribute("abc", d);
			response.sendRedirect("view.jsp");
			}
			else
			{
				abc="invalid account number!";
			session.setAttribute("abc",abc);
			response.sendRedirect("view.jsp");
				
			}
		}
	    
	    else if(action.equals("change"))
	    {
	    	
	    	String user = request.getParameter("old");
			String newPass = request.getParameter("new");
			String cp = request.getParameter("con");
			
			boolean update = userservice.changePassword(user,newPass,cp);
			if(update==true)
			{
				abc = "Password updated";
				session.setAttribute("abc", abc);
				response.sendRedirect("view.jsp");
			}
			else
			{
				abc = "Password mismatched";
				session.setAttribute("abc", abc);
				response.sendRedirect("view.jsp");
				
			}
	    }
	    else if(action.equals("statement"))
	    {
	    	int accn=Integer.parseInt(request.getParameter("accno"));
	    	String s1=(String)session.getAttribute("cid");
	    	List<Mini> list=userservice.printstatement(accn,s1);
	    	session.setAttribute("list", list);
	    	System.out.println(list);
	    	response.sendRedirect("miniprint.jsp");
	    }
	    /*else 
        {
            String s1=(String)session.getAttribute("name");
             l=userservice.ministatement(s1);
             session.setAttribute("list",l);
             System.out.println();
             response.sendRedirect("miniprint.jsp");
        }*/
	
	    
	    }
}

