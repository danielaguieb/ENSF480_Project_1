package Domain;

import java.util.ArrayList;

// for the observer pattern
public interface Observer {
	public abstract void update(ArrayList<Document> documents);
}
