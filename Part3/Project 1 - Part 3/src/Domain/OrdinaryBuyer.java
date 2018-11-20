package Domain;

import java.util.ArrayList;
import java.util.Properties;
import java.util.function.DoubleToLongFunction;

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
	
	// might be the gui that does this but ill just add this in anyways
	public void makePayments(double payment)
	{
		UserDatabaseController userDB = new UserDatabaseController();
		userDB.makePayments(this, payment);
	}
	
	public static void main(String[] args)
	{
		OrdinaryBuyer buyer = new OrdinaryBuyer("spacesloth605@gmail.com", "huzaifa147", 400);
		buyer.makePayments(100);
	}

}
