import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static ArrayList<Item> itemArray;
	private static ArrayList<String> emailArray;

	private static int sleep;

	private static boolean cont;
	private static boolean itemFound;

	public Main() {
		itemArray = new ArrayList<Item>();
		emailArray = new ArrayList<String>();
		sleep = 0;
		cont = true;
		itemFound = false;
	}

	public static ArrayList<Item> getItems() {
		return itemArray;
	}

	public static ArrayList<String> getEmails() {
		return emailArray;
	}

	public static int getSleep() {
		return sleep;
	}

	public static boolean getCont() {
		return cont;
	}

	public static boolean getItemFound() {
		return itemFound;
	}

	public static void addPhone(String phone) {
		emailArray.add(phone);
	}

	public static void setItem(String item, String site) throws IOException {
		itemArray.add(new Item(item, site));
	}

	public static void clearItems() {
		itemArray.clear();
	}

	public static void clearNumbers() {
		emailArray.clear();
	}

	public static void setCont(boolean b) {
		cont = b;
	}

	public static void setSleep(int num) {
		sleep = num;
	}

	public static void removeItem(int num) {
		itemArray.remove(num);
	}

	public static void removePhone(int num) {
		emailArray.remove(num);
	}

	public static void start() throws InterruptedException, IOException {

		GUI.results.setText("");

		while (itemArray.size() > 0 && cont) {

			if (GUI.results.getLineCount() > 35) {
				GUI.results.setText("");
			}

			for (Item i : itemArray) {
				GUI.results.append("New post for: " + i.getName() + " : " + i.hasChanged() + "\n");
				itemFound = false;

				if (i.hasChanged()) {
					
					GUI.results.append("Sending texts for item: " + i.getName());
					
					for (String s : emailArray) {
						SendMail.send(s, i.getName(), i.getWebsite());
					}
					
					if(GUI.getRemove()){
						itemArray.remove(i);
						GUI.results.append("\n\nItems remaining: " + itemArray.size());
					}else{
						i.resetHasChanged();
					}
					
					itemFound = true;
					break;
				}
			}

			if (!itemFound) {
				GUI.results.append("\nSleeping for " + GUI.getInterval() / 60000 + " minutes.");
				Thread.sleep(GUI.getInterval());
			}

			GUI.results.append("\n\n");
		}

		if (!cont) {
			GUI.results.append("Stopped the search.");
		} else {
			GUI.results.append("All Items Found!");
		}

		GUI.setStop(false);
		GUI.setStart(true);

	}

	public static void loadSettings() {

		try {
			boolean phoneNumbers = false;
			boolean items = false;

			File f = new File("config.txt");
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {
				String s = sc.nextLine();
				if (items == true) {
					String info[] = s.split(",");
					try {
						Main.setItem(info[0].trim(), info[1].trim());

					} catch (IOException e1) {
						System.out.println("Error Adding Items From File");
					}
				}
				if (s.length() > 5 && s.substring(0, 6).equals("Items:")) {
					phoneNumbers = false;
					items = true;
				}
				if (phoneNumbers == true) {
					addPhone(s);
				}
				if (s.length() > 12 && s.substring(0, 13).equals("PhoneNumbers:")) {
					phoneNumbers = true;
				}
			}

			sc.close();
		}

		catch (IOException e) {
			GUI.results.append("Failed to read: config.txt");
		}
	}

	public static void checkFiles() {

		File f = new File("config.txt");

		if (!f.exists() || f.isDirectory()) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter("config.txt", "UTF-8");
				writer.println("PhoneNumbers:");
				writer.println("Items:");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			writer.close();
		}
	}

	public static void main(String[] args) {
		GUIThread t1 = new GUIThread();
		t1.start();
		checkFiles();
		loadSettings();
	}
}
