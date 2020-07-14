package com.dxc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dxc.pojos.Mini;

public class UserDaoImpl implements IuserDao{

	private static Connection conn;
	int accn;
	int count=0,var,a;
	private double d1;
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

    
    public boolean logindetails(String user,String password)
    {
            int flag=0;
            PreparedStatement pstmt;
            try {
                pstmt = conn.prepareStatement("select * from banksystem1");
            ResultSet rset=pstmt.executeQuery();
            while(rset.next())
            {
                System.out.println(rset.getString(5));
            if(user.equals(rset.getString(4)) && password.equals(rset.getString(5)))
                flag=1;
            }
            } catch (SQLException e){e.printStackTrace();}
        if(flag==1)
            return true;
        else
            return false;
    }

	@Override
	public String deposite(int accno, double accbal,String s1)
	{
		int flag=0;
		try {
		
			PreparedStatement pstmt2=conn.prepareStatement("select accno from banksystem1 where userid=?");
			pstmt2.setString(1, s1);
			ResultSet rs=pstmt2.executeQuery();
			rs.next();
			accn=rs.getInt(1);
			
			if(accn==accno)
			{
			PreparedStatement pstmt=conn.prepareStatement("update banksystem1 set accbal=accbal+? where accno=?");
			pstmt.setDouble(1, accbal);
			pstmt.setInt(2, accno);
			pstmt.executeUpdate();
			PreparedStatement pstmt1=conn.prepareStatement("insert  into mini values(now(),?,'deposited',?)");
			pstmt1.setDouble(2,accbal);
			pstmt1.setInt(1, accno);
			pstmt1.executeUpdate();
			flag=1;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(flag==1)
		{
			return "successfully deposited";
		}
		else
		{
			return "invalid account number";
		}

	}
public double showbalance(int accno,String s1) {
		int flag=0;
		try {
			 PreparedStatement p=conn.prepareStatement("select accno from banksystem1 where userid=?");
		        
             p.setString(1,s1);
             ResultSet s=p.executeQuery();
       
               s.next();
               accn=s.getInt(1);
   
			if(accn==accno)
			{
		PreparedStatement pstmt=conn.prepareStatement("select accbal from banksystem1 where accno=?");
		pstmt.setInt(1, accno);
		ResultSet rset=pstmt.executeQuery();
		rset.next();
	     d1=rset.getDouble(1);
		flag=1;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 if(flag==1)
		 {
		
		     return d1;
		 }
		 return 0;
	}
		



@Override
public boolean search(int a) 
{
	int flag=0;
	PreparedStatement pmst;
	try {
		pmst = conn.prepareStatement("select * from bankSystem1 ");
		ResultSet rst=pmst.executeQuery();
		while(rst.next())
		{
			if(rst.getInt(1)==a)
			{
				flag=1;
			}
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	if(flag==1)
	{
	return true;
	}
	else
	{
		return false;
	}
}



@Override
public String debitedTo(String Cid,int accno,double accbal)
{
    PreparedStatement pstmt,pstmt1,pstmt2,pstmt3,pstmt4;
    int flag=1;
    try {
        pstmt2=conn.prepareStatement("select * from banksystem1 where userid=?");
        pstmt2.setString(1, Cid);
        ResultSet rset=pstmt2.executeQuery();
        rset.next();
        if(rset.getDouble(3)<=accbal)
        {
            flag=0;
           
        }
        pstmt4=conn.prepareStatement("select name from banksystem1 where accno=?");
        pstmt4.setInt(1, accno);
        ResultSet rs=pstmt4.executeQuery();
        rs.next();
        String s=rs.getString(1);
               
    if(flag==1)
    {
        pstmt = conn.prepareStatement("update banksystem1 set accbal=(accbal+?) where accno=?");
    pstmt.setDouble(1, accbal);
    pstmt.setInt(2, accno);
    pstmt.execute();
    pstmt1 =conn.prepareStatement("update banksystem1 set accbal=(accbal-?) where userid=?");
   
    System.out.println(Cid);
    pstmt1.setDouble(1, accbal);
    pstmt1.setString(2, Cid);
    pstmt1.execute();
    String s1="transfer to   "+s;
    PreparedStatement pstmt5=conn.prepareStatement("insert  into mini values(now(),?,?,?)");
	pstmt5.setDouble(3,accbal);
	pstmt5.setInt(1, accno);
	pstmt5.setString(2, s1);
	
	pstmt5.executeUpdate();
   
   
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    if(flag==1)
    {
        return "amount debited";
    }
    else
    {
        return "Insufficient balance";
    }
   
}


@Override
public String withdraw(int accno, double accbal,String s1) {
	
	double amount=0.0;
	int flag=0;
	try {
		PreparedStatement pstmt1=conn.prepareStatement("select accno from banksystem1 where userid=?");
		pstmt1.setString(1, s1);
		ResultSet rs1=pstmt1.executeQuery();
		rs1.next();
		accn=rs1.getInt(1);
		if(accn==accno)
		{
		PreparedStatement pstmt=conn.prepareStatement("select * from banksystem1 where accno=?");
		pstmt.setInt(1,accno);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			amount=rs.getDouble(3);
			
			if(amount>accbal)
			{
				amount=amount-accbal;
				
				
			}
			else
			{
				return "insufficant balance";
			}
		}
			PreparedStatement pstmt2=conn.prepareStatement("update banksystem1 set accbal=? where accno=?");
			pstmt2.setDouble(1, amount);
			pstmt2.setInt(2, accno);
			pstmt2.executeUpdate();
			PreparedStatement pstmt5=conn.prepareStatement("insert  into mini values(now(),?,'withdrawn',?)");
			pstmt5.setDouble(2,accbal);
			pstmt5.setInt(1, accno);
			pstmt5.executeUpdate();
		flag=1;
		}	
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	if(flag==1)
	{
		return "TRUE";
	}
	else
	{
		return "FALSE";
	}
}



@Override
public boolean changePassword( String user, String n, String cp) 
{
	System.out.println(user);
	System.out.println(n);
	
	try
	{
		if(n.equals(cp))
		{
			PreparedStatement pstmt = conn.prepareStatement("update banksystem1 set password=? where userid=?");
			pstmt.setString(1,cp);
			pstmt.setString(2,user);
			pstmt.execute();
			return true;
		}
	
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
    return false;
}

public List<Mini> printstatement(int accno,String s1)
{
	List<Mini> list =new ArrayList<>();
	try
	{
		PreparedStatement p=conn.prepareStatement("select accno from banksystem1 where name=?");
        
        p.setString(1,s1);
        ResultSet s=p.executeQuery();
  
          s.next();
          accn=s.getInt(1);
          if(accno==accn)
          {
        	  PreparedStatement pstmt1=conn.prepareStatement("select * from mini where accno=?");
              pstmt1.setInt(1, accn);
              ResultSet rs=pstmt1.executeQuery();
              while(rs.next())
              {
                  count=count+1;
              }
		PreparedStatement pstmt=conn.prepareStatement("select * from mini where accno=?");
		pstmt.setInt(1, accno);
		 ResultSet rset=pstmt.executeQuery();
		 var=count-5;
		 while(rset.next())
		 {

			 a=a+1;
             System.out.println(a);
              System.out.println(count);
             if(a>var)
             {
             list.add(new Mini(rset.getDate(1), rset.getInt(2),rset.getString(3), rset.getDouble(4)));
             }
          }
          }
          
		System.out.println(list);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return list;
}

/*public List<Mini> ministatement(String s1) {
    List<Mini> list =new ArrayList<>();
    try {
        PreparedStatement p=conn.prepareStatement("select accno from banksystem1 where name=?");
        
              p.setString(1,s1);
              ResultSet s=p.executeQuery();
        
                s.next();
                accn=s.getInt(1);
    
                PreparedStatement pstmt1=conn.prepareStatement("select * from mini where accno=?");
                pstmt1.setInt(1, accn);
                ResultSet rs=pstmt1.executeQuery();
                while(rs.next())
                {
                    count=count+1;
                }
                PreparedStatement pstmt=conn.prepareStatement("select * from mini where accno=?");
                pstmt.setInt(1, accn);
                ResultSet rset=pstmt.executeQuery();
                var=count-5;
                System.out.println(var);
               while(rset.next())
               {       
                a=a+1;
                System.out.println(a);
                 System.out.println(count);
                if(a>var)
                {
                list.add(new Mini(rset.getDate(1), rset.getInt(2),rset.getString(3), rset.getDouble(4)));
                }
               
              System.out.println(list); 
           
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
           
            return list;
            }*/
        
    







}








	 


