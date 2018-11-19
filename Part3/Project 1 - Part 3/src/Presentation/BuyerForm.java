package Presentation;

import java.util.ArrayList;
import java.util.Scanner;

import Database.*;
import Domain.Document;

public class BuyerForm extends Form {
	
	private boolean isRegistered;
	private Controller userDBC;
	
	public BuyerForm(String n, int i, Controller docDBC, Controller userDBC, boolean isRegistered) {
		super(n,i,docDBC);
		this.userDBC = userDBC;
		this.isRegistered = isRegistered;
	}
	
	public void checkRegistered() {
		if(isRegistered)
			System.out.println("User registered status is: TRUE");
		else
			System.out.println("User registered status is: FALSE");
	}
	
	// searches for document in database through the controller
	public void search(String docType, String docName) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		String result = docDBC.search(docName, docType);
		if (result!=null)
			System.out.println(result);
	}
	
	// order a document if it exists
	public void order(String docType, String docName) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		// TODO 
//		String result = docDBC.order(docName, docType);
//		if (result!=null)
//			System.out.println(result);
	}
	
	public void checkPayments(int userID) {
		UserDatabaseController UDBC = (UserDatabaseController) userDBC;
		System.out.println("Payments left: " + UDBC.checkPayments(userID) );
	}
	
	// make a payment to outstanding payments
	public void makePayment(int userID, double payment) {
		UserDatabaseController UDBC = (UserDatabaseController) userDBC;
		UDBC.makePayments(userID, payment);
	}
	
	// register to be a registered buyer
	public void register(int userID) {		
		if(isRegistered)
			System.out.println("Error: Already registered");
		else {
			UserDatabaseController UDBC = (UserDatabaseController) userDBC;
			UDBC.register(userID);
			
		}
	}
	
	// TODO registered buyer unregisters and becomes ordinary
	public void unsubscribe() {
		System.out.println("Unsubscribe command is not finished");
	}
	
	// access promotion list as a registered buyer
	public void accessPromotionList() {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		ArrayList<Document> promoDocs = docDBC.getPromotedDocuments();
		promoDocs.forEach((doc) -> System.out.println(doc.toString()));
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		BuyerForm buyerForm = new BuyerForm("Dan", 12, new DocumentDatabaseController(), new UserDatabaseController(),true);
		String lineIn;
		String[] inputs;
		Scanner sc = new Scanner(System.in);
		
		for(lineIn = sc.nextLine(); lineIn.toLowerCase().compareTo("quit") == 0;
				lineIn = sc.nextLine()) {
			
			inputs = lineIn.split(" ");
			switch (inputs[0].toLowerCase()) {	
				
			case "search":
				buyerForm.search(inputs[1], inputs[2]);
				break;
				
			case "placeorder":
				buyerForm.order(inputs[1], inputs[2]);
				break;
			
			case "checkpayments":
				buyerForm.checkPayments(buyerForm.ID);
				
			case "makepayments":
				buyerForm.makePayment(buyerForm.ID, Double.parseDouble(inputs[2]));
				break;
			
			case "isregistered":
				buyerForm.checkRegistered();
				break;
				
			case "register":
				buyerForm.register(buyerForm.ID);
				break;
				
			case "accesspromotionlist":
				buyerForm.accessPromotionList();
				break;
				
			case "unsubscribe":
				
				
			case "help":
				System.out.println("Commands:\n"
								 + "Search <Document_Type> <Document_Name>\n"
								 + "PlaceOrder <Document_Type> <Document_Name>\n"
								 + "CheckPayments\n"
								 + "MakePayments\n"
								 + "Register\n"
								 + "IsRegistered\n\n"
								 + "If already a registered buyer:\n"
								 + "AccessPromotionList\n"
								 + "Unsubscribe\n");
				break;
				
			default:
				System.out.println("Input unrecognized, type 'help' for commands");
				break;
			}
			
		}
		
		sc.close();
		
	}
}

