package Domain;

public class Magazine extends Document
{
	private int isOngoing;
	
	public Magazine(String name, String author, String pubDate, String publisher, int isPromotion, double price, int isOngoing)
	{
		super(name, author, pubDate, publisher, isPromotion, price);
		this.isOngoing = isOngoing;
	}
	
	public Magazine(int docID, String name, String author, String pubDate, String publisher, int isPromotion, double price, int isOngoing)
	{
		super(docID, name, author, pubDate, publisher, isPromotion, price);
		this.isOngoing = isOngoing;
	}

	public int isOngoing() {
		return isOngoing;
	}

	public void setOngoing(int isOngoing) {
		this.isOngoing = isOngoing;
	}

}