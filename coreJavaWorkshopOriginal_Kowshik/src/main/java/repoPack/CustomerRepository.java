package repoPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beansPack.Customer;

public class CustomerRepository {
	   static final String DB_URL = "jdbc:mysql://localhost:3306/coreJavaWorkshop?characterEncoding=utf8";
	   static final String USER = "root";
	   static final String PASS = "Kowshik1111475";

	public void addNewCustomer(Customer c) throws ClassNotFoundException 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL,USER, PASS);

			
			PreparedStatement stmt=con.prepareStatement("insert into customer (custerName , phonenumber,customerPass) values (?,?,?)");
			stmt.setString(1,c.getCustomerName());
			stmt.setBigDecimal(2,c.getCustomerPhone());
			stmt.setString(3,c.getCustomerName());
			if(stmt.executeUpdate() > 0)
			{
				stmt = con.prepareStatement("select customerId from customer where custerName =? and phonenumber =? and customerPass =? ");
				stmt.setString(1,c.getCustomerName());
				stmt.setBigDecimal(2,c.getCustomerPhone());
				stmt.setString(3, c.getCustomerPassword());
				ResultSet rs = stmt.executeQuery();
				int custId = 0;
				while(rs.next())
				{
					custId = rs.getInt(1);
				}
				if(custId!=0)
				{
					c.setCustomerId(custId);
					stmt = con.prepareStatement("insert into address values(?,?,?,?)");
					stmt.setInt(1,custId);
					stmt.setString(2, c.getStreet());
					stmt.setString(3, c.getCity());
					stmt.setInt(4,c.getPincode());
					if(stmt.executeUpdate() > 0)
					{
						c.setCustomerId(custId);
						System.out.println("Added address!");
					}
					else
					{
						System.out.println("Could not add address!");
					}
					
					stmt = con.prepareStatement("insert into customerLogin values(?,?,?)");
					stmt.setInt(1,custId);
					stmt.setString(2, c.getCustomerUsername());
					stmt.setString(3, c.getCustomerPassword());
					if(stmt.executeUpdate() > 0) 
					{
						System.out.println("Created username and password");
					}
				}
			}
			stmt = con.prepareStatement("insert into customer(custerName,phonenumber) values(?,?)");
			stmt.setString(1, c.getCustomerName());
			stmt.setBigDecimal(2, c.getCustomerPhone());

			int i = stmt.executeUpdate();
			if(i > 0)
				System.out.println(" Registration Successfull!! please Login");
			con.close();
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public boolean customerLogin(Customer c) throws ClassNotFoundException 
	{
		try 
		{
		  Class.forName("com.mysql.jdbc.Driver");  
		    Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);  
		    PreparedStatement stmt=conn.prepareStatement("select * from customerLogin where customerUserName =? and customerPassword =?");
		    ResultSet rs = stmt.executeQuery();
		    boolean isLoggedIn = false;
	         while(rs.next())
	         {
	           if(rs.getString(1).equals(c.getCustomerUsername()) && rs.getString(2).equals(c.getCustomerPassword()))
	           {
	        	   System.out.println("Login Successful!!");
	        	   isLoggedIn = true;
	        	   return true;
	           }
	         }
	         if(!isLoggedIn)
	         {
	        	 
	        	 System.out.println("Could not login");
	        	 return false;
	         }
		}
		catch(Exception e) 
		{
			e.getMessage();
		}
		return false;
	}
}
