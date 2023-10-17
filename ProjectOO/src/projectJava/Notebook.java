package projectJava;
public class Notebook extends Item {
	private int sections;
	private static int stockNotebook;
	private static final String Type = "Notebook";
	public Notebook(String name,double price,String description,int stock,int id,int sections)
	{
		super(name,price,description,stock,id);
		stockNotebook+=stock;
		this.sections=sections;
	}
	public String getDetails()
	{
		return "Sections: "+Integer.toString(sections);
	}
	public static int getStock()
	{
		return stockNotebook;
	}
	@Override
	public String getType()
	{
		return Type;
	}
	@Override
	public void setStock(int s)
	{
		stockNotebook=stockNotebook+s;
	}
}
