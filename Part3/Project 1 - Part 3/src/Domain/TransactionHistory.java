package Domain;

import java.util.ArrayList;

public class TransactionHistory {
	private ArrayList<Receipt> receiptList;
	private int numReceipts;
	
	public TransactionHistory() {
		receiptList = new ArrayList<Receipt>();
		numReceipts = 0;
	}
	
	public void addReceipt(Receipt r) {
		receiptList.add(r);
		numReceipts++;
	}
	
	public int totalSumPayments() {
		int sum = 0;
		for(Receipt receipt : receiptList) {
			sum += receipt.getPrice();
		}
		return sum;
	}

}
