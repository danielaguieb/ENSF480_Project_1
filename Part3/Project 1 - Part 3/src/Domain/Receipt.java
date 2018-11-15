package Domain;

import java.util.ArrayList;

public class Receipt {
	private int price;
	private String date;
	private ArrayList<String> items;
	
	public Receipt(int p, String d, ArrayList<String> i) {
		setPrice(p);
		date = d;
		items.addAll(i);
	}
	
	public void addItem(String i) {
		items.add(i);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
