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
	public void order(String docName, int userID, String username) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		docDBC.placeOrder(docName, userID, username);
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
		Scanner sc = new Scanner(System.in);
		String lineIn = sc.nextLine();
		String[] inputs;
		boolean isDone = false;	

		System.out.println("Enter 'help' for commands");
		while(!isDone) {
			inputs = lineIn.split(" ");
			switch (inputs[0].toLowerCase()) {	
				
			case "search":
				buyerForm.search(inputs[1], inputs[2]);
				break;
				
			case "placeorder":
				buyerForm.order(inputs[1], buyerForm.ID, buyerForm.name);
				break;
			
			case "checkpayments":
				buyerForm.checkPayments(buyerForm.ID);
				
			case "makepayments":
				buyerForm.makePayment(buyerForm.ID, Double.parseDouble(inputs[1]));
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
								 + "[Document displayed as name, author, docID, and price]\n"
								 + "PlaceOrder <Document_Type> <Document_Name>\n"
								 + "[The possible document types are 'journal', 'magazine', and 'book']\n"
								 + "CheckPayments\n"
								 + "MakePayments <Payment_Amount>\n"
								 + "Register\n"
								 + "IsRegistered\n\n"
								 + "If already a registered buyer:\n"
								 + "AccessPromotionList\n"
								 + "Unsubscribe\n\n"
								 + "If finished, enter 'quit'\n");
				break;
				
			case "quit":
				System.out.println("Exiting program...");
				isDone = true;
				break;
				
			default:
				System.out.println("Input unrecognized, type 'help' for commands");
				break;
			}
			
			lineIn = sc.nextLine();
			
		}
		
		sc.close();
		
	}
}

