package serviceImplPack;

import java.util.Map;
import java.util.Scanner;


import servicesPack.CustomerService;
import servicesPack.OrderService;

public class OrderServiceImpl implements OrderService{

	public void createOrder() 
	{
		
	}
	public void editOrder() 
	{
		CustomerServiceImpl cObj = new CustomerServiceImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("Your cart has...");
		System.out.println("Product ID -- \t Product Quantity");
		for(Map.Entry<Integer, Integer> curr : cObj.cartMap.entrySet() )
		{
			System.out.println(curr.getKey()+" -> "+curr.getValue());
		}
		System.out.println("Do you want to increment or decrement? (I/D)");
		String IorD = sc.next();
		System.out.println("Enter the product id that you want to edit");
		int id = sc.nextInt();
		if(cObj.cartMap.containsKey(id))
		{
			System.out.println("Editing");
			if(IorD.equalsIgnoreCase("I"))
			{
				cObj.cartMap.put(id, cObj.cartMap.get(id)+1);
			}
			else if(IorD.equalsIgnoreCase("D"))
			{
				cObj.cartMap.put(id, cObj.cartMap.get(id)-1);
			}
			else
			{
				return;
			}
		}
	}

	public void deleteOrder() 
	{
		CustomerServiceImpl cObj = new CustomerServiceImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("Your cart has...");
		System.out.println("Product ID -- \t Product Quantity");
		for(Map.Entry<Integer, Integer> curr : cObj.cartMap.entrySet() )
		{
			System.out.println(curr.getKey()+" -> "+curr.getValue());
		}
		System.out.println("Enter the product id that you want to delete");
		int id = sc.nextInt();
		if(cObj.cartMap.containsKey(id))
		{
			cObj.cartMap.remove(id);
		}
		System.out.println("Deleted the product");	
	}

}
