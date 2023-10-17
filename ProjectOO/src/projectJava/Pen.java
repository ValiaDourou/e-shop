package projectJava;
public class Pen extends Item{
	private String color;
	private double tipSize;
	private static int stockPen=0;
	private static final String Type = "Pen";
	public Pen(String name,double price,String description,int stock,int id,String color,double tipSize)
	{
		super(name,price,description,stock,id);
		stockPen=stockPen+stock;
		this.color=color;
		this.tipSize=tipSize;
	}
	public String getDetails()
	{
		return "Color: "+color+" Tip size: "+Double.toString(tipSize);
	}
	public static int getStock()
	{
		return stockPen;
	}
	@Override
	public void setStock(int s)
	{
		stockPen=stockPen+s;
	}
	@Override
	public String getType()
	{
		return Type;
	}
}