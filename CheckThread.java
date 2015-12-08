import java.io.IOException;

class CheckThread extends Thread {

	public void run() {
		try {
			Main.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
