package projectJava;
public class Buyer extends User {
	private int bonus=0;
	enum buyerCategory{
			BRONZE,
			SILVER,
			GOLD
	}
	private buyerCategory bC;
	private ShoppingCart myCart;
	private String buyerEmail,buyerName;
	public Buyer(String name,String email,int bonus,buyerCategory bC,ShoppingCart myCart)
	{
		super(name,email);
		buyerEmail=email;
		buyerName=name;
		this.bonus=bonus;
		this.bC=bC;
		this.myCart=myCart;
	}
	public String getEmail()
	{
		return buyerEmail;
	}
	public String getName()
	{
		return buyerName;
	}
	public void awardBonus()
	{
		bonus+=(int)0.1*myCart.calculateNet();
	}
	public void setbuyerCategory(int bonus)
	{ 
		if (bonus<100)
		{
	      bC = buyerCategory.BRONZE;
		}
		else if((bonus>100)&&(bonus<200))
		{
			bC = buyerCategory.SILVER;
		}
		else if(bonus>200)
		{
			bC = buyerCategory.GOLD;
		}
	}
	public void placeOrder(Item i,int quantity)
	{
		myCart.addItemOrdered(i,quantity);
	}
	public String getCategory()
	{
		return bC.toString();
	}
	public int getBonus()
	{
		return bonus;
	}
	public ShoppingCart getCart()
	{
		return myCart;
	}
}
