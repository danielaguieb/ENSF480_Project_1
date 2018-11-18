package Presentation;

import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Database.*;

public class BuyerForm extends Form {
	
	private Container container;
	
	JRadioButton book, magazine, journal;
	JLabel name, id;
	JTextField search;
	
	public BuyerForm(String n, int i, Controller c) {
		super(n,i,c);
	}

	// TODO searches for document in database
	public void search() {
		
	}
	
	// TODO order a document that exists
	public void order() {
		
	}
	
	// TODO pay for a document
	public void pay() {
		
	}
	
	// TODO register to be a registered buyer
	public void register() {
		
	}
	
	// TODO registered buyer unregisters and becomes ordinary
	public void unregister() {
		
	}
	
	// TODO access promotion list as a registered buyer
	public void accessPromotionList() {
		
	}
	
	
}

