package Presentation;
import javax.swing.JFrame;

import Database.*;

/*
 * note that on the class diagram with details, we forgot to add that
 * Form has a database object but it's a little implicit on the other diagrams
 */


public abstract class Form extends JFrame{

	private String name;
	private int ID;
	private Controller controller;
	
	public Form() {
		name = null;
		ID = 0;
		controller = null;
	}
	
	public Form(String n, int i, Controller c) {
		name = n;
		ID = i;
		controller = c;
	}

}
