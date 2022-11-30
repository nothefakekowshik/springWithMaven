package serviceImplPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import servicesPack.CustomerService;
import servicesPack.ProductService;
import uiPack.EcomUI;

public class ProductServiceImpl implements ProductService{

	public void displayProducts() 
	{
		Scanner sc = new Scanner(System.in);
		CustomerServiceImpl cObj = new CustomerServiceImpl();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coreJavaWorkshop?characterEncoding=utf8","root","Kowshik1111475");
			PreparedStatement stmt=con.prepareStatement("select * from product");
			ResultSet rs = stmt.executeQuery();
			System.out.println("List of products");
			System.out.println("--Id-- \t --Name-- \t --Price-- \t --Type--");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+" \t"+rs.getString(4));
			}
			while(true)
			{
				System.out.println("Do you want to buy anything (Y/N)? Click Q to exit");
				String ch= sc.next();
				if(ch.equalsIgnoreCase("y"))
				{
					System.out.println("Enter the id that you want to buy");
					int id = sc.nextInt();
					stmt=con.prepareStatement("select * from product");
					rs = stmt.executeQuery();
					while(rs.next())
					{
						if(rs.getString(1).equals(String.valueOf(id)))
						{
							cObj.cartMap.put(id,cObj.cartMap.getOrDefault(id, 0) + 1);
							System.out.println("Added");
						}
					}
				}
				else if(ch.equalsIgnoreCase(String.valueOf("Q")))
				{
					break;
				}
				else if(ch.equalsIgnoreCase("N"))
				{
					System.out.println("Ok,check out the menu.");
					break;
				}
			}
			EcomUI.showMenu();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
