package Database;

import Domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentDatabaseController extends Controller
{
	private String bookTable;
	private String journalTable;
	private String magazineTable;
	
	public DocumentDatabaseController()
	{
		super("documentdatabase");
		bookTable = "book";
		journalTable = "journal";
		magazineTable = "magazine";
	}
	
	public void addDocuments(Document origdoc)
	{
		String sql = "INSERT INTO ";
		
		if (origdoc instanceof Book) {
			Book doc = (Book) origdoc;
			sql = bookTable + "(name, author, pubDate, publisher, genre, isFiction)" +
				" VALUES ( " + doc.getName()+ ", '" + 
				doc.getAuthor() + "', " + 
				doc.getPubDate() + ", " + 
				doc.getPublisher() + ", " + 
				doc.getGenre() + ", " + 
				doc.isFiction() + ");";
		}
		
		else if (origdoc instanceof Magazine) {
			Magazine doc = (Magazine) origdoc;
			sql = magazineTable + "(name, author, pubDate, publisher, isOngoing)" +
				" VALUES ( " + doc.getName()+ ", '" + 
				doc.getAuthor() + "', " + 
				doc.getPubDate() + ", " + 
				doc.getPublisher() + ", " + 
				doc.isOngoing() + ");";
		}
		
		else {
			Journal doc = (Journal) origdoc;
			String co_contributers = "";
			for (String co_author: doc.getCo_contributers())
				co_contributers += co_author;
			
			sql = journalTable + "(name, author, pubDate, publisher, genre, contributers)" +
				" VALUES ( " + doc.getName()+ ", '" + 
				doc.getAuthor() + "', " + 
				doc.getPubDate() + ", " + 
				doc.getPublisher() + ", " + 
				co_contributers + ");";
		}
		
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	
	public void removeDocuments(Document origdoc)
	{
		String sql = "DELETE FROM ";
		
		if (origdoc instanceof Book) {
			sql += bookTable;
		}
		
		else if (origdoc instanceof Magazine) {
			sql += magazineTable;
		}
		
		else {
			sql += journalTable;
		}
		
		try {
			sql += " WHERE docID = " + origdoc.getDocID();
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	
	public void updateDocuments(Document origdoc)
	{
		String sql = "UPDATE ";
		
		if (origdoc instanceof Book) {
			Book doc = (Book) origdoc;
			sql += bookTable + " SET name = " + doc.getName()
					+ ", author = " + doc.getAuthor()
					+ ", pubDate = " + doc.getPubDate()
					+ ", publisher = " + doc.getPublisher()
					+ ", genre = " + doc.getGenre()
					+ ", isFiction = " + doc.isFiction()
					+ " WHERE docID = " + doc.getDocID() 
					+ ";";
		}
		
		else if (origdoc instanceof Magazine) {
			Magazine doc = (Magazine) origdoc;
			sql += magazineTable + " SET name = " + doc.getName()
			+ ", author = " + doc.getAuthor()
			+ ", pubDate = " + doc.getPubDate()
			+ ", publisher = " + doc.getPublisher()
			+ ", isOngoing = " + doc.isOngoing()
			+ " WHERE docID = " + doc.getDocID() 
			+ ";";
		}
		
		else {
			Journal doc = (Journal) origdoc;
			String co_contributers = "";
			for (String co_author: doc.getCo_contributers())
				co_contributers += co_author;
			
			sql += journalTable + " SET name = " + doc.getName()
			+ ", author = " + doc.getAuthor()
			+ ", pubDate = " + doc.getPubDate()
			+ ", publisher = " + doc.getPublisher()
			+ ", contributers = " + co_contributers
			+ " WHERE docID = " + doc.getDocID() 
			+ ";";
		}
		
		try {
			statement = jdbc_connection.createStatement();
			statement.executeUpdate(sql);
		}catch (SQLException e) {
			System.out.println("Error: Cant add documents to document database");
		}
	}
	
	public String search(String docName)
	{
		// the search maybe could use roundbuttons to see what type of doc im searching for but it does say book 
		// so for now just worry about books. also then we can make place an order and payments deal with books only? 
		// we can say we interpreted it as that
		String sql = "SELECT * FROM " + bookTable + " WHERE name = " + docName;
		ResultSet result;
		try {
			statement = jdbc_connection.createStatement();
			result = statement.executeQuery(sql);
			if(result.next()){
				String toreturn = result.getString("name") + ";" + 
								  result.getString("author") + ";" +
								  result.getString("genre");
				return toreturn;
			}
			else {
				System.out.println("Error: Book not found");
			}
		
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	//gotta add some more functions, mostly the search one. the last two might not even be needed to implemented here;
	//they probably just need the search function as well
	//the promotion list might need its own table. havn't made a table yet thought. wonder if how there's different types 
	//of documents might become a problem
}