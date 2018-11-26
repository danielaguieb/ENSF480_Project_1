package Presentation;

import java.util.ArrayList;
import java.util.Scanner;

import Database.Controller;
import Database.DocumentDatabaseController;
import Database.UserDatabaseController;
import Domain.Book;
import Domain.Journal;
import Domain.Magazine;

public class OperatorForm extends Form {
	private Controller userDBC;
	
	
	
	public OperatorForm(String n, int i, Controller doc_c, Controller user_c) {
		super(n, i, doc_c);
		userDBC = user_c;
	}
	
	
	// add documents to the database
	public void addJournal(String name, String author, String pubDate, String publisher,
							int isPromoted, double price, String co_contributors) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		ArrayList<String> co_contributers_list = new ArrayList<String>(); 
		String[] contributors = co_contributors.split(";");
		for(int i=0; i<contributors.length; i++) {
			co_contributers_list.add(contributors[i]);
		}
		Journal toSend = new Journal(name, author, pubDate, publisher, isPromoted, price, co_contributers_list);
		docDBC.addDocuments(toSend);
	}
	
	public void addMagazine(String name, String author, String pubDate, String publisher,
							int isPromoted, double price, int isOngoing) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		Magazine toSend = new Magazine(name, author, pubDate, publisher, isPromoted, price, isOngoing);
		docDBC.addDocuments(toSend);
	}
	
	public void addBook(String name, String author, String pubDate, String publisher,
							int isPromoted, double price, String genre, int isFiction) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		Book toSend = new Book(name, author, pubDate, publisher, isPromoted, price, genre, isFiction);
		docDBC.addDocuments(toSend);
	}
	
	public void search(String docType, String docName) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		String result = docDBC.search(docName, docType);
		if (result!=null)
			System.out.println(result);
	}
	
	// remove existing documents from the database
	public void remove(String docType, int docID) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		if(docType.toLowerCase().compareTo("book") == 0) {
			Book toSend = new Book(docID, null, null, null, null, 0, 0, null, 0);
			docDBC.removeDocuments(toSend);
		}
		else if(docType.toLowerCase().compareTo("journal") == 0) {
			Journal toSend = new Journal(docID, null, null, null, null, 0, 0, null);
			docDBC.removeDocuments(toSend);
		}
		else if(docType.toLowerCase().compareTo("magazine") == 0) {
			Magazine toSend = new Magazine(docID, null, null, null, null, 0, 0, 0);
			docDBC.removeDocuments(toSend);
		}
		
		else
			System.out.println("Unrecognized behaviour. Command not accepted");
	}
	
	public void updateJournal(String name, String author, String pubDate, String publisher, int docID,
			int isPromoted, double price, String co_contributors) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		ArrayList<String> co_contributers_list = new ArrayList<String>(); 
		String[] contributors = co_contributors.split(";");
		for(int i=0; i<contributors.length; i++) {
		co_contributers_list.add(contributors[i]);
		}
		Journal toSend = new Journal(docID, name, author, pubDate, publisher, isPromoted, price, co_contributers_list);
		docDBC.updateDocuments(toSend);
	}
	
	// update/edit exiting document info from the database
		public void updateMagazine(String name, String author, String pubDate, String publisher, int docID,
				int isPromoted, double price, int isOngoing) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		Magazine toSend = new Magazine(docID, name, author, pubDate, publisher, isPromoted, price, isOngoing);
		docDBC.updateDocuments(toSend);
	}
	
	public void updateBook(String name, String author, String pubDate, String publisher, int docID,
			int isPromoted, double price, String genre, int isFiction) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		Book toSend = new Book(docID, name, author, pubDate, publisher, isPromoted, price, genre, isFiction);
		docDBC.updateDocuments(toSend);
	}
	
	public static void main(String[] args) {
		System.out.println("Enter 'help' for commands\n");
		OperatorForm operatorForm = new OperatorForm("Dan", 314, new DocumentDatabaseController(), new UserDatabaseController());
		Scanner sc = new Scanner(System.in);
		String lineIn = sc.nextLine();
		String[] inputs;
		boolean isDone = false;	
		
		while(!isDone) {
			try {
				inputs = lineIn.split(" ");
				switch (inputs[0].toLowerCase()) {
				
				case "addjournal":
					operatorForm.addJournal(inputs[1], inputs[2], inputs[3], inputs[4], Integer.parseInt(inputs[5]), Double.parseDouble(inputs[6]), inputs[7]);
					break;
				
				case "addmagazine":
					operatorForm.addMagazine(inputs[1], inputs[2], inputs[3], inputs[4], Integer.parseInt(inputs[5]), Double.parseDouble(inputs[6]), Integer.parseInt(inputs[7]));
					break;
					
				case "addbook":
					operatorForm.addBook(inputs[1], inputs[2], inputs[3], inputs[4], Integer.parseInt(inputs[5]), Double.parseDouble(inputs[6]), inputs[7], Integer.parseInt(inputs[8]));
					break;
				case "search":
					operatorForm.search(inputs[1], inputs[2]);
					break;
					
				case "remove":
					operatorForm.remove(inputs[1], Integer.parseInt(inputs[2]));
					break;
					
				case "updatejournal":
					operatorForm.updateJournal(inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], Integer.parseInt(inputs[6]), Double.parseDouble(inputs[7]), inputs[8]);
					break;
				
				case "updatemagazine":
					operatorForm.updateMagazine(inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], Integer.parseInt(inputs[6]), Double.parseDouble(inputs[7]), Integer.parseInt(inputs[8]));
					break;
					
				case "updatebook":
					operatorForm.updateBook(inputs[1], inputs[2], inputs[3], inputs[4], inputs[5], Integer.parseInt(inputs[6]), Double.parseDouble(inputs[7]), inputs[8], Integer.parseInt(inputs[9]));
					break;
					
				case "help":
					System.out.println("Commands:\n"
								     + "AddJournal <Doc_Name> <Doc_Author> <Doc_PubDate> <Doc_Publisher> "
								     	+ "<Doc_IsPromoted> <Doc_Price> <Doc_Co-contributors>\n"
								     + "[Co-contributor list should be written in the format Name1;Name2;Name3...]\n"
								     + "AddMagazine <Doc_Name> <Doc_Author> <Doc_PubDate> <Doc_Publisher> "
								     	+ "<Doc_isPromoted> <Doc_Price> <Doc_isOngoing>\n"
								     + "AddBook <Doc_Name> <Doc_Author> <Doc_PubDate> <Doc_Publisher> "
								     	+ "<Doc_isPromoted> <Doc_Price> <Doc_Genre> <Doc_isFiction>\n"
								     + "Search <Doc_Type> <Doc_Name>\n"
									 + "[Document displayed as name, author, docID, and price]\n"
								     + "Remove <Doc_Type> <Doc_ID>\n"
								     + "UpdateJournal <Doc_Name> <Doc_Author> <Doc_PubDate> <Doc_Publisher> "
								     	+ "<Doc_IsPromoted> <Doc_Price> <Doc_Co-contributors>\n"
								     + "[Co-contributor list should be written in the format Name1;Name2;Name3...]\n"
								     + "UpdateMagazine <Doc_Name> <Doc_Author> <Doc_PubDate> <Doc_Publisher> "
								     	+ "<Doc_isPromoted> <Doc_Price> <Doc_isOngoing>\n"
								     + "UpdateBook <Doc_Name> <Doc_Author> <Doc_PubDate> <Doc_Publisher> "
								     	+ "<Doc_isPromoted> <Doc_Price> <Doc_Genre> <Doc_isFiction>\n"
								     + "[For parameters with 'is', the appropriate argument is 1 for yes and 0 for no]\n"
								     + "[The possible document types are 'journal', 'magazine', and 'book']\n\n"
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
			} 
			catch (IndexOutOfBoundsException e){
				System.out.println("Please check your arguments\n");
			}
			catch (Exception e) {
				System.out.println("What is happening\n");
			}
			
			lineIn = sc.nextLine();
		}
		
		sc.close();
	}
}
