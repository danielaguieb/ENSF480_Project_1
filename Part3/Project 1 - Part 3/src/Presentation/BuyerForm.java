package Presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Database.*;

public class BuyerForm extends Form {
	
	private Container container;
	
	JRadioButton book, magazine, journal;
//	JLabel name, id;
	JTextField searchTextField;
	JButton searchButton;
	JTextArea documentName, documentID;
	
	public BuyerForm(String n, int i, Controller c) {
		super(n,i,c);
		
		createFrame(name, ID);
		
		setSize(420,315);		// placeholder dimensions
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public void createFrame(String n, int i){
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
			JPanel userInfoPanel = new JPanel();
				userInfoPanel.add(new JLabel(name));
				userInfoPanel.add(new JLabel(""+ID));
			mainPanel.add(userInfoPanel, BorderLayout.NORTH);
			
			JPanel mainPanelCenter = new JPanel(new BorderLayout());
				JPanel searchRow = new JPanel();
					searchRow.add(searchTextField);
					searchRow.add(searchButton);
				mainPanelCenter.add(searchRow, BorderLayout.NORTH);
				
				JPanel documentInfo = new JPanel(new GridLayout(2, 1));
					documentName.setText("");
					documentID.setText("");
					documentInfo.add(documentName);
					documentInfo.add(documentID);
				mainPanelCenter.add(documentInfo, BorderLayout.CENTER);
			mainPanel.add(mainPanelCenter, BorderLayout.CENTER);
		
		getContentPane().add(mainPanel);
		
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
	
	public static void main(String[] args) {
		
		BuyerForm buyerForm = new BuyerForm("Dan", 12, new DocumentDatabaseController());
		buyerForm.setVisible(true);
		
	}
}

