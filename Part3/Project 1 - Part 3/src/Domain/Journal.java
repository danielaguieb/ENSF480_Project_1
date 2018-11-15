package Domain;

import java.util.ArrayList;

public class Journal extends Document
{
	private ArrayList<String> co_contributers;
	
	public Journal(String name, String author, String pubDate, String publisher, ArrayList<String> co_contributers)
	{
		super(name, author, pubDate, publisher);
		this.co_contributers = co_contributers;
	}

	public ArrayList<String> getCo_contributers() {
		return co_contributers;
	}

	public void setCo_contributers(ArrayList<String> co_contributers) {
		this.co_contributers = co_contributers;
	}
}