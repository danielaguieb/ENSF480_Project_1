package Database;

import Domain.Document;
import Domain.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DocumentDatabaseController extends Controller
{
	private String bookTable;
	private String journalTable;
	private String magazineTable;
	
	public DocumentDatabaseController()
	{
		super();
		bookTable = "book";
		journalTable = "journal";
		magazineTable = "magazine";
	}
	
	public void addDocuments(Document origdoc)
	{
		String sql = "";
		
		if (origdoc instanceof Book) {
			Book doc = (Book) origdoc;
			sql = "INSERT INTO " + bookTable + "(name, author, pubDate, publisher, genre, isFiction)" +
				" VALUES ( " + doc.getName()+ ", '" + 
				doc.getAuthor() + "', " + 
				doc.getPubDate() + ", " + 
				doc.getPublisher() + ", " + 
				doc.getGenre() + ", " + 
				doc.isFiction() + ");";
		}
		
		else if (origdoc instanceof Magazine) {
			Magazine doc = (Magazine) origdoc;
			sql = "INSERT INTO " + magazineTable + "(name, author, pubDate, publisher, isOngoing)" +
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
			
			sql = "INSERT INTO " + bookTable + "(name, author, pubDate, publisher, genre, contributers)" +
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
	
	public void removeDocuments()
	{
		
	}
	
	public void updateDocuments()
	{
		
	}
}