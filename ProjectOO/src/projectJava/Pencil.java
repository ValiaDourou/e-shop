package projectJava;
public class Pencil extends Item {
	enum type{
		H,
		B,
		HB
	}
	private double tipSize;
	private type type;
	private static int stockPencil;
	private static final String Type = "Pencil";
	public Pencil(String name,double price,String description,int stock,int id,double tipSize,type type)
	{
		super(name,price,description,stock,id);
		stockPencil+=stock;
		this.tipSize=tipSize;
		this.type=type;
	}
	public String getDetails()
	{
		return "Tip size: "+Double.toString(tipSize)+" Type: "+type;
	}
	public static int getStock() 
	{
		return stockPencil;
	}
	@Override
	public void setStock(int s)
	{
		stockPencil=stockPencil+s;
	}
	@Override
	public String getType()
	{
		return Type;
	}
}
