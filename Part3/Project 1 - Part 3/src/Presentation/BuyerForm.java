package Presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.naming.directory.SearchControls;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Database.*;

public class BuyerForm extends Form {
	
	private Container container;
	private boolean isRegistered;
	
	JRadioButton book, magazine, journal;
//	JLabel name, id;
	JTextField searchTextField;
	JButton searchButton, placeOrderButton, registerButton, accessPromotionListButton;
	JTextArea docName, docAuthors, docID, docPrice;
	
	
	
	
	
	
	
	
	// STILL TODO MAKE THE JLIST STUFF WORKING
	
	
	
	
	
	
	
	
	
	
	public BuyerForm(String n, int i, Controller c, boolean isRegistered) {
		super(n,i,c);
		this.isRegistered = isRegistered;
		
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
				JPanel searchAndRegister = new JPanel();
					searchTextField.setText("Search for a document");
					searchButton.setText("Search");
					placeOrderButton.setText("Place Order");
					
					searchAndRegister.add(searchTextField);
					searchAndRegister.add(placeOrderButton);
					
					// requires that the controller has the function isRegistered
					// for testing, consider that isRegistered is true
					if (isRegistered) {
						registerButton.setText("Register");
						accessPromotionListButton.setText("Access Promo List");
						searchAndRegister.add(searchButton);
						searchAndRegister.add(registerButton);
					}
					
				mainPanelCenter.add(searchAndRegister, BorderLayout.NORTH);
				
				// card 1 will be for a search, card 2 will be for promotion list
				JPanel cardPanels = new JPanel(new CardLayout());
					JPanel documentInfo = new JPanel(new GridLayout(4, 1));
						docName.setText("");
						docAuthors.setText("");
						docID.setText("");
						docPrice.setText("");
						documentInfo.add(docName);
						documentInfo.add(docAuthors);
						documentInfo.add(docID);
						documentInfo.add(docPrice);
					cardPanels.add(documentInfo, "DOCPANEL");
					
					if (isRegistered) {
						JPanel promolistPanel = new JPanel();	
					}
				mainPanelCenter.add(cardPanels, BorderLayout.CENTER);
					
				
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
		
		BuyerForm buyerForm = new BuyerForm("Dan", 12, new DocumentDatabaseController(), true);
		buyerForm.setVisible(true);
		
	}
}

