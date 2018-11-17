package Domain;

import java.util.ArrayList;
import Presentation.BuyerForm;

public class OrdinaryBuyer extends User {
	protected ArrayList<TransactionHistory> transactionList;
	protected BuyerForm buyerForm;

	public OrdinaryBuyer(String u, String p, int i) {
		super(u, p, i);
	}
	
	public void register()
	{
		// once this buyer registers, it should be added to the promotion list's list of observers. 
		// but it will still an instance of OrdinaryBuyer, and therefore cause problems
		// it might be wise to simply remake the class as a registeredBuyer
	}

}
