package projectJava;

import java.util.ConcurrentModificationException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import projectJava.Buyer.buyerCategory;

public class Menu {
	private Buyer wantedBuyer;
	private static int error=0;
	private ItemOrdered modifiedItem;
	private static Scanner input=new Scanner(System.in);
	public Menu() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
	{
		emailRequest();
	}
	public void emailRequest() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
	{
		System.out.println("Give your email please: ");
		String email=input.nextLine();
		try
		{
		if(EShop.getEmailList().contains(email))
		{
			for(Buyer b:EShop.getBuyersList())
			{
		if(email.equals(b.getEmail()))
		{
			wantedBuyer=b;
			System.out.println("Welcome " +b.getUserName()+"! Your total points are: "+b.getBonus()+" and your category is: "+b.getCategory());
			System.out.println("*******************************************************************************************************************");
			methodForBuyer();
		}
			}
		}
		if(email.equals(Owner.getEmail()))
		{
			    System.out.println("Welcome "+Owner.getName()+"! You are the owner!");
			    System.out.println("*******************************************************************************************************************");
			    methodForOwner();
		}
		if((!(EShop.getEmailList().contains(email)))&&(!(email.equals(Owner.getEmail()))))
				{
			       throw new NoSuchElementException();
				}
		}
		catch(NoSuchElementException ec)
		{
			System.out.println("This email doesn't exist.\nDo you want to create a new email account? (1.yes/2.no)\n");
			do
			{
			error=0;
			System.out.println("Give a number");
			try
			{
				int choice=input.nextInt();
			if((choice!=1)&&(choice!=2))
			{
				throw new OneOrTwoException();
			}
			else if(choice==2)
			{
				input.nextLine();
				emailRequest();
			}
			else if(choice==1)
			{
				input.nextLine();
				System.out.println("Give your name\n");
				String name=input.nextLine();
				System.out.println("Give an email\n");
				String newEmail=input.nextLine();
				ShoppingCart sc=new ShoppingCart();
				Buyer b=new Buyer(name,newEmail,0,buyerCategory.BRONZE,sc);
				EShop.addBuyer(b);
				emailRequest();
			}
			}
			catch(OneOrTwoException e)
			{
				System.out.println("The answer must be 1 or 2\n");
				error=1;
			} 
			catch(InputMismatchException e)
			{
				input.nextLine();
				error=1;
			}
			}while(error==1);
		}
	}
	public void methodForBuyer() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
	{
		System.out.println(EShop.getEShopName());
		System.out.println("*******************************************************************************************************************");
	    System.out.println("MENU:");
	    System.out.println("1.Browse Store ");
	    System.out.println("2.View Cart ");
	    System.out.println("3.Checkout ");
	    System.out.println("4.Log out ");
	    System.out.println("5.Exit ");
	    System.out.println("*******************************************************************************************************************");
	    error=0;
	    do
	    {
	    	error=0;
	    System.out.println("Give a number");
	    try
	    {
		    int number=input.nextInt();
	    if ((number!=1)&&(number!=2)&&(number!=3)&&(number!=4)&&(number!=5))
	    {
	       throw new OneToFiveException();
	    }
	    switch(number)
	    {
	    case 1:
	    {
	    	browseStore();
	    	break;
	    }
	    case 2:
	    {
	    	viewCart();
	    	break;
	    }
	    case 3:
	    {
	    	Checkout();
	    	break;
	    }
	    case 4:
	    {
	    	LogOut();
	    	break;
	    }
	    case 5:
	    {
	    	Exit();
	    	break;
	    }
	    }
	    }
	    catch(OneToFiveException ex)
	    {
	    	System.out.println("The number must be from 1 to 5 \n");
	    	error=1;
	    }
		catch(InputMismatchException e)
		{
			input.nextLine();
			error=1;
		}
	    }while(error==1);
	}
	public void browseStore() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
	{
		EShop.showCategories();
		EShop.showProductsInCategory();
		EShop.showProduct();
		Item chosenItem=EShop.getItemById(EShop.getProductId());
		if(chosenItem.getItemStock()<=0)
		 {
			 browseStore();
		 }
		else
		{
		System.out.println("Do you want to add this product to your cart? (1.yes/2.no)");
		do
		{
			error=0;
		try
		{
			int choice=input.nextInt();
	    if ((choice!=1)&&(choice!=2))
	    {
	       throw new OneOrTwoException();
	    }
	    else if(choice==1)
		{
			do
			{
				error=0;
				System.out.println("Give the quantity you wish to buy: ");
				try
				{
                int quantity=input.nextInt();
			 if((quantity>chosenItem.getItemStock())||(quantity<0))
			 {
				 throw new QuantityException();
			 }
			 try
			 {
				 if((wantedBuyer.getCart()).getOrderNameList().contains(chosenItem.getName()))
				 {
					 throw new CopyException();
				 }
				 else
				 {
			 wantedBuyer.placeOrder(chosenItem,quantity);
				}
			 }
			 catch(CopyException e)
			 {
				 System.out.println("This item already exists, but the quantity will be updated\n");
				 for(ItemOrdered io:(wantedBuyer.getCart()).getOrderList())
				 {
					 if(io.getOrderName().equals(chosenItem.getName()))
                  {
						 chosenItem.setItemStock(-quantity);
				 (wantedBuyer.getCart()).changeItemOrderedQuantity(io,quantity);
                  }
				  }
			 }
				}
				catch(QuantityException e)
				{
					System.out.println("This quantity is not available \n");
			    	error=1;
				}
				catch(InputMismatchException e)
				{ 
					System.out.println("You must give a number\n");
					input.nextLine();
					error=1;
				}
			}while(error==1);
			 System.out.println("\n\nDo you want to continue browsing the store? (1.yes/2.no)");
				do
				{
					 error=0;
				System.out.println("Give a number");
				try
				{
			    int browse=input.nextInt();
			    if ((browse!=1)&&(browse!=2))
			    {
			       throw new OneOrTwoException();
			    }
		     if(browse==1)
		     {
			 browseStore();
		     }
		     else if(browse==2)
		     {
		    	 viewCart();
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
					input.nextLine();
					error=1;
				}
				}while(error==1);		
		}
		else if(choice==2)
		{
			System.out.println("\n\nDo you want to go back? (1.yes/2.no)");
				do
				{
					 error=0;
				System.out.println("Give a number");
				try
				{
			    int goBack=input.nextInt();
			    if ((goBack!=1)&&(goBack!=2))
			    {
			       throw new OneOrTwoException();
			    }
		     if(goBack==1)
		     {
			    methodForBuyer();
		     }
		     else if(goBack==2)
		     {
		    	 browseStore();
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
					input.nextLine();
					error=1;
				}
				}while(error==1);
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
			input.nextLine();
			error=1;
		}
		}while(error==1);
		}
	}
	public void viewCart() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
	{
		(wantedBuyer.getCart()).showCart(wantedBuyer);
		System.out.println("Do you wish to 1.modify an item, 2.clear your cart, 3.go to checkout or 4.go back? ");
		do
		{
			error=0;
		System.out.println("Give a number");
		try
		{
	    int choice=input.nextInt();
	    if ((choice!=1)&&(choice!=2)&&(choice!=3)&&(choice!=4))
	    {
	       throw new OneToFourException();
	    }
 	    switch(choice)
 	    {
 	    case 1:
 	    {
 	    	modifyItem();
	     break;
	    }
 	    case 2:
 	    {
 	    	System.out.println("Do you want to clear your cart? (1.yes/2.no)");
			do
			{
				error=0;
			System.out.println("Give a number");
			try
			{
		    int clearation=input.nextInt();
		    if ((clearation!=1)&&(clearation!=2))
		    {
		       throw new OneOrTwoException();
		    }
    	    if(clearation==1)
 	    	{
 	    		(wantedBuyer.getCart()).clearCart();
 	    		for(ItemOrdered i:((wantedBuyer).getCart()).getOrderList())
 	    		{
 	    			(i.getItem()).setItemStock(i.getQuantity());
 	    		}
 	    	}
    	    else if(clearation==2)
    	    {
    	    	viewCart();
    	    }
			}
			  catch(OneOrTwoException ex)
		    {
				System.out.println("The answer must be 1 or 2\n");
		    	error=1;
		    }
			catch(InputMismatchException e)
			{
				input.nextLine();
				error=1;
			}
			catch(ConcurrentModificationException e)
			{
				error=0;
			}
			}while(error==1);
 	    	System.out.println("\n\nDo you want to go back? (1.yes/2.no)");
			do
			{
				error=0;
			System.out.println("Give a number");
			try
			{
		    int gogoBack=input.nextInt();
		    if ((gogoBack!=1)&&(gogoBack!=2))
		    {
		       throw new OneOrTwoException();
		    }
		     if(gogoBack==1)
		     {
			    methodForBuyer();
		     }
		     else if(gogoBack==2)
		     {
		    	viewCart();
		     }
			}
			  catch(OneOrTwoException ex)
		    {
				System.out.println("The answer must be 1 or 2\n");
		    	error=1;
		    }
			catch(InputMismatchException e)
			{
				input.nextLine();
				error=1;
			}
			}while(error==1);
    	    break;
 	    }
 	    case 3:
 	    {
 	    	Checkout();
 	    	break;
 	    }
 	    case 4:
 	    {
 	    	System.out.println("\n\nDo you want to go back? (1.yes/2.no)");
 			do
 			{
 				error=0;
 			System.out.println("Give a number");
 			try
 			{
 		    int goBack=input.nextInt();
 		    if ((goBack!=1)&&(goBack!=2))
 		    {
 		       throw new OneOrTwoException();
 		    }
 		     if(goBack==1)
 		     {
 			    methodForBuyer();
 		     }
 		     else if(goBack==2)
 		     {
 		    	viewCart();
 		     }
 			}
 			  catch(OneOrTwoException ex)
 		    {
 				System.out.println("The answer must be 1 or 2\n");
 		    	error=1;
 		    }
 			catch(InputMismatchException e)
			{
				input.nextLine();
				error=1;
			}
 			}while(error==1);
 			break;
 	    }
 	    }
		}
		catch(OneToFourException ex)
	    {
			System.out.println("The number must be 1,2,3 or 4 \n");
	    	error=1;
	    }
		catch(InputMismatchException e)
		{
			input.nextLine();
			error=1;
		}
		}while(error==1);
 	    }
 	    public void modifyItem() throws OneToThreeException, OneToFiveException, OneToFourException, OneOrTwoException
 	    {
 	    	if((wantedBuyer.getCart()).getOrderList().isEmpty())
 	    	{
 	    		System.out.println("Your cart is empty\n");
 	    		methodForBuyer();
 	    	}
 	    	input.nextLine();
 			System.out.println("Choose the item you wish to modify: ");
 		    do
 		    {
 		    error=0;
 		    try
 		    {
 		    	String selected=input.nextLine();
 		    	if((wantedBuyer.getCart()).getOrderNameList().contains(selected))
 		    	{
 		    		for(ItemOrdered i:(wantedBuyer.getCart()).getOrderList())
 		    		{
 		    			if(selected.equals((i.getItem()).getName()))
 		    				{
 		    				modifiedItem=i;
 		    				}
 		    		}
 		    		System.out.println("Do you wish to 1.delete it or 2.change the quantity or 3.go back?");
 		   		do
 		   		{
 		   		error=0;
 		   		System.out.println("Give a number");
 		   		try
 		   		{
 		   	    int modification=input.nextInt();
 		   	    if ((modification!=1)&&(modification!=2)&&(modification!=3))
 		   	    {
 		   	       throw new OneToThreeException();
 		   	    }
 		    	    if(modification==1)
 		    	    {
 		    	    	(wantedBuyer.getCart()).removeItemOrdered(modifiedItem);
 		    	    	System.out.println("Do you wish to go back? (1.yes/2.no)");
 		    	    	do
 		    	    	{
 		    	    	error=0;
 		    	    	try
 		    	    	{
 		    	    		int back=input.nextInt();
 		    	        if(back==1)
 		    	   		{
 		    	   			methodForBuyer();
 		    	   		}
 		    	   		else if(back==2)
 		    	   		{
 		    	   			viewCart();
 		    	   		}
 		    	   		else if((back!=1)&&(back!=2))
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
 							input.nextLine();
 							error=1;
 						}
 		    	   		catch(NoSuchElementException e)
 		    	   		{
 		    	   			error=1;
 		    	   		}
 		    	   		catch(ConcurrentModificationException e)
 		    	   		{
 		    	   			error=0;
 		    	   		} 
 		    	   		}while(error==1);
 		    	    }
 		    	    else if(modification==2)
 		    	    {
 		    	    	System.out.println("Do you want to 1.increase or 2.decrease the quantity or 3.go back? ");
 		 		   		do
 		 		   		{
 		 		   		error=0;
 		 		   		System.out.println("Give a number");
 		 		   		try
 		 		   		{
 		 		   	    int changed=input.nextInt();
 		 		   	    if ((changed!=1)&&(changed!=2)&&(changed!=3))
 		 		   	    {
 		 		   	       throw new OneToThreeException();
 		 		   	    }
 		 		   	    input.nextLine();
 			    	    System.out.println("Type the quantity you wish to add/substract: ");
 			    	    do
 			    	    {
 			    	    	error=0;
 			    	    try
 			    	    {
 			    	    int amountation=input.nextInt();
 			    	    if(((amountation>0)&&(amountation>(modifiedItem.getItem()).getItemStock()))||((amountation<0)&&(Math.abs(amountation)>modifiedItem.getQuantity())))
 			    	    {
 			    	    	throw new QuantityException();
 			    	    }
 			    	    else 
 			    	    {
 			    	    if(changed==1)
 		    	    	{
 			    	    	(modifiedItem.getItem()).setItemStock(-amountation);
 			    	    	(wantedBuyer.getCart()).changeItemOrderedQuantity(modifiedItem,amountation);
 			    	    	System.out.println("Item modified");
 			    	    	viewCart();
 		    	    	}
 			    	    else if(changed==2)
 		    	    	{
 			    	    	(modifiedItem.getItem()).setItemStock(-amountation);
 			    	    	(wantedBuyer.getCart()).changeItemOrderedQuantity(modifiedItem,amountation);
 			    	    	System.out.println("Item modified");
 			    	    	viewCart();
 		    	    	}
 			    	    else if(changed==3)
 			    	    {
 			    	    	modifyItem();
 			    	    }
 			    	    }
 			    	    }
 			    	    catch(QuantityException e)
 			    	    {
 			    	    	System.out.println("Not available quantity, please type a new one");
 			    	    	error=1;
 			    	    }
 			    	    }while(error==1);
 		    	    }
 		 		   	catch(OneToThreeException ex)
 		 			    {
 		 		   	        System.out.println("The number must be 1,2 or 3 \n");
 		 			    	error=1;
 		 			    }
 		 		   	catch(InputMismatchException e)
 						{
 		 		   		System.out.println("You must give a number");
 							input.nextLine();
 							error=1;
 						}
 		 				}while(error==1);
 		 		   		}
 		 		   		
 		    	    else if(modification==3)
 		    	    {
 		    	    	modifyItem();
 		    	    }
 		    	}	
 				catch(OneToThreeException ex)
 			    {
 					System.out.println("The number must be 1,2 or 3 \n");
 			    	error=1;
 			    }
 		   	catch(InputMismatchException e)
					{
 		   		System.out.println("You must give a number");
						input.nextLine();
						error=1;
					}
 				}while(error==1);
 		    }
 		    	else
 		    	{
 		    		throw new NoSuchElementException();
 		    	}
 		    }
 		    catch(NoSuchElementException e)
 		    {
 		    	System.out.println("This item doesn't exist in your order list, please give another item\n");
 		    	error=1;
 		    }
 	    }while(error==1);
 	    }
 	    public void Checkout() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
 	    {
 	    	System.out.println("The cost of your order is: "+Double.toString((wantedBuyer.getCart()).calculateNet())+" and the shipping cost is: "+Double.toString((wantedBuyer.getCart()).calculateCourierCost(wantedBuyer))+".");
 	    	(wantedBuyer.getCart()).checkout(wantedBuyer);
 	    	System.out.println("\n\nDo you want to go back? (1.yes/2.no)");
			do
			{
				error=0;
			System.out.println("Give a number");
			try
			{
		    int goBack=input.nextInt();
		    if ((goBack!=1)&&(goBack!=2))
		    {
		       throw new OneOrTwoException();
		    }
		     if(goBack==1)
		     {
			    methodForBuyer();
		     }
		     else if(goBack==2)
		     {
                 System.out.println("Do you want to 1.logout or 2.go to the menu?\n");
     			do
     			{
     				error=0;
     			System.out.println("Give a number");
     			try
     			{
     		    int menu=input.nextInt();
     		    if ((menu!=1)&&(menu!=2))
     		    {
     		       throw new OneOrTwoException();
     		    }
     		    else if(menu==1)
     		    {
     		    	LogOut();
     		    }
     		    else if(menu==2)
     		    {
     		    	methodForBuyer();
     		    }
     			}
     			catch(OneOrTwoException ex)
    		    {
     				System.out.println("The answer must be 1 or 2\n");
    		    	error=1;
    		    }
     			catch(InputMismatchException e)
					{
						input.nextLine();
						error=1;
					}
    			}while(error==1);
		     }
			}
		    catch(OneOrTwoException ex)
		    {
		    	System.out.println("The answer must be 1 or 2\n");
		    	error=1;
		    }
			catch(InputMismatchException e)
				{
					input.nextLine();
					error=1;
				}
			}while(error==1);	
 	    }
 	    public void LogOut() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
 	    {
 	    	System.out.println("Are you sure you want to logout? (1.yes/2.no)");
			do
			{
				error=0;
			System.out.println("Give a number");
			try
			{
		    int logout=input.nextInt();
		    if ((logout!=1)&&(logout!=2))
		    {
		       throw new OneOrTwoException();
		    }
		    if(logout==1)
		    {
		    	input.nextLine();
		    	emailRequest();
		    }
		    if(logout==2)
		    {
		    	methodForBuyer();
		    }
			}
		    catch(OneOrTwoException ex)
		    {
		    	System.out.println("The answer must be 1 or 2\n");
		    	error=1;
		    }
			catch(InputMismatchException e)
				{
					input.nextLine();
					error=1;
				}
			}while(error==1);
 	    }
 	    public void Exit() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
 	    {
 	    	System.out.println("Are you sure you want to exit? (1.yes/2.no)");
			do
			{
				error=0;
			System.out.println("Give a number");
			try
			{
		    int exit=input.nextInt();
		    if ((exit!=1)&&(exit!=2))
		    {
		       throw new OneOrTwoException();
		    }
		    if(exit==1)
		    {
		    	System.exit(0);
		    }
		    if(exit==2)
		    {
		    	methodForBuyer();
		    }
			}
		    catch(OneOrTwoException ex)
		    {
		    	System.out.println("The answer must be 1 or 2\n");
		    	error=1;
		    }
			catch(InputMismatchException e)
				{
					input.nextLine();
					error=1;
				}
			}while(error==1);
 	    }
 	    public void methodForOwner() throws OneToFiveException,OneOrTwoException, OneToThreeException,OneToFourException
 	    {
 	    	System.out.println(EShop.getEShopName());
 	    	System.out.println("*******************************************************************************************************************");
 	    	System.out.println("MENU:");
 		    System.out.println("1.Browse Store ");
 		    System.out.println("2.Check Status ");
 		    System.out.println("3.Log out ");
 		    System.out.println("4.Exit ");
 		    System.out.println("*******************************************************************************************************************");
 		    do
 		    {
 		    	error=0;
 		    System.out.println("Give a number");
 		    try
 		    {
 			 int number=input.nextInt();
 		    if ((number!=1)&&(number!=2)&&(number!=3)&&(number!=4))
 		    {
 		       throw new OneToFourException();
 		    }
 		    switch(number)
 		    {
 		    case 1:
 		    {
 		    	browseStoreOwner();
 		    	break;
 		    }
 		    case 2:
 		    {
 		    	CheckStatus();
 		    	break;
 		    }
 		    case 3:
 		    {
 		    	LogOutOwner();
 		    	break;
 		    }
 		    case 4:
 		    {
 		    	ExitOwner();
 		    	break;
 		    }
 		    }
 		    }
 		    catch(OneToFourException ex)
 		    {
 		    	System.out.println("The number must be 1,2,3 or 4 \n");
 		    	error=1;
 		    }
 		   catch(InputMismatchException e)
				{
					input.nextLine();
					error=1;
				}
 		    }while(error==1);
 		    
 	    }
 	    public void browseStoreOwner() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
 	    {
 			EShop.showCategories();
 			EShop.showProductsInCategory();
 			EShop.showProduct();
 			System.out.println("Do you want to modify this product? (1.yes/2.no)");
			do
			{
				error=0;
			System.out.println("Give a number");
			try
			{
		    int choice=input.nextInt();
		    if ((choice!=1)&&(choice!=2))
		    {
		       throw new OneOrTwoException();
		    }
 			if(choice==1)
 			{
 				 System.out.println("Give the quantity you wish to modify: (if you want to reduce the quantity of the selected item, please give a negative number) ");
 				 do
 				 {
 					 error=0;
 					 System.out.println("Give a number\n");
 				 try
 				 {
 	                int quantity=input.nextInt();
 				 Item chosenItem=EShop.getItemById(EShop.getProductId());							
 				 if((quantity<0)&&(Math.abs(quantity)>chosenItem.getItemStock()))
 				 {
 					 throw new QuantityException();
 				 }
 				 else
 				 {
 					 EShop.updateItemStock(chosenItem,quantity);
 					 chosenItem.setItemStock(quantity);
 				 }
 				 System.out.println("\n\nDo you want to continue browsing the store? (1.yes/2.no)");
 				do
 				{
 					error=0;
 				System.out.println("Give a number");
 				try
 				{
 			    int browse=input.nextInt();
 			    if ((browse!=1)&&(browse!=2))
 			    {
 			       throw new OneOrTwoException();
 			    }
 			     if(browse==1)
 			     {
 				 browseStoreOwner();
 			     }
 			     else if(browse==2)
 			     {
 			    	 methodForOwner();
 			     }
 				}
 			    catch(OneOrTwoException ex)
 			    {
 			    	System.out.println("The answer must be 1 or 2\n");
 			    	error=1;
 			    }
 				catch(InputMismatchException e)
					{
						input.nextLine();
						error=1;
					}
 				}while(error==1);
 				}
 				catch(QuantityException e)
 				{
 					System.out.println("This quantity is not available \n");
 			    	error=1;
 				}
 				catch(InputMismatchException e)
 				{
 					input.nextLine();
 					error=1;
 				}
 			}while(error==1);
 			}
 			else if(choice==2)
 			{
 				System.out.println("\n\nDo you want to go back? (1.yes/2.no)");
 				do
 				{
 					error=0;
 				System.out.println("Give a number");
 				try
 				{
 			    int goBack=input.nextInt();
 			    if ((goBack!=1)&&(goBack!=2))
 			    {
 			       throw new OneOrTwoException();
 			    }
 			     if(goBack==1)
 			     {
 				    methodForOwner();
 			     }
 			     else if(goBack==2)
 			     {
 			    	 browseStoreOwner();
 			     }
 				}
 			    catch(OneOrTwoException ex)
 			    {
 			    	System.out.println("The answer must be 1 or 2\n");
 			    	error=1;
 			    }
 				catch(InputMismatchException e)
					{
						input.nextLine();
						error=1;
					}
 				}while(error==1);
			}
			}
		    catch(OneOrTwoException ex)
		    {
		    	System.out.println("The answer must be 1 or 2\n");
		    	error=1;
		    }
			catch(InputMismatchException e)
				{
					input.nextLine();
					error=1;
				}
			}while(error==1);
 	    }
 	    public void CheckStatus() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
 	    {
 	    	EShop.checkStatus();
 	    	System.out.println("Do you wish to 1.modify a buyer or 2.go back?");
				do
				{
					error=0;
				System.out.println("Give a number");
				try
				{
			    int moded=input.nextInt();
			    if ((moded!=1)&&(moded!=2))
			    {
			       throw new OneOrTwoException();
			    }
 	    	if(moded==1)
 	    	{
 	    	   	if(EShop.getBuyersList().isEmpty())
 				{
 					System.out.println("The buyer list is empty\n");
 					methodForOwner();
 				}
 	    	   	input.nextLine();
 	    	System.out.println("Give the name of the buyer you wish to see: ");
 	    	do
 	    	{
 	    		error=0;
 	    		try
 	    		{
 			String choice=input.nextLine();
 			if(EShop.getBuyerNameList().contains(choice))
 			{
 			for(Buyer b:EShop.getBuyersList())
 			{
 				if(choice.equals(b.getUserName()))
 				{
 					wantedBuyer=b;
 					(wantedBuyer.getCart()).showCart(wantedBuyer);
 					System.out.println("Do you wish to delete this buyer from the buyers list? (1.yes/2.no)");
 					do
 					{
 						error=0;
 					System.out.println("Give a number");
 					try
 					{
 				    int deleted=input.nextInt();
 				    if ((deleted!=1)&&(deleted!=2))
 				    {
 				       throw new OneOrTwoException();
 				    }
 		 			if(deleted==1)
 		 			{
 		 				EShop.removeBuyer(wantedBuyer);
 		 				ShoppingCart.setCheck(false);
 		 				(wantedBuyer.getCart()).clearCart();
 		 				CheckStatus();
 		 			}
 		 			else if(deleted==2)
 		 			{
 		 				CheckStatus();
 		 			}
 					}
 				    catch(OneOrTwoException ex)
 				    {
 				    	System.out.println("The answer must be 1 or 2\n");
 				    	error=1;
 				    }
 					catch(InputMismatchException e)
						{
							input.nextLine();
							error=1;
						}
 					}while(error==1);
 				}
 			}
 			}
 			else if(!(EShop.getBuyerNameList().contains(choice)))
 			{
 				throw new NoSuchElementException();
 			}
 	    		}
 	    		catch(NoSuchElementException e)
 	    		{
 	    		   System.out.println("This buyer does not exist, please type another name\n");
 	    			error=1;
 	    		}
 	    	}while(error==1);
 	    	}
 	    	else if(moded==2)
 			{
 				methodForOwner();
 			}
				}
			    catch(OneOrTwoException ex)
			    {
			    	System.out.println("The answer must be 1 or 2\n");
			    	error=1;			    
			    }
				catch(InputMismatchException e)
					{
						input.nextLine();
						error=1;
					}
				}while(error==1);
 	    }
 	   public void LogOutOwner() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
	    {
	    	System.out.println("Are you sure you want to logout? (1.yes/2.no)");
				do
				{
					error=0;
				System.out.println("Give a number");
				try
				{
			    int logout=input.nextInt();
			    if ((logout!=1)&&(logout!=2))
			    {
			       throw new OneOrTwoException();
			    }
		    if(logout==1)
		    {
		    	input.nextLine();
		    	emailRequest();
		    }
		    if(logout==2)
		    {
		    	methodForOwner();
		    }
				}
			    catch(OneOrTwoException ex)
			    {
			    	System.out.println("The answer must be 1 or 2\n");
			    	error=1;
			    }
				catch(InputMismatchException e)
					{
						input.nextLine();
						error=1;
					}
				}while(error==1);
	    }
 	  public void ExitOwner() throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException
	    {
	    	System.out.println("Are you sure you want to exit? (1.yes/2.no)");
			do
			{
				error=0;
			System.out.println("Give a number");
			try
			{
		    int exit=input.nextInt();
		    if ((exit!=1)&&(exit!=2))
		    {
		       throw new OneOrTwoException();
		    }
		    if(exit==1)
		    {
		    	System.exit(0);
		    }
		    if(exit==2)
		    {
		    	methodForOwner();
		    }
			}
		    catch(OneOrTwoException ex)
		    {
		    	System.out.println("The answer must be 1 or 2\n");
		    	error=1;
		    }
			catch(InputMismatchException e)
				{
					input.nextLine();
					error=1;
				}
			}while(error==1);
	    }
}
