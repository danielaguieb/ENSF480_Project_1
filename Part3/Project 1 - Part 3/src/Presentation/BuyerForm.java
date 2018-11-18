package Presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import Database.*;

public class BuyerForm extends Form {
	
	private Container container;
	private boolean isRegistered;
	
	JRadioButton book, magazine, journal;
//	JLabel name, id;
	JTextField searchTextField;
	JButton searchButton, placeOrderButton, registerButton, accessPromotionListButton;
	JTextArea docNameTextArea, docAuthorsTextArea, docIDTextArea, docPriceTextArea;
	
	JList<String> promoJList;
	
	
	
	
	
	
	// STILL TODO MAKE THE JLIST STUFF WORKING
	
	
	
	
	
	
	
	
	
	
	public BuyerForm(String n, int i, Controller c, boolean isRegistered) {
		super(n,i,c);
		this.isRegistered = isRegistered;
		
		createFrame(name, ID);

//		setSize(420,315);		// placeholder dimensions
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createFrame(String n, int i){
		
		
		// for testing, isRegistered is true
		
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
			JPanel userInfoPanel = new JPanel();
				userInfoPanel.add(new JLabel(name));
				userInfoPanel.add(new JLabel(""+ID));
			mainPanel.add(userInfoPanel, BorderLayout.NORTH);
			
			JPanel mainPanelCenter = new JPanel(new BorderLayout());
				JPanel searchAndRegister = new JPanel();
					searchTextField = new JTextField("Search for a document", 20);
					searchButton = new JButton("Search");
					placeOrderButton = new JButton("Place order");
					
					searchAndRegister.add(searchTextField);
					searchAndRegister.add(searchButton);
					searchAndRegister.add(placeOrderButton);
					
					if (isRegistered) {
						registerButton = new JButton("Register");
						accessPromotionListButton = new JButton("Access promotion list");
						
						searchAndRegister.add(searchButton);
						searchAndRegister.add(registerButton);
					}
					
				mainPanelCenter.add(searchAndRegister, BorderLayout.NORTH);
				
				// card 1 will be for a search, card 2 will be for promotion list
				JPanel cardPanels = new JPanel(new CardLayout());
					JPanel documentInfo = new JPanel(new GridLayout(4, 1));
						docNameTextArea = new JTextArea();
						docAuthorsTextArea = new JTextArea();
						docIDTextArea = new JTextArea();
						docPriceTextArea = new JTextArea();
						documentInfo.add(docNameTextArea);
						documentInfo.add(docAuthorsTextArea);
						documentInfo.add(docIDTextArea);
						documentInfo.add(docPriceTextArea);
					cardPanels.add(documentInfo, "DOCPANEL");
					
					if (isRegistered) {
						JPanel promolistPanel = new JPanel();	
							promoJList = new JList<String>();
							promoJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							ListModel<String> listModel = new List
						cardPanels.add(promolistPanel, "PROMOPANEL");
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

