package Domain;

import java.util.ArrayList;
import Presentation.BuyerForm;

public class OrdinaryBuyer extends User {
	protected ArrayList<TransactionHistory> transactionList;
	protected BuyerForm buyerForm;

	public OrdinaryBuyer(String u, String p, int i) {
		super(u, p, i);
	}

}
