package Domain;

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

import Database.DocumentDatabaseController;
import Database.UserDatabaseController;
import Presentation.BuyerForm;

public class OrdinaryBuyer extends User {
	protected ArrayList<TransactionHistory> transactionList;
	protected BuyerForm buyerForm;

	public OrdinaryBuyer(String u, String p, int i) {
		super(u, p, i);
		transactionList = new ArrayList<TransactionHistory>();
	}
	
	public void register()
	{
		// once this buyer registers, it should be added to the promotion list's list of observers. 
		// but it will still an instance of OrdinaryBuyer, and therefore cause problems
		// it might be wise to simply remake the class as a registeredBuyer
		// if thats the case then the registeredbuyer's 
	}
	
	public void placeOrder(String docName)
	{
		// the idea here is that you can place an order for a doc by typing in the doc's name or whatever
		// im going of the assumption that were just typing that name directly like its not displaying the 
		// name in a JList we just now the name of the doc
		
		// im going to need to know the doc's price
		
		DocumentDatabaseController docDB = new DocumentDatabaseController();
		double price = docDB.getPriceDoc(docName);
		
		String emailmessage = "You have placed an order for: " + docName + ". It costs " + price + ", and the money has been added to your outstanding_payments";
		
		UserDatabaseController userDB = new UserDatabaseController();
		userDB.addToOutstandingPayments(this, docName, price);
		
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
			message.setFrom(new InternetAddress("huzaifa.amar@ucalgary"));
			message.setRecipient(Message.RecipientType.TO, internetaddress);
			message.setSubject("Promotion List has Been Updated");
			message.setText(emailmessage);
			Transport.send(message); // Send the Email Message
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		OrdinaryBuyer buyer = new OrdinaryBuyer("spacesloth605@gmail.com", "huzaifa147", 400);
		buyer.placeOrder("My First Magazine");
	}

}
