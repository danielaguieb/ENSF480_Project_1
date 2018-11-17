package Database;

import Domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentDatabaseController extends Controller
{
	private String bookTable;
	private String journalTable;
	private String magazineTable;
	private PromotionList promotionList;
	
	public DocumentDatabaseController()
	{
		super("documentdatabase");
		bookTable = "book";
		journalTable = "journal";
		magazineTable = "magazine";
		promotionList = PromotionList.getInstance();
	}
	
	public void addDocuments(Document origdoc)
	{
		String sql = "INSERT INTO ";
		
		if (origdoc instanceof Book) {
			Book doc = (Book) origdoc;
			sql += bookTable + " (name, author, pubDate, publisher, isPromotion, genre, isFiction)" +
				" VALUES(?, ?, ?, ?, ?, ?, ?)";
					
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());	
				statement.setInt(5, doc.getIsPromotion());
				statement.setString(6, doc.getGenre());	
				statement.setInt(7, doc.isFiction());
				statement.executeUpdate();
				System.out.println("book now sucessfully added in");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if (origdoc instanceof Magazine) {
			Magazine doc = (Magazine) origdoc;
			System.out.println("doc is a magainze");
			sql += magazineTable + " (name, author, pubDate, publisher, isPromotion, isOngoing)" +
					" VALUES(?, ?, ?, ?, ?, ?)";
						
				try {
					statement = jdbc_connection.prepareStatement(sql);
					statement.setString(1, doc.getName());
					statement.setString(2, doc.getAuthor());
					statement.setString(3, doc.getPubDate());
					statement.setString(4, doc.getPublisher());	
					statement.setInt(5, doc.getIsPromotion());
					statement.setInt(6, doc.isOngoing());	
					statement.executeUpdate();
					System.out.println("magazine now successfully added in");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		else {
			Journal doc = (Journal) origdoc;
			System.out.println("doc is a journal");
			String co_contributers = "";
			for (String co_author: doc.getCo_contributers())
				co_contributers += co_author;
			
			sql += journalTable + " (name, author, pubDate, publisher, isPromotion, contributers)" +
					" VALUES(?, ?, ?, ?, ?, ?)";
						
				try {
					statement = jdbc_connection.prepareStatement(sql);
					statement.setString(1, doc.getName());
					statement.setString(2, doc.getAuthor());
					statement.setString(3, doc.getPubDate());
					statement.setString(4, doc.getPublisher());	
					statement.setInt(5, doc.getIsPromotion());
					statement.setString(6, co_contributers);	
					statement.executeUpdate();
					System.out.println("journal now successfully added in");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		if (origdoc.getIsPromotion() == 1) 
			promotionList.addDocument(origdoc);
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
			sql += " WHERE docID = ?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, origdoc.getDocID());
			statement.executeUpdate();
			System.out.println("Document Successfully removed");
		}catch (SQLException e) {
			System.out.println("Error: Cant remove documents from document database");
		}
		
		if (origdoc.getIsPromotion() == 1) 
			promotionList.removeDocument(origdoc);
	}
	
	public void updateDocuments(Document origdoc)
	{
		String sql = "UPDATE ";
		
		if (origdoc instanceof Book) {
			Book doc = (Book) origdoc;
			sql += bookTable + " SET name = ?, author = ?, pubDate = ?, publisher = ?, isPromotion = ?, genre = ?, isFiction = ?"
					+ " WHERE docID = ?";
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());
				statement.setInt(5, doc.getIsPromotion());
				statement.setString(6, doc.getGenre());
				statement.setInt(7, doc.isFiction());
				statement.setInt(8, doc.getDocID());
				statement.executeUpdate();
				System.out.println("Book has been updated");
			} catch (SQLException e) {
				System.out.println("Error: book cannot be updated");
				e.printStackTrace();
			}
		}
		
		else if (origdoc instanceof Magazine) {
			Magazine doc = (Magazine) origdoc;
			sql += magazineTable + " SET name = ?, author = ?, pubDate = ?, publisher = ?, isPromotion = ?, isOngoing = ?"
					+ " WHERE docID = ?";
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());
				statement.setInt(5, doc.getIsPromotion());
				statement.setInt(6, doc.isOngoing());
				statement.setInt(7, doc.getDocID());
				statement.executeUpdate();
				System.out.println("Magazine has been updated");
			} catch (SQLException e) {
				System.out.println("Error: magazine cannot be updated");
			}
		}
		
		else {
			Journal doc = (Journal) origdoc;
			String co_contributers = "";
			for (String co_author: doc.getCo_contributers())
				co_contributers += co_author;
			
			sql += magazineTable + " SET name = ?, author = ?, pubDate = ?, publisher = ?, isPromotion = ?, contributers = ?"
					+ " WHERE docID = ?";
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());
				statement.setInt(5, doc.getIsPromotion());
				statement.setString(6, co_contributers);
				statement.setInt(7, doc.getDocID());
				statement.executeUpdate();
				System.out.println("Journal has been updated");
			} catch (SQLException e) {
				System.out.println("Error: journal cannot be updated");
			}
		}
		
		if (origdoc.getIsPromotion() == 1) 
			promotionList.updateDocument(origdoc);
	}
	
	public String search(String docName, String docTable)
	{
		String sql = "SELECT * FROM " + docTable + " WHERE name = ?";
		ResultSet result;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, docName);
			result = statement.executeQuery();
			if(result.next()){
				String toreturn = result.getString("name") + ";" + 
								  result.getString("author") + ";" +
								  result.getString("genre");
				return toreturn;
			}
			else {
				System.out.println("Error: Book not found");
			}
		
		} catch (SQLException e) { System.out.println("Error: Document's cannot be searched for");}
		
		return null;
	}
	
	public ArrayList<Document> getPromotedDocuments()
	{
		String sql = "SELECT * FROM " + bookTable + " WHERE isPromotion = ?";
		ResultSet result;
		ArrayList<Document> documents = new ArrayList<Document>();
		
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 1);
			result = statement.executeQuery();
			while(result.next()){
				documents.add(new Book(result.getString("name"), null, null, null, 0, null, 0));
			}	
		} catch (SQLException e) { System.out.println("Error: Promoted Books cannot be found");}
		
		sql = "SELECT * FROM " + magazineTable + " WHERE isPromotion = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 1);
			result = statement.executeQuery();
			while(result.next()){
				documents.add(new Magazine(result.getString("name"), null, null, null, 0, 0));
			}	
		} catch (SQLException e) { System.out.println("Error: Promoted Magazines cannot be found");}
		
		sql = "SELECT * FROM " + journalTable + " WHERE isPromotion = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 1);
			result = statement.executeQuery();
			while(result.next()){
				documents.add(new Journal(result.getString("name"), null, null, null, 0, null));
			}	
		} catch (SQLException e) { System.out.println("Error: Promoted Magazines cannot be found");}
		
		return documents;
	}
	
	public static void main(String[] args)
	{
		DocumentDatabaseController databaseController = new DocumentDatabaseController();
//		Book book = new Book("Trial Book", "Huz", "November 16, 2018", "Backend development", 0, "Jokes", 1);
//		databaseController.addDocuments(book);
		//Magazine magazine = new Magazine("My First Magazine", "Daniel Guieb", "October 1, 1965", "Daniel's Mom", 1, 1);
		//databaseController.addDocuments(magazine);
		//Book book = new Book(3, null, null, null, null, 0, null, 0);
		//databaseController.removeDocuments(book);
		//Book book = new Book(2, "Journey to the Centre of the Earth", "Jules Verne", "March 18, 1844", "Penguin House", 0, "science fantasy", 1);
		//databaseController.updateDocuments(book);
		System.out.println(databaseController.search("Trial Book", "book"));
		
	}
	
}