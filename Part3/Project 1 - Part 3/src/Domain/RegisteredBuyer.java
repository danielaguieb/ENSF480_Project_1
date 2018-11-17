package Domain;

import java.util.ArrayList;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class RegisteredBuyer extends OrdinaryBuyer implements Observer{
	
	private Subject promotionList;
	private ArrayList<Document> documents;
	
	public RegisteredBuyer(String u, String p, int i) {
		super(u, p, i);
	}
	
	public void unsubscribe() {
		
	}

	public void update(ArrayList<Document> documents) {
		this.documents = documents;
		
		String emailmessage = "The documents:\n";
		int i = 0;
		for (Document doc: documents)
			emailmessage += "	" + doc.getName() + "\n";
		emailmessage += "Are now available in the promotion list";
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		RegisteredBuyer trial = new RegisteredBuyer("spacesloth605@gmail.com", "huzaifa147", 400);
		ArrayList<Document >documents = new ArrayList<Document>();
		documents.add(new Book("Trial Email Book", "Huzman the Snoozman", "November 17, 2018", "Huz and Dan", 1, "Trial Docs", 1));
		trial.update(documents);
	}
}
