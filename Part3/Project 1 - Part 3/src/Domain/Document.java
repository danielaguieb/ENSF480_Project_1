package Domain;

public abstract class Document
{
	protected int docID;
	protected String name;
	protected String author;
	protected String pubDate;
	protected String publisher;
	protected int isPromotion;
	protected double price;
	
	public Document(String name, String author, String pubDate, String publisher, int isPromotion, double price)
	{
		this.name = name;
		this.author = author;
		this.pubDate = pubDate;
		this.publisher = publisher;
		this.isPromotion = isPromotion;
		this.price = price;
	}
	
	public Document(int docID, String name, String author, String pubDate, String publisher, int isPromotion, double price)
	{
		this(name, author, pubDate, publisher, isPromotion, price);
		this.docID = docID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public int getIsPromotion() {
		return isPromotion;
	}

	public void setIsPromotion(int isPromotion) {
		this.isPromotion = isPromotion;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}