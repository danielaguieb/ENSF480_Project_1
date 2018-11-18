package Domain;
import Database.*;

import java.util.ArrayList;

public class PromotionList implements Subject {
	private ArrayList<Observer> observers;
	private ArrayList<Document> documents;
	private static PromotionList promotionList = null;
	
	public static PromotionList getInstance()
	{
		if (promotionList == null)
			promotionList = new PromotionList();
		return promotionList;
	}
	
	private PromotionList() {
		observers = new ArrayList<Observer>();
		documents = new ArrayList<Document>();
	}
	
	public void setDocuments(ArrayList<Document> docs)
	{
		documents = docs;
	}
	
	public void setObservers(ArrayList<Observer> obs)
	{
		observers = obs;
	}
	
	public void addDocument(Document doc)
	{
		documents.add(doc);
		notifyObservers();
	}
	
	public void removeDocument(Document doc)
	{
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i) == doc) {
				documents.remove(i);
				break;
			}
		}
		notifyObservers();
	}
	
	public void updateDocument(Document doc) 
	{
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i) == doc) {
				documents.remove(i);
				break;
			}
		}
		addDocument(doc);
	}

	public void remove(Observer observer) {
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i) == observer) {
				observers.remove(i);
				return;
			}
		}
	}

	public void register(Observer observer) {
		observers.add(observer);
		observer.update(documents);
	}

	public void notifyObservers() {
		for (Observer o: observers)
			o.update(documents);
	}
	
	public static void main(String[] args)
	{
		PromotionList list = new PromotionList();
		list.notifyObservers();
	}
}	

// Notes:
// the promotionlist class will have to be created when the program starts up
// when that happens, it goes into the database and gets the promtion documents and which buyers are registered

