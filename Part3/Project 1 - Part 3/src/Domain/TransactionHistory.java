package Domain;

import java.util.ArrayList;
import java.util.Iterator;

public class TransactionHistory {
	private ArrayList<Receipt> receiptList;
	private int numReceipts;
	
	public TransactionHistory(ArrayList<Receipt> r, int n) {
		receiptList.addAll(r);
		numReceipts = n;
	}
	
	public void addReceipt(Receipt r) {
		receiptList.add(r);
	}
	
	public int totalSumPayments() {
		int sum = 0;
//		for(Iterator<Receipt> it = receiptList.iterator(); it.hasNext();) {
//			sum += it.next().getPrice();
//		}
		for(Receipt receipt : receiptList) {
			sum += receipt.getPrice();
		}
		return sum;
	}

}
