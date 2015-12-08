import java.io.*;
import java.net.*;

public class Item {

	private String itemName;
	private String userID;
	private String website;
	private int originalKeys;

	public Item(String name, String site) throws IOException {
		itemName = name;
		website = site;
		userID = getID();
		originalKeys = checkKeywords();
	}

	public String getName() {
		return itemName;
	}

	public String getKey() {
		return userID;
	}

	public String getWebsite() {
		return website;
	}

	public int getOrigKeys() {
		return originalKeys;
	}

	private static String getUrlSource(String url) throws IOException {
		String inputLine;

		URL webURL = new URL(url);
		URLConnection yc = webURL.openConnection();
		yc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
		StringBuilder a = new StringBuilder();

		while ((inputLine = in.readLine()) != null)
			a.append(inputLine);
		in.close();

		return a.toString();
	}

	public int checkKeywords() throws IOException {
		String websiteSource = getUrlSource(website);

		int stop = websiteSource.length() - userID.length();
		int count = 0;

		for (int i = 0; i < stop; i++) {
			if (websiteSource.substring(i, i + userID.length()).equals(userID)) {
				count++;
			}
		}

		return count;
	}

	public String getID() throws IOException {

		String search = "author may-blank id-";
		String id = "";
		String websiteSource = getUrlSource(website);

		int stop = websiteSource.length() - search.length();
		int count = 0;
		int idStart = 0;
		int idStop = 0;

		for (int i = 0; i < stop; i++) {
			if (websiteSource.substring(i, i + search.length()).equals(search)) {
				count++;
				if (count == 32) {
					int index = i + search.length();
					int index2 = i + search.length();

					while (!websiteSource.substring(index2, index2 + 1).equals("\"")) {
						index2++;
					}
					idStart = index;
					idStop = index2;
				}
			}
		}

		id = websiteSource.substring(idStart, idStop);

		return id;

	}

	public boolean hasChanged() throws IOException {
		boolean change = false;

		if (checkKeywords() != originalKeys) {
			change = true;
		}

		return change;
	}
	
	public void resetHasChanged() throws IOException{
		originalKeys = checkKeywords();
	}
}
