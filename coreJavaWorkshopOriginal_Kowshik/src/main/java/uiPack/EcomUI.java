package uiPack;

import java.util.Scanner;

import servicesPack.CustomerService;
import servicesPack.OrderService;
import servicesPack.ProductService;
import serviceImplPack.CustomerServiceImpl;
import serviceImplPack.OrderServiceImpl;
import serviceImplPack.ProductServiceImpl;

public class EcomUI {
	public static void showMenu()
	{
		while(true)
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("===MENU===");
			System.out.println("Enter your option");
			System.out.println("1.See the list of products");
			System.out.println("2.See your cart");
			System.out.println("3.Check out");
			System.out.println("4.Edit order");
			System.out.println("5.Delete order");
			System.out.println("6.Edit your details");
			int option = sc.nextInt();
			if(option == 1)
			{
				ProductService pObj = new ProductServiceImpl();
				pObj.displayProducts();
			}
			else if(option == 2) 
			{
				CustomerServiceImpl cObj = new CustomerServiceImpl();
				cObj.showCart();
			}
			else if(option == 3)
			{
				OrderService OsObj = new OrderServiceImpl();
				OsObj.createOrder();
			}
			else if(option == 4)
			{
				OrderService OsObj = new OrderServiceImpl();
				OsObj.editOrder();
			}
			else if(option == 5)
			{
				OrderService OsObj = new OrderServiceImpl();
				OsObj.deleteOrder();
			}
			else if(option == 6)
			{
				CustomerService cObj = new CustomerServiceImpl();
				cObj.updateCustomerDetails();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CustomerService cs = (CustomerService) new CustomerServiceImpl();
		Scanner sc = new Scanner(System.in);
		
			System.out.println("1.Enter 1 if you are new user and 2 if you are an old customer");
			int option = sc.nextInt();
			try
			{
				if(option == 1)
				{
					cs.addNewCustomer();
				}
				else if(option ==2)
				{
					boolean isLoggedIn = cs.customerLogin();
					if(isLoggedIn)
					{
						showMenu();
					}
				}
				else
				{
					System.out.println("Thank you for shopping, bye!");
				}
			}
			catch(Exception e)
			{
				System.out.println(e+" bye");
			}
	}
}
