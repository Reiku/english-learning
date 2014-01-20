package client.observer;

public interface Observable {
	public void setObserver(Observer obs);
	public void notifyObserver(String message, String title, int type);
	public Observer getObserver();
}
