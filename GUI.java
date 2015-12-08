import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GUI extends JPanel {

	private static final long serialVersionUID = 1L;

	static Main m = new Main();

	private JFrame frame;

	private JPanel panel;
	private JPanel group1;
	private JPanel group2;
	private JPanel group3;
	private JPanel group4;
	private JPanel group5;
	private JPanel group6;
	private JPanel group7;
	private JPanel group8;

	private JTextField searchName;
	private JTextField item;
	private JTextField phone;
	private JTextField interval2;
	public static JTextArea results;

	private SpinnerNumberModel intOptions;
	private static JSpinner interval;

	private JMenuBar menus;
	private JMenu fileMenu;
	private JMenuItem quitItem;
	private JMenuItem clearCurrent;
	private JMenuItem clearSaved;
	private JMenuItem removeItem;
	private JMenuItem removePhone;
	private JMenuItem saveCurrent;
	private static JMenuItem load;

	private JMenu helpMenu;
	private JMenuItem help;
	private JMenuItem about;

	private JButton add1;
	private JButton add2;
	private JButton show;
	private static JButton start;
	private static JButton stop;
	private static JButton save1;
	private static JButton save2;
	
	private static JCheckBox remove;

	private JComboBox<String> carriers;
	private String options[];

	private Color panelBackground;

	private CheckThread t2;

	public static void setStart(boolean b) {
		start.setEnabled(b);
	}

	public static void setStop(boolean b) {
		stop.setEnabled(b);
	}

	public static void setLoad(boolean b) {
		load.setEnabled(b);
	}

	public static void setSave(boolean b) {
		save1.setEnabled(b);
	}

	public static int getInterval() {
		return (Integer) interval.getValue() * 60000;
	}

	public static boolean getRemove() {
		return remove.isSelected();
	}

	public GUI() {

		// Frame
		frame = new JFrame("HardwareSwap Notifier");

		// Panels
		panel = new JPanel();
		group1 = new JPanel();
		group2 = new JPanel();
		group3 = new JPanel();
		group4 = new JPanel();
		group5 = new JPanel();
		group6 = new JPanel();
		group7 = new JPanel();
		group8 = new JPanel();

		// Menu Bar
		menus = new JMenuBar();
		fileMenu = new JMenu("File");
		clearCurrent = new JMenuItem("Clear");
		quitItem = new JMenuItem("Quit");
		load = new JMenuItem("Load");
		saveCurrent = new JMenuItem("Save All");
		clearSaved = new JMenuItem("Clear Saved");
		removeItem = new JMenuItem("Remove Item");
		removePhone = new JMenuItem("Remove Phone");
		saveCurrent = new JMenuItem("Save Current");
		helpMenu = new JMenu("Help");
		help = new JMenuItem("How To Use");
		about = new JMenuItem("About");

		// Buttons
		add1 = new JButton("Add");
		add2 = new JButton("Add");
		start = new JButton("Start");
		stop = new JButton("Stop");
		save1 = new JButton("Add/Save");
		save2 = new JButton("Add/Save");
		show = new JButton("Display Data");

		add1.setFocusPainted(false);
		add2.setFocusPainted(false);
		start.setFocusPainted(false);
		stop.setFocusPainted(false);
		save1.setFocusPainted(false);
		save2.setFocusPainted(false);
		show.setFocusPainted(false);

		stop.setEnabled(false);

		//CheckBox
		remove = new JCheckBox("Remove items when found");
		remove.setFocusable(false);
		
		// Listener
		ButtonListener listener = new ButtonListener();

		add1.addActionListener(listener);
		add2.addActionListener(listener);
		start.addActionListener(listener);
		stop.addActionListener(listener);
		load.addActionListener(listener);
		save1.addActionListener(listener);
		save2.addActionListener(listener);
		saveCurrent.addActionListener(listener);
		show.addActionListener(listener);
		quitItem.addActionListener(listener);
		clearCurrent.addActionListener(listener);
		clearSaved.addActionListener(listener);
		saveCurrent.addActionListener(listener);
		help.addActionListener(listener);
		about.addActionListener(listener);
		removePhone.addActionListener(listener);
		removeItem.addActionListener(listener);
		remove.addActionListener(listener);

		// Carrier Selection
		options = new String[10];
		options[0] = "AT&T";
		options[1] = "Boost Mobile";
		options[2] = "Cellular One";
		options[3] = "Nextel";
		options[4] = "T-Mobile";
		options[5] = "Tracfone";
		options[6] = "US Cellular";
		options[7] = "Sprint";
		options[8] = "Verizon";
		options[9] = "Virgin Mobile";

		carriers = new JComboBox<String>(options);

		// Text Fields
		searchName = new JTextField(15);
		item = new JTextField(15);
		phone = new JTextField(15);
		interval2 = new JTextField(15);
		results = new JTextArea(10, 20);

		JScrollPane scrollPane = new JScrollPane(results);

		results.setEditable(false);

		// Interval
		intOptions = new SpinnerNumberModel(5, 1, 60, 1);
		interval = new JSpinner(intOptions);
		JFormattedTextField tf = ((JSpinner.DefaultEditor) interval.getEditor()).getTextField();
		tf.setHorizontalAlignment(JFormattedTextField.LEFT);

		// Background
		panelBackground = new Color(237, 237, 237);

		panel.setBackground(panelBackground);
		searchName.setBackground(panelBackground);
		item.setBackground(panelBackground);
		phone.setBackground(panelBackground);
		interval.setBackground(panelBackground);

		// Panel Layouts
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		group1.setLayout(new BoxLayout(group1, BoxLayout.PAGE_AXIS));
		group2.setLayout(new BoxLayout(group2, BoxLayout.X_AXIS));
		group3.setLayout(new BoxLayout(group3, BoxLayout.PAGE_AXIS));
		group4.setLayout(new BoxLayout(group4, BoxLayout.X_AXIS));
		group5.setLayout(new BoxLayout(group5, BoxLayout.X_AXIS));
		group6.setLayout(new BoxLayout(group6, BoxLayout.X_AXIS));
		group7.setLayout(new BoxLayout(group7, BoxLayout.X_AXIS));
		group8.setLayout(new BoxLayout(group8, BoxLayout.X_AXIS));

		// Borders
		searchName.setBorder(BorderFactory.createTitledBorder(null, "Search Name", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.DARK_GRAY));
		item.setBorder(BorderFactory.createTitledBorder(null, "Item", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.DARK_GRAY));
		phone.setBorder(BorderFactory.createTitledBorder(null, "Cell Phone", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.DARK_GRAY));
		group5.setBorder(BorderFactory.createTitledBorder(null, "Check Interval (mins)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_JUSTIFICATION, null, Color.DARK_GRAY));

		// Sizes
		panel.setPreferredSize(new Dimension(200, 0));
		searchName.setMaximumSize(new Dimension(190, 50));
		item.setMaximumSize(new Dimension(190, 50));
		phone.setMaximumSize(new Dimension(185, 50));
		carriers.setMaximumSize(new Dimension(175, 20));
		group5.setPreferredSize(new Dimension(190, 47));
		group5.setMaximumSize(new Dimension(190, 47));

		add1.setMaximumSize(new Dimension(90, 20));
		save1.setMaximumSize(new Dimension(90, 20));
		add2.setMaximumSize(new Dimension(90, 20));
		save2.setMaximumSize(new Dimension(90, 20));
		start.setMaximumSize(new Dimension(90, 20));
		stop.setMaximumSize(new Dimension(90, 20));
		show.setMaximumSize(new Dimension(120, 20));

		// Add file menu items
		fileMenu.add(clearCurrent);
		fileMenu.add(clearSaved);
		fileMenu.add(load);
		fileMenu.add(removeItem);
		fileMenu.add(removePhone);
		fileMenu.add(saveCurrent);
		fileMenu.add(quitItem);

		// Add help menu items
		helpMenu.add(help);
		helpMenu.add(about);

		// Add to menu bar
		menus.add(fileMenu);
		menus.add(helpMenu);

		// Add items to panel
		group1.add(searchName);
		group1.add(item);

		group2.add(add1);
		group2.add(Box.createHorizontalStrut(10));
		group2.add(save1);
		
		group6.add(remove);

		group3.add(phone);
		group3.add(Box.createVerticalStrut(10));
		group3.add(carriers);

		group4.add(add2);
		group4.add(Box.createHorizontalStrut(10));
		group4.add(save2);

		group5.add(interval);

		group7.add(show);

		group8.add(start);
		group8.add(Box.createHorizontalStrut(10));
		group8.add(stop);

		panel.add(Box.createVerticalStrut(10));
		panel.add(group1);
		panel.add(Box.createVerticalStrut(10));
		panel.add(group2);
		panel.add(Box.createVerticalStrut(40));
		panel.add(group3);
		panel.add(Box.createVerticalStrut(10));
		panel.add(group4);
		panel.add(Box.createVerticalStrut(40));
		panel.add(group5);
		panel.add(Box.createVerticalStrut(30));
		panel.add(group6);
		panel.add(Box.createVerticalStrut(40));
		panel.add(group7);
		panel.add(Box.createVerticalStrut(10));
		panel.add(group8);
		panel.add(Box.createVerticalStrut(10));

		// Setup frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menus);
		frame.add(scrollPane);
		frame.add(BorderLayout.EAST, panel);
		frame.pack();
		frame.setSize(new Dimension(670, 620));
		frame.setVisible(true);
	}

	private class ButtonListener implements ActionListener {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == add1) {
				addItem();
			}

			if (e.getSource() == add2) {
				addPhone();
			}

			if (e.getSource() == save1) {
				saveItem();
			}

			if (e.getSource() == save2) {
				savePhone();
			}

			if (e.getSource() == help) {
				help();
			}

			if (e.getSource() == about) {
				about();
			}

			if (e.getSource() == show) {
				displayInformation();
			}

			if (e.getSource() == removeItem) {
				removeItem();
			}

			if (e.getSource() == removePhone) {
				removePhone();
			}

			if (e.getSource() == clearSaved) {
				clearSaved();
			}

			if (e.getSource() == saveCurrent) {
				saveCurrent();
			}

			if (e.getSource() == quitItem) {
				System.exit(1);
			}

			if (e.getSource() == start) {
				if (Main.getItems().isEmpty()) {
					results.setText("Please add items to search for.");
				} else {
					Main.setSleep((Integer) interval.getValue() * 60000);

					t2 = new CheckThread();
					t2.start();

					Main.setCont(true);
					start.setEnabled(false);
					stop.setEnabled(true);
				}
			}

			if (e.getSource() == stop) {
				t2.stop();
				Main.setCont(false);
				start.setEnabled(true);
				stop.setEnabled(false);
			}

			if (e.getSource() == load) {
				if (Main.getItems().isEmpty()) {
					Main.loadSettings();
					displayInformation();
				} else {
					results.setText("All current items must be cleared before loading. (File>Clear)");
				}
			}

			if (e.getSource() == clearCurrent) {
				searchName.setText("");
				item.setText("");
				results.setText("");
				phone.setText("");
				interval2.setText("");
				carriers.setSelectedIndex(0);
				Main.clearItems();
				Main.clearNumbers();
				displayInformation();
			}
		}
	}

	private void addItem() {
		if (!searchName.getText().isEmpty() && !item.getText().isEmpty()) {

			try {
				Main.setItem(searchName.getText(), "http://www.reddit.com/r/hardwareswap/search?q=" + item.getText() + "&sort=new&restrict_sr=on");

				results.setText("Current Items");

				displayInformation();

			} catch (IOException e1) {
				e1.printStackTrace();
			}

			searchName.setText("");
			item.setText("");

		} else {

			results.setText("Please provide all info for Item Name, Keyword, and Website");
		}
	}

	private void saveItem() {

		File f = new File("config.txt");

		if (f.exists() && !f.isDirectory()) {

			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("config.txt", true)))) {
				if (!searchName.getText().equals("") && !item.getText().equals("")) {

					out.println(searchName.getText() + "," + "http://www.reddit.com/r/hardwareswap/search?q=" + item.getText() + "&sort=new&restrict_sr=on");
					addItem();

				} else {

					results.setText("Please provide all info for Search Name and Item");
				}

			} catch (IOException e1) {
				results.append("Error saving to file.");
			}

		} else {
			Main.checkFiles();
		}
	}

	private void addPhone() {
		if (phone.getText().length() == 10) {
			if (carriers.getSelectedIndex() == 0) {
				Main.addPhone(phone.getText() + "@txt.att.net");
			}
			if (carriers.getSelectedIndex() == 1) {
				Main.addPhone(phone.getText() + "@myboostmobile.com");
			}
			if (carriers.getSelectedIndex() == 2) {
				Main.addPhone(phone.getText() + "@mobile.celloneusa.com");
			}
			if (carriers.getSelectedIndex() == 3) {
				Main.addPhone(phone.getText() + "@messaging.nextel.com");
			}
			if (carriers.getSelectedIndex() == 4) {
				Main.addPhone(phone.getText() + "@tmomail.net");
			}
			if (carriers.getSelectedIndex() == 5) {
				Main.addPhone(phone.getText() + "@txt.att.net");
			}
			if (carriers.getSelectedIndex() == 6) {
				Main.addPhone(phone.getText() + "@email.uscc.net");
			}
			if (carriers.getSelectedIndex() == 7) {
				Main.addPhone(phone.getText() + "@messaging.sprintpcs.com");
			}
			if (carriers.getSelectedIndex() == 8) {
				Main.addPhone(phone.getText() + "@vtext.com");
			}
			if (carriers.getSelectedIndex() == 9) {
				Main.addPhone(phone.getText() + "@vmobl.com");
			}
			displayInformation();
			phone.setText("");
			carriers.setSelectedIndex(0);
		} else {
			results.setText("Please add 10 digit cell number.");
		}
	}

	private void savePhone() {

		if (phone.getText().length() == 10) {
			File f = new File("config.txt");
			Scanner sc;

			ArrayList<String> config = new ArrayList<String>();

			try {
				sc = new Scanner(f);

				while (sc.hasNext()) {
					String s = sc.nextLine();
					config.add(s);
				}
				sc.close();

			} catch (FileNotFoundException e2) {
				results.setText("Error reading config.txt");
			}

			int i = 0;

			for (String s : config) {

				if (s.equals("PhoneNumbers:")) {
					break;
				}
				i++;
			}

			if (carriers.getSelectedIndex() == 0) {
				config.add(i + 1, phone.getText() + "@txt.att.net");
			}
			if (carriers.getSelectedIndex() == 1) {
				config.add(i + 1, phone.getText() + "@myboostmobile.com");
			}
			if (carriers.getSelectedIndex() == 2) {
				config.add(i + 1, phone.getText() + "@mobile.celloneusa.com");
			}
			if (carriers.getSelectedIndex() == 3) {
				config.add(i + 1, phone.getText() + "@messaging.nextel.com");
			}
			if (carriers.getSelectedIndex() == 4) {
				config.add(i + 1, phone.getText() + "@tmomail.net");
			}
			if (carriers.getSelectedIndex() == 5) {
				config.add(i + 1, phone.getText() + "@txt.att.net");
			}
			if (carriers.getSelectedIndex() == 6) {
				config.add(i + 1, phone.getText() + "@email.uscc.net");
			}
			if (carriers.getSelectedIndex() == 7) {
				config.add(i + 1, phone.getText() + "@messaging.sprintpcs.com");
			}
			if (carriers.getSelectedIndex() == 8) {
				config.add(i + 1, phone.getText() + "@vtext.com");
			}
			if (carriers.getSelectedIndex() == 9) {
				config.add(i + 1, phone.getText() + "@vmobl.com");
			}

			PrintWriter writer = null;
			try {
				writer = new PrintWriter("config.txt", "UTF-8");
				for (String s : config) {
					writer.println(s);
				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			writer.close();
			addPhone();
		} else {
			results.setText("Please add 10 digit cell number.");
		}
	}

	private void displayInformation() {
		results.setText("-Cell Phones-\n");
		if (Main.getEmails().isEmpty()) {
			results.append("\nNo Numbers");
		} else {
			ArrayList<String> emails = Main.getEmails();
			int index = 0;

			for (String s : emails) {
				index++;
				results.append("\n(" + index + ")   " + s);
			}
		}

		results.append("\n\n-Current Items-");
		if (Main.getItems().isEmpty()) {
			results.append("\n\nNo Items");
		} else {
			ArrayList<Item> items = Main.getItems();
			int index = 0;

			for (Item i : items) {
				String s = i.getWebsite().substring(46, i.getWebsite().length());

				index++;

				for (int j = 0; j < s.length(); j++) {
					if (s.substring(j, j + 1).equals("&")) {
						s = s.substring(0, j);
					}
				}

				results.append("\n\n(" + index + ")\nName: \t" + i.getName() + "\nItem: \t" + s);
			}
			results.append("\n\n");
		}
	}

	private void removeItem() {
		String s = JOptionPane.showInputDialog("Index of item to be removed.");
		try {
			int index = Integer.parseInt(s) - 1;
			Main.removeItem(index);
			saveCurrent();
			displayInformation();
		} catch (NumberFormatException nfe) {
			results.setText("Please type in the index number of the item.");
		}
	}

	private void removePhone() {
		String s = JOptionPane.showInputDialog("Index of cell phone to be removed.");
		try {
			int index = Integer.parseInt(s) - 1;
			Main.removePhone(index);
			saveCurrent();
			displayInformation();
		} catch (NumberFormatException nfe) {
			results.setText("Please type in the index number of the cell phone.");
		}
	}

	private void saveCurrent() {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter("config.txt", "UTF-8");
			writer.println("PhoneNumbers:");

			for (String s : Main.getEmails()) {
				writer.println(s);
			}

			writer.println("Items:");

			for (Item s : Main.getItems()) {
				writer.println(s.getName() + "," + s.getWebsite());
			}

			results.setText("Current settings have been saved sucessfully.");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		writer.close();
	}

	private void clearSaved() {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter("config.txt", "UTF-8");
			writer.println("PhoneNumbers:");
			writer.println("Items:");
			results.setText("Config.txt has been cleared.");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		writer.close();
	}

	private void help() {
		results.setText("-How to use this program-");
		results.append("\n\n\nSearch Name : \tFor your identification only. Does not effect anything.");
		results.append("\n\nItem : \tThis is the item you are searching for. Make sure to be vague.");
		results.append("\n\tIt is the same as searching Reddit.com/r/HardwareSwap/new.");
		results.append("\n\tFor example if I were searching for a GTX 670 I would type");
		results.append("\n\tin 670 for the item.");
		results.append("\n\nCell Phone : \tThis is the number that will recieve a text message when");
		results.append("\n\ta new post is found.");
		results.append("\n\nCheck Interval : \tThis is how often it will check for a new post in minutes.");
		results.append("\n\nRemove Item : \tStops searching for item when a new post is found.");
		results.append("\nWhen Found \t");
		results.append("\n\nDisplay Data : \tThis is will show you the current cell phones and items.");
		results.append("\n\nAdd : \tThis will add the inputed information for this session only.");
		results.append("\n\nAdd/Save : \tThis will add the inputed information and save it to config.txt.");
		results.append("\n\nStart/Stop : \tThese will start and stop the program respectively.");
		results.append("\n\n\nClear : \tThis will clear currently loaded data.");
		results.append("\n\nClear Saved : \tThis will reset the config.txt file.");
		results.append("\n\nLoad : \tThis will load the config.txt file. Can only be done after clearing");
		results.append("\n\tcurrent items.");
		results.append("\n\nRemove Item : \tRemoves item from session and config.txt given the index");
		results.append("\n\tof the item. (Can be found in display data)");
		results.append("\n\nRemove Item : \tRemoves phone from session and config.txt given the index");
		results.append("\n\tof the phone. (Can be found in display data)");
		results.append("\n\nSave Current : \tSaves everything in current session and overwrites config.txt");
		results.append("\n\nQuit : \tThis will close the program.");

	}

	private void about() {
		results.setText("-About-");
		results.append("\n\n\nVersion 1.1.");
		results.append("\n\nCreated by Sean Crowley");
		results.append("\n\nCrowley.P.Sean@Gmail.com");
		results.append("\n\nReddit.com/u/Crowley2012");
		results.append("\n\n\nFeel free to contact me via email or reddit with bugs/new features.");

	}
}
