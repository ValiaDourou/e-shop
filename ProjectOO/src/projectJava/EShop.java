package projectJava;
import java.util.*;
public class EShop {
	private static String name;
	private Owner owner;
	private static Item tempItem;
	private static ArrayList<Item> itemsList=new ArrayList<Item>();
	private static ArrayList<Buyer> buyersList=new ArrayList<Buyer>();
	private static ArrayList<String> classList=new ArrayList<String>();
	private static ArrayList<String> itemsNameList=new ArrayList<String>();
	private static ArrayList<String> emailList=new ArrayList<String>();
	private static ArrayList<String> buyerNameList=new ArrayList<String>();
	private static int ProductId;
	private static int error;
	private static Scanner showCat=new Scanner(System.in);
	public EShop(String name,Owner owner)
	{
		this.name=name;
		this.owner=owner;
	}
	public void addItem(Item i)
	{
		do
		{
			error=0;
		try
		{
			if(itemsList.contains(i))
			{
				throw new CopyException();
			}
			else 
			{
				itemsList.add(i);
			}
		}
		catch(CopyException e)
		{
			System.out.println("This item already exists\n");
			error=1;
		}
		}while(error==1);
	}
	public static Item getItemById(int id)
	{
		do
		{
		error=0;
		try
		{
		for(Item i:itemsList)
		{
			if (id==i.getId(i))
			{
		     tempItem=i;
			}
		}
		for(Item i:itemsList)
		{
		if(!(itemsList.contains(i)))
		{
			throw new NoSuchElementException();
		}
		}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No such element exists. Give another item\n");
			error=1;
		}
		}while(error==1);
			return tempItem;
	}
	public void removeItem(Item i)
	{
		do
		{
			error=0;
		try
		{
			if(!(itemsList.contains(i)))
			{
				throw new NoSuchElementException();
			}
			else 
			{
				itemsList.remove(i);
			}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("This item doesn't exist, so it can't be removed\n");
			error=1;
		}
		}while(error==1);
	}
	public static void addBuyer(Buyer b)
	{
		do
		{
			error=0;
		try
		{
			if(buyersList.contains(b))
			{
				throw new CopyException();
			}
			else 
			{
				buyersList.add(b);
			}
		}
		catch(CopyException e)
		{
			System.out.println("This buyer already exists\n");
			error=1;
		}
		}while(error==1);
	}
	public static void removeBuyer(Buyer b)
	{
		do
		{
			error=0;
		try
		{
			if(!(buyersList.contains(b)))
			{
				throw new NoSuchElementException();
			}
			else 
			{
				buyersList.remove(b);
			}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("This buyer doesn't exist, so he can't be removed\n");
			error=1;
		}
		}while(error==1);
	}
	public void NameList()
	{
		for(Item i:itemsList)
		{
			itemsNameList.add(i.getName());
		}
	}
	public void setClassNameList()
	{
		for(Item i: itemsList)
		{
			try {
			String classes =i.getClass().getSimpleName();
			if(classList.contains(classes))
			{
				throw new CopyException();
			}
			classList.add(classes);
			}
		    catch(CopyException e)
			{
			    classList.remove(i.getClass().getSimpleName());
			}
		}
	}
	public static void updateItemStock(Item i,int stock)
	{
			if(i.getType().equals("Pen"))
			{
				((Pen)i).setStock(stock);
			}
			else if(i.getType().contentEquals("Pencil"))
			{
				((Pencil)i).setStock(stock);
			}
			else if(i.getType().contentEquals("Notebook"))
			{
				((Notebook)i).setStock(stock);
			}
			else if(i.getType().contentEquals("Paper"))
			{
				((Paper)i).setStock(stock);
			}
	}
	public static void showCategories()
	{
		try
		{
		if(classList.isEmpty())
		{
			throw new NoSuchElementException();
		}
		for(String s:classList)
		{
			if(s.equals("Pen"))
			{
				System.out.println(s+" "+Integer.toString(Pen.getStock()));
			}
			else if(s.equals("Pencil"))
			{
				System.out.println(s+" "+Integer.toString(Pencil.getStock()));
			}
			else if(s.equals("Notebook"))
			{
				System.out.println(s+" "+Integer.toString(Notebook.getStock()));
			}
			else if(s.equals("Paper"))
			{
				System.out.println(s+" "+Integer.toString(Paper.getStock()));
			}
		}
	    }
		catch(NoSuchElementException e)
		{
			System.out.println("The e-shop is empty\n");
			System.exit(0);
		}
	}
	public static void showProductsInCategory()
	{
		do
		{
			error=0;
			try
			{
		System.out.println("Give the name of the category you wish to see: ");
		String choice=showCat.nextLine();
		if(choice.equals(Pen.class.getSimpleName()))
		{
			int  j=1;
			for (Item i:itemsList)
			{
				if(i instanceof Pen)
				{
					System.out.println(j+" "+i.getName());
					j++;
				}
			}
		}
		else if(choice.equals(Pencil.class.getSimpleName()))
		{
			int  j=1;
			for (Item i:itemsList)
			{
				if(i instanceof Pencil)
				{
					System.out.println(j+" "+i.getName());
					j++;
				}
			}
		}
		else if(choice.equals(Notebook.class.getSimpleName()))
		{
			int  j=1;
			for (Item i:itemsList)
			{
				if(i instanceof Notebook)
				{
					System.out.println(j+" "+i.getName());
					j++;
				}
			}
		}
		else if(choice.equals(Paper.class.getSimpleName()))
		{
			int  j=1;
			for (Item i:itemsList)
			{
				if(i instanceof Paper)
				{
					System.out.println(j+" "+i.getName());
					j++;
				}
			}
		}
	   else if((!choice.equals(Paper.class.getSimpleName()))&&(!choice.equals(Notebook.class.getSimpleName()))&&(!choice.equals(Pencil.class.getSimpleName()))&&(!choice.equals(Pen.class.getSimpleName())))
		    {
			 throw new NoSuchElementException();
			}
		}
			catch(NoSuchElementException e)
			{
				error=1;
			}
			catch(IllegalStateException e)
			{
				error=1;
			} 
		}while(error==1);
	}
	public static void showProduct()
	{
		System.out.println("Give the name of the product you wish to see: ");
		do
		{
			error=0;
			try
			{
		String choice=showCat.nextLine();
			for(Item i:itemsList)
			{
				if(choice.contains((i.getName())))
				{
					if(i.getItemStock()<=0)
					 {
						 System.out.println("This item is out of stock");
					 }
					else
					{
					System.out.println(i.toString());
					ProductId=i.getId(i);
					}
				}
			}
			if(!(itemsNameList.contains(choice)))
		{
			throw new NoSuchElementException();
		}
			}
			catch(NoSuchElementException e)
			{
				System.out.println("No such element exists\n");
				error=1;
			}
			catch(IllegalStateException e)
			{
				error=1;
			} 
		}while(error==1);
	}
	public static int getProductId()
	{
		return ProductId;
	}
	public static void checkStatus()
	{
		int i=1;
		for(Buyer b:buyersList)
		{
			System.out.println(i+". "+b.getUserName()+" "+b.getBonus()+" "+b.getCategory());
			i++;
		}
	}
	public static ArrayList<Buyer> getBuyersList()
	{
	    return buyersList;
	}
	public static String getEShopName()
	{
		return name;
	}
	public static ArrayList<String> getEmailList()
	{
		for(Buyer b:buyersList)
		{
			emailList.add(b.getEmail());
		}
		return emailList;
	}
	public static ArrayList<String> getBuyerNameList()
	{
		for(Buyer b:buyersList)
		{
			buyerNameList.add(b.getName());
		}
		return buyerNameList;
	}
}
