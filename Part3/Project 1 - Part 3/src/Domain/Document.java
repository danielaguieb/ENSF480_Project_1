package Domain;

public abstract class Document
{
	protected int docID;
	protected String name;
	protected String author;
	protected String pubDate;
	protected String publisher;
	
	public Document(String name, String author, String pubDate, String publisher)
	{
		this.name = name;
		this.author = author;
		this.pubDate = pubDate;
		this.publisher = publisher;
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
}