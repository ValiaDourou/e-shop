package projectJava;
public abstract class Item {
	private String name;
	private double price;
	private String description;
	private int stock;
	private int id;
	public Item(String name,double price,String description,int stock,int id)
	{
		this.name=name;
		this.price=price;
		this.description=description;
		this.stock=stock;
		this.id=id;
	}
	public String getBasicInfo()
	{
		return "Name: "+name+" Price: "+Double.toString(price)+" Description: "+description+" Available stock: "+Integer.toString(stock)+" ID: "+Integer.toString(id);
	}
	public int getId(Item i)
	{
		return i.id;
	}
	public String getName()
	{
		return name;
	}
	public double getPrice()
	{
		return price;
	}
	public int getItemStock()
	{
		return stock;
	}
	public abstract String getDetails();
	@Override
	public String toString()
	{
		return getBasicInfo()+" "+getDetails();
	}
	public abstract String getType();
	public abstract void setStock(int s);
	public void setItemStock(int l)
	{
		stock+=l;
	}
}
