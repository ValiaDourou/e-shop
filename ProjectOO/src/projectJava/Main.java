package projectJava;
import projectJava.Pencil.type;
import projectJava.Buyer.buyerCategory;
public class Main {
	public static void main(String args[]) throws OneToFiveException,OneOrTwoException, OneToThreeException, OneToFourException, CopyException
	{
		Owner owner=new Owner("Paul","zeopeo@hotmail.gr",true);
		EShop eshop=new EShop("That's a wrap!",owner);
		Pen p1=new Pen("Bic",0.5,"ball-point",23,80934,"blue",0.6);
		Pen p2=new Pen("Pilot",0.7,"ball-point",14,98723,"red",0.5);
		Pen p3=new Pen("Parker",0.8,"ball-point",22,98463,"green",0.4);
		Pencil pc1=new Pencil("Staedtler",0.9,"draw pencil",34,10987,0.3, type.HB);
		Pencil pc2 = new Pencil("Palomino",1.5,"Graphite pencil",58,52178,0.4,type.B);
		Pencil pc3 = new Pencil("Blackwing",3.2,"Solid graphite pencil",12,14127,0.5,type.H);
		Notebook n1=new Notebook("SKAG",3.5,"binder notebook",25,78907,3);
		Notebook n2=new Notebook("GLOBUS",5.0,"A4 notebook",45,34569,4);
		Notebook n3=new Notebook("Jumbo",2.5,"binder notebook",75,78906,2);
		Paper pp1=new Paper("Xerox",5.0,"A4 yellow",35,13245,10,1000);
		Paper pp2=new Paper("Papex",6.5,"A3 white",30,87654,15,600);
	    Paper pp3=new Paper("Origamix",7.0,"A2 white",65,54673,10,750);
	    eshop.addItem(p1);
	    eshop.addItem(p2);
	    eshop.addItem(p3);
	    eshop.addItem(pc1);
	    eshop.addItem(pc2);
	    eshop.addItem(pc3);
	    eshop.addItem(n1);
	    eshop.addItem(n2);
	    eshop.addItem(n3);
	    eshop.addItem(pp1);
	    eshop.addItem(pp2);
	    eshop.addItem(pp3);
	    eshop.NameList();
	    eshop.setClassNameList();
	    ShoppingCart sc1=new ShoppingCart();
	    ShoppingCart sc2=new ShoppingCart();
	    Buyer b1=new Buyer("Eva","eva@gmail.com",200,buyerCategory.GOLD,sc1);
	    eshop.addBuyer(b1);
	    Buyer b2=new Buyer("Demetres","jimmy@yahoo.de",60,buyerCategory.BRONZE,sc2);
	    eshop.addBuyer(b2);
	    b1.placeOrder(p1, 3);
	    b1.placeOrder(p3,4);
	    b1.placeOrder(n2, 2);
	    b1.placeOrder(pp1,1);
	    b2.placeOrder(p2, 2);
	    b2.placeOrder(n1, 2);
	    b2.placeOrder(n3, 3);
	    b2.placeOrder(pp2,1);
	    Menu m=new Menu();
	}
}
