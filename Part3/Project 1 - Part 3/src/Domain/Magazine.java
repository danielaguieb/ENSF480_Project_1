package Domain;

public class Magazine extends Document
{
	private boolean isOngoing;
	
	public Magazine(String name, String author, String pubDate, String publisher, boolean isOngoing)
	{
		super(name, author, pubDate, publisher);
		this.isOngoing = isOngoing;
	}

	public boolean isOngoing() {
		return isOngoing;
	}

	public void setOngoing(boolean isOngoing) {
		this.isOngoing = isOngoing;
	}

}