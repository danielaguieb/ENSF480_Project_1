package Domain;

public class Book extends Document
{
	private String genre;
	private int isFiction;
	
	public Book(String name, String author, String pubDate, String publisher, int isPromotion, double price, String genre, int isFiction)
	{
		super(name, author, pubDate, publisher, isPromotion, price);
		this.genre = genre;
		this.isFiction = isFiction;
	}
	
	public Book(int docID, String name, String author, String pubDate, String publisher, int isPromotion, double price, String genre, int isFiction)
	{
		super(docID, name, author, pubDate, publisher, isPromotion, price);
		this.genre = genre;
		this.isFiction = isFiction;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int isFiction() {
		return isFiction;
	}

	public void setFiction(int isFiction) {
		this.isFiction = isFiction;
	}
}