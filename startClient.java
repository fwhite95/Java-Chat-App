package Client;

public class startClient {

	public static void main(String[] args) {
		try {
			System.out.println("Hello");
			MyChat c = new MyChat("Freddie");
			Thread t1 = new Thread(c);
			t1.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
