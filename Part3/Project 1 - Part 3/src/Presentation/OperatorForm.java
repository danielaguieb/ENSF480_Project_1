package Presentation;

import java.util.Scanner;

import Database.Controller;
import Database.DocumentDatabaseController;
import Domain.Book;
import Domain.Document;

public class OperatorForm extends Form {
	
	
	public OperatorForm(String n, int i, Controller c) {
		super(n, i, c);
	}
	
	
	// add documents to the database
	public void add() {
		DocumentDatabaseController docDBC = (DocumentDatabaseController) controller;
		
		
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
			inputs = lineIn.split(" ");
			switch (inputs[0].toLowerCase()) {
			
			case "add":
				operatorForm.add(inputs[1]);
				
				// TODO decide how choose different inputs for different doc types
				// most likely, have three different Add <Doc_Type> <Doc_Name> ...
				// and depending on <Doc_Type>, run create a different doc to send
				
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
							     + "Add <Doc_Type> <Doc_Name> <Doc_Author> <Doc_PublishDate> <Doc_Publisher> <Doc_IsPromoted> <Doc_Price>\n"
							     + "[//TODO SHOULD THE ABOVE CARE FOR MULTIPLE AUTHORS?\n"
							     + "Date must be entered as //TODO DATE FORMAT???]\n"
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
	}
	
}
