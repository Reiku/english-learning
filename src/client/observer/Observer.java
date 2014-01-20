package client.observer;

public interface Observer {
	public void update(String message, String title, int type);
	public void dispose();
}
