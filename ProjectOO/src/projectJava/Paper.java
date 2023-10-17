package projectJava;
public class Paper extends Item {
	private int weight;
	private int pages;
	private static int stockPaper;
	private static final String Type = "Paper";
	public Paper(String name,double price,String description,int stock,int id,int weight,int pages)
	{
		super(name,price,description,stock,id);
		stockPaper+=stock;
		this.weight=weight;
		this.pages=pages;
	}
	public String getDetails() 
	{
		return "Weight: "+Integer.toString(weight)+" Pages: "+Integer.toString(pages);
	}
	public static int getStock()
	{
		return stockPaper;
	}
	@Override
	public String getType()
	{
		return Type;
	}
	@Override
	public void setStock(int s)
	{
		stockPaper=stockPaper+s;
	}
}
