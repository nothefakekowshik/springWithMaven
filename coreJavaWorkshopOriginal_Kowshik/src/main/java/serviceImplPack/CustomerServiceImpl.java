package serviceImplPack;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import beansPack.Address;
import beansPack.Customer;
import exceptionsPack.InvalidPhoneNumber;
import exceptionsPack.InvalidUserName;
import servicesPack.CustomerService;
import uiPack.EcomUI;
import serviceImplPack.CustomerServiceImpl;
import repoPack.CustomerRepository;



public class CustomerServiceImpl implements CustomerService {
	static final String DB_URL = "jdbc:mysql://localhost:3306/coreJavaWorkshop?characterEncoding=utf8";
	   static final String USER = "root";
	   static final String PASS = "Kowshik1111475";
	Scanner scanner = new Scanner(System.in);
	Customer c = new Customer();
	Address a = new Address();
	CustomerRepository cr = new CustomerRepository();
	HashMap<Integer,Integer> cartMap = new HashMap<Integer,Integer>();
	
	public void addNewCustomer()
	{
		try
		{
			Connection con = DriverManager.getConnection(DB_URL,USER, PASS);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your name");
			String customerName = sc.next();
			
			
			System.out.println("Enter your street name");
			String streetName = sc.next();
			
			
			System.out.println("Enter your city name");
			String cityName = sc.next();
			
			
			System.out.println("Enter the pin code");
			int pincode = sc.nextInt();
			
			
			System.out.println("Enter your user name");
			String userName = sc.next();
			
			System.out.println("Enter your phone number");
			BigDecimal customerPhNo = sc.nextBigDecimal();
			if(String.valueOf(customerPhNo).length()!=10)
			{
				throw new InvalidPhoneNumber("Phone number should have 10 digits!");
			}
			
			System.out.println("Enter the password");
			String customerPass = sc.next();
			
			
			
			//checking the username in the database.
			PreparedStatement stmt = con.prepareStatement("select * from customerlogin where customerUserName =? ");
			stmt.setString(1,userName);
			ResultSet rs = stmt.executeQuery();
			int count = 0 ;
			while(rs.next())
			{
				count+=1;
			}
			if(count == 0)
			{
				c.setCustomerName(customerName);
				c.setCustomerUsername(userName);
				c.setCustomerPassword(customerPass);
				c.setCustomerPhone(customerPhNo);
				c.setStreet(streetName);
				c.setPincode(pincode);
				c.setCity(cityName);
				cr.addNewCustomer(c);	
			}
			else
			{
				throw new InvalidUserName("User name already taken!");
			}
		}
		catch(InvalidPhoneNumber e)
		{
			System.out.println(e.getMessage());
		}
		catch(InvalidUserName e)
		{
			System.out.println(e.getMessage());
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void updateCustomerDetails() 
	{


	}

	public boolean customerLogin() 
	{
		boolean ans=false;;
		try 
		{
			System.out.println("Enter your username");
			c.setCustomerUsername(scanner.next());
			System.out.println("Enter your password");
			c.setCustomerPassword(scanner.next());
			ans = cr.customerLogin(c);
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
		return ans;
	}
	public void showCart()
	{
		if(cartMap.size()==0)
		{
			System.out.println("Empty cart!");
		}
		else
		{
			System.out.println("Id of the product \t quantity");
			System.out.println("--------------------------");
			for(Map.Entry<Integer, Integer> curr : cartMap.entrySet())
			{
				System.out.println(curr.getKey() + " -> \t" + curr.getValue());
			}
		}
		EcomUI.showMenu();
	}

}
