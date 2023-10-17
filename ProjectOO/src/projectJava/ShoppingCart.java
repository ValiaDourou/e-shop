package projectJava;
import java.util.*;
public class ShoppingCart {
	private ArrayList<ItemOrdered> orderList=new ArrayList<ItemOrdered>();
	private ArrayList<String> orderNameList=new ArrayList<String>();
	private static boolean check=false;
	private static int error=0;
	private static Scanner ShoppingCart=new Scanner(System.in);
	public ShoppingCart()
	{
		
	}
	public void addItemOrdered(Item i,int quantity)
	{
		ItemOrdered order=new ItemOrdered(i,quantity);
		orderList.add(order);
		EShop.updateItemStock(i,-quantity);
		i.setItemStock(-quantity);
	}
	public  void removeItemOrdered(ItemOrdered i)
	{
		int quantity=i.getQuantity();
		if(!check)
		{
		EShop.updateItemStock(i.getItem(),quantity);
		(i.getItem()).setItemStock(quantity);
		}
		else if(check)
		{
		EShop.updateItemStock(i.getItem(),0);
		}
		orderList.remove(i);
		orderNameList.remove(i.getOrderName());
	}
	public void changeItemOrderedQuantity(ItemOrdered i,int quantity)
	{
			if (orderList.contains(i))
			{
				i.setQuantity(quantity);
				EShop.updateItemStock(i.getItem(), -quantity);
			}
	}
	public void showCart(Buyer b)
	{
		for(ItemOrdered i:orderList)
		{
			System.out.println((i.getItem()).getName()+" "+(i.getItem()).getPrice());
		}
		System.out.println("Total: " +calculateNet()+ "€ Shipping cost: "+calculateCourierCost(b)+"€");
	}
	public void clearCart()
	{
		try
		{
			if(orderList.size()>0)
			{
			while(orderList.size()>0)
			{
			removeItemOrdered(orderList.get(0));
			}
			}
			else
			{
				throw new NullPointerException();
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("Your cart is empty\n");
		}
	}
	public void checkout(Buyer b) 

	{
		showCart(b);
		System.out.println("Do you wish to proceed to checkout? (1.yes/2.no)");
		do
		{
			error=0;
		try
		{
			int choice=ShoppingCart.nextInt();
		if(choice==1)
		{
			b.awardBonus();
			check=true;
			clearCart();
		}
		else if(choice==2)
		{
			showCart(b);
		}
		else if((choice!=1)&&(choice!=2))
		{
			throw new OneOrTwoException();
		}
		}
		 catch(OneOrTwoException ex)
	    {
			 System.out.println("The answer must be 1 or 2\n");
	    	error=1;
	    }
		catch(InputMismatchException e)
		{
			System.out.println("You must give a number");
			ShoppingCart.nextLine();
			error=1;
		}
		catch(NoSuchElementException e)
		{
			error=1;
		}
		catch(IllegalStateException e)
		{
			error=1;
		} 
		catch(ConcurrentModificationException e)
		{
			error=0;
		} 
		}while(error==1);
		check=false;
	}
	public double calculateNet()
	{
		double total=0.0;
		for(ItemOrdered i:orderList)
		{
			total=total+(i.getItem()).getPrice()*i.getQuantity();
		}
		return total;
	}
	public double calculateCourierCost(Buyer b)
	{
		double cost=calculateNet()*0.02;
		String category=b.getCategory();
		switch(category)
		{
			case "BRONZE": 
				{
					if((cost<3)&&(cost>0))
					{
						cost=3;
					}
					else if(cost>=3)
					{
					cost=cost;
					}
					else if(cost==0)
					{
					  cost=0;
					}
					break;
				}
			case "SILVER":
			{
				cost=0.5*cost;
				break;
			}
			case "GOLD":
			{
				cost=0;
				break;
		     }
		}
		return cost;
	}
	public ArrayList<ItemOrdered> getOrderList()
	{
		return orderList;
	}
	public ArrayList<String> getOrderNameList()
	{
		for(ItemOrdered i:orderList)
		{
			orderNameList.add(i.getOrderName());
		}
		return orderNameList;
	}
	public static void setCheck(boolean b)
	{
		check=b;
	}
}
