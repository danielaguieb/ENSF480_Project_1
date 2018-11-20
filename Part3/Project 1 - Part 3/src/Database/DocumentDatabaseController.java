package Database;

import Domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
		promotionList.setDocuments(getPromotedDocuments());
	}
	
	public void addDocuments(Document origdoc)
	{
		String sql = "INSERT INTO ";
		
		if (origdoc instanceof Book) {
			Book doc = (Book) origdoc;
			sql += bookTable + " (name, author, pubDate, publisher, isPromotion, price, genre, isFiction)" +
				" VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
					
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());	
				statement.setInt(5, doc.getIsPromotion());
				statement.setDouble(6,  doc.getPrice());
				statement.setString(7, doc.getGenre());	
				statement.setInt(8, doc.isFiction());
				statement.executeUpdate();
				System.out.println("book now sucessfully added in");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if (origdoc instanceof Magazine) {
			Magazine doc = (Magazine) origdoc;
			System.out.println("doc is a magainze");
			sql += magazineTable + " (name, author, pubDate, publisher, isPromotion, price, isOngoing)" +
					" VALUES(?, ?, ?, ?, ?, ?, ?)";
						
				try {
					statement = jdbc_connection.prepareStatement(sql);
					statement.setString(1, doc.getName());
					statement.setString(2, doc.getAuthor());
					statement.setString(3, doc.getPubDate());
					statement.setString(4, doc.getPublisher());	
					statement.setInt(5, doc.getIsPromotion());
					statement.setDouble(6, doc.getPrice());
					statement.setInt(7, doc.isOngoing());	
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
			
			sql += journalTable + " (name, author, pubDate, publisher, isPromotion, price, contributers)" +
					" VALUES(?, ?, ?, ?, ?, ?, ?)";
						
				try {
					statement = jdbc_connection.prepareStatement(sql);
					statement.setString(1, doc.getName());
					statement.setString(2, doc.getAuthor());
					statement.setString(3, doc.getPubDate());
					statement.setString(4, doc.getPublisher());	
					statement.setInt(5, doc.getIsPromotion());
					statement.setDouble(6, doc.getPrice());
					statement.setString(7, co_contributers);	
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
			sql += bookTable + " SET name = ?, author = ?, pubDate = ?, publisher = ?, isPromotion = ?, price = ?, genre = ?, isFiction = ?"
					+ " WHERE docID = ?";
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());
				statement.setInt(5, doc.getIsPromotion());
				statement.setDouble(6, doc.getPrice());
				statement.setString(7, doc.getGenre());
				statement.setInt(8, doc.isFiction());
				statement.setInt(9, doc.getDocID());
				statement.executeUpdate();
				System.out.println("Book has been updated");
			} catch (SQLException e) {
				System.out.println("Error: book cannot be updated");
				e.printStackTrace();
			}
		}
		
		else if (origdoc instanceof Magazine) {
			Magazine doc = (Magazine) origdoc;
			sql += magazineTable + " SET name = ?, author = ?, pubDate = ?, publisher = ?, isPromotion = ?, price = ?, isOngoing = ?"
					+ " WHERE docID = ?";
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());
				statement.setInt(5, doc.getIsPromotion());
				statement.setDouble(6, doc.getPrice());
				statement.setInt(7, doc.isOngoing());
				statement.setInt(8, doc.getDocID());
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
			
			sql += magazineTable + " SET name = ?, author = ?, pubDate = ?, publisher = ?, isPromotion = ?, price = ?, contributers = ?"
					+ " WHERE docID = ?";
			try {
				statement = jdbc_connection.prepareStatement(sql);
				statement.setString(1, doc.getName());
				statement.setString(2, doc.getAuthor());
				statement.setString(3, doc.getPubDate());
				statement.setString(4, doc.getPublisher());
				statement.setInt(5, doc.getIsPromotion());
				statement.setDouble(6, doc.getPrice());
				statement.setString(7, co_contributers);
				statement.setInt(8, doc.getDocID());
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
								  Integer.toString(result.getInt("docId")) + ";" +
								  Double.toString(result.getDouble("price"));
				return toreturn;
			}
			else {
				System.out.println("Error: Book not found");
			}
		
		} catch (SQLException e) { System.out.println("Error: Document's cannot be searched for");}
		
		return null;
	}
	
	public boolean isPresent()
	{
		return false;
	}
	
	// imma need the user's username
	public void placeOrder(String docName, int userID, String username)
	{		
		double price = getPriceDoc(docName);
		
		String emailmessage = "You have placed an order for: " + docName + ". It costs " + price + ", and the money has been added to your outstanding_payments";
		
		UserDatabaseController userDB = new UserDatabaseController();
		// this function will need the user's ID
		userDB.addToOutstandingPayments(userID, docName, price);
		
		try {
			InternetAddress internetaddress = new InternetAddress(username);
			
			Properties properties = new Properties();
			properties.put("mail.smtp.starttls.enable", "true"); 
			properties.put("mail.smtp.auth", "true"); 
			properties.put("mail.smtp.host", "smtp.gmail.com"); 
			properties.put("mail.smtp.port", "587"); 
			
			Session session = Session.getInstance(properties,
					new javax.mail.Authenticator(){
					 protected PasswordAuthentication getPasswordAuthentication() {
					 return new PasswordAuthentication("amarhuzaifa@gmail.com", "Huzaifa@147");
					 }
					});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO, internetaddress);
			message.setSubject("Order Successfully Placed");
			message.setText(emailmessage);
			Transport.send(message); // Send the Email Message
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
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
				documents.add(new Book(result.getString("name"), null, null, null, 0, 0, null, 0));
			}	
		} catch (SQLException e) { System.out.println("Error: Promoted Books cannot be found");}
		
		sql = "SELECT * FROM " + magazineTable + " WHERE isPromotion = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 1);
			result = statement.executeQuery();
			while(result.next()){
				documents.add(new Magazine(result.getString("name"), null, null, null, 0, 0, 0));
			}	
		} catch (SQLException e) { System.out.println("Error: Promoted Magazines cannot be found");}
		
		sql = "SELECT * FROM " + journalTable + " WHERE isPromotion = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, 1);
			result = statement.executeQuery();
			while(result.next()){
				documents.add(new Journal(result.getString("name"), null, null, null, 0, 0, null));
			}	
		} catch (SQLException e) { System.out.println("Error: Promoted Magazines cannot be found");}
		
		return documents;
	}
	public double getPriceDoc(String docName)
	{
		String sql = "SELECT * FROM " + bookTable + " WHERE name = ?";
		ResultSet result;
		
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, docName);
			result = statement.executeQuery();
			if(result.next()){
				System.out.println("The Doc was found in the book table");
				return result.getDouble("price");
			}	
		} catch (SQLException e) { System.out.println("Error: Price Searching Messed Up");}
		
		try {
			sql = "SELECT * FROM " + magazineTable + " WHERE name = ?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, docName);
			result = statement.executeQuery();
			if(result.next()){
				System.out.println("The Doc was found in the magazine table");
				return result.getDouble("price");
			}	
		} catch (SQLException e) { System.out.println("Error: Price Searching Messed Up");}
		
		try {
			sql = "SELECT * FROM " + journalTable + " WHERE name = ?";
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, docName);
			result = statement.executeQuery();
			if(result.next()){
				System.out.println("The Doc was found in the journal table");
				return result.getDouble("price");
			}	
		} catch (SQLException e) { System.out.println("Error: Price Searching Messed Up");}
		
		return -1;
	}
	
	public static void main(String[] args)
	{
		//DocumentDatabaseController databaseController = new DocumentDatabaseController();
		//Book book = new Book("Trial Book", "Huz", "November 16, 2018", "Backend development", 0, "Jokes", 1);
		//databaseController.addDocuments(book);
		//Magazine magazine = new Magazine("My First Magazine", "Daniel Guieb", "October 1, 1965", "Daniel's Mom", 1, 1);
		//databaseController.addDocuments(magazine);
		//Book book = new Book(3, null, null, null, null, 0, null, 0);
		//databaseController.removeDocuments(book);
		//Book book = new Book(2, "Journey to the Centre of the Earth", "Jules Verne", "March 18, 1844", "Penguin House", 0, "science fantasy", 1);
		//databaseController.updateDocuments(book);
		//System.out.println(databaseController.search("Trial Book", "book"));
		//System.out.println(databaseController.getPriceDoc("Journal 1"));
		
	}
	
}