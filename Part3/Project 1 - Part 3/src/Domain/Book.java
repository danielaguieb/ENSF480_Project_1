package Domain;

public class Book extends Document
{
	private String genre;
	private boolean isFiction;
	
	public Book(String name, String author, String pubDate, String publisher, String genre, boolean isFiction)
	{
		super(name, author, pubDate, publisher);
		this.genre = genre;
		this.isFiction = isFiction;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean isFiction() {
		return isFiction;
	}

	public void setFiction(boolean isFiction) {
		this.isFiction = isFiction;
	}
}