package Presentation;

import java.util.Scanner;

import Database.Controller;
import Database.DocumentDatabaseController;

public class OperatorForm extends Form {
	
	
	public OperatorForm(String n, int i, Controller c) {
		super(n, i, c);
	}
	
	
	// add documents to the database
	public void add() {
		
	}
	
	// TODO remove existing documents from the database
	public void remove() {
		
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
				
				break;
				
			case "remove":
				break;
				
			case "update":
				break;
				
			case "help":
				System.out.println("Commands:\n"
							     + "Add //TODO solve ID issue\n"
							     + "Remove //TODO find doc ID\n"
							     + "Update // TODO should know info to update it\n\n"
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
