package Database;

import Domain.Document;
import Domain.Book;
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
	
	public void addDocuments(Document doc)
	{
		String sql; 
		
		if (doc instanceof Book) {
			sql = "INSERT INTO " + bookTable + "(name, author, pubDate, publisher, genre, isFiction)" +
				" VALUES ( " + doc.getName()() + ", '" + 
				doc.getAuthor()() + "', " + 
				doc.getPubDate()() + ", " + 
				doc.getPublisher()() + ", " + 
				((Book) doc).getGenre()() + ");";
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