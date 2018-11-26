package Presentation;

import java.util.ArrayList;
import java.util.Scanner;

import Database.Controller;
import Database.DocumentDatabaseController;
import Domain.Book;
import Domain.Document;
import Domain.Journal;
import Domain.Magazine;

public class OperatorForm extends Form {
	
	
	public OperatorForm(String n, int i, Controller c) {
		super(n, i, c);
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
	
	// TODO remove existing documents from the database
	public void remove(String docType, int docID) {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		if(docType.compareTo("book") == 0) {
			Book toSend = new Book(docID, null, null, null, null, 0, 0, null, 0);
			docDBC.removeDocuments(toSend);
		}
		else if(docType.compareTo("journal") == 0) {
			
			
		}
		else if(docType.compareTo("magazine") == 0) {
			
		}
		
		else
			System.out.println("Unrecognized behaviour. Command not accepted");
	}
	
	// TODO update/edit exiting document info from the database
	public void update() {
		
	}
	
	public static void main(String[] args) {
		OperatorForm operatorForm = new OperatorForm("Dan", 314, new DocumentDatabaseController());
		Scanner sc = new Scanner(System.in);
		String lineIn = sc.nextLine();
		String[] inputs;
		boolean isDone = false;	
		
		System.out.println("Enter 'help' for commandr");
		while(!isDone) {
			try {
				inputs = lineIn.split(" ");
				switch (inputs[0].toLowerCase()) {
				
				case "addjournal":
					operatorForm.addJournal(inputs[1], inputs[2], inputs[3], inputs[4], Integer.parseInt(inputs[5]), Double.parseDouble(inputs[6]), inputs[7]);
					
					break;
				
				case "search":
					operatorForm.search(inputs[1], inputs[2]);
					break;
					
				case "remove":
					operatorForm.remove(inputs[1], Integer.parseInt(inputs[2]));
					break;
					
				case "update":
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
								     + "[For parameters with 'is', the appropriate argument is 1 for yes and 0 for no]\n"
								     + "Search <Doc_Type> <Doc_Name>\n"
									 + "[Document displayed as name, author, docID, and price]\n"
								     + "Remove <Doc_Type> <Doc_ID>\n"
								     + "Update <Doc_Type>\n"
								     + "[The possible document types are 'journal', 'magazine', and 'book']\n\n"
								     + "If finished, enter 'quit'\n");
					// issue is that book id shouldn't be determined by
					// the adder, it should just happen
					// also could be
					// Remove <docID> <docType>
					// for the update issue, I think that you should be
					// able to search and then update info after the search
					// because how else do you see the info which to update
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
