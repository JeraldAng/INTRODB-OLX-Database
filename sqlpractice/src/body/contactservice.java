package body;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import login.loginviewer;

public class contactservice {
	private List<reportdata> rep;
	private int caseid = 1;
	private int transid = 1;
	private List<transactdata> trans;
	private JComboBox byear, bday, bmonth;
	private contactservice cs = this;
	private List<requestinfo> buyreq;
	private JTextArea chathist;
	private JComboBox items;
	private List<chathist> chat;
	private int day, month, year;
	private int adid = 1;
	private JComboBox request;
	private List<monthlytransactdata> mntr;
	private JTable adtable;
	private DefaultTableModel dataad;
	private JButton selback, selok;
	private JComboBox selcat1;
	private JLabel selprod, selcat;
	private JTextField selprod1;
	private String nameup;
	private JButton buyad1, buyad2;
	private int showind;
	private contactdb db;
	private JComboBox locser;
	private JScrollPane adpane;
	private List<requestinfo> reqinf = new ArrayList<requestinfo>(1000);
	private List<advertisedata> asda = new ArrayList<advertisedata>(1000);
	private loginviewer logview;
	private JTextArea chathis;
	private int ind = 1;
	private int noofads;
	private int gam = 0;
	private JFrame frame, regf;
	private JTextArea text1, text2;
	private JTextField mob;
	private JLabel buyadlab1, buyadlab2;
	private JLabel lab1, lab2, lab3, lab4, lab5, lab6, name, mobile, olxg, locat, olxgdisp1, mainad, profad;
	private JComboBox chatcom;
	private JButton log, reg, conf, back, mainb1, mainb2, mainb3, mainb4, logout, profile, profback, proflog, onehund, twohund, trehund, buylog, buyback;
	private JPasswordField pass, pass2, passconf;
	private List<main> maj = new ArrayList<main>(1000);
	public List<transactdata> gettransactions() {
		String query = "SELECT * FROM transaction";
		List<transactdata> tr = new ArrayList<transactdata>(1000);
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				transid++;
				transactdata tra = new transactdata();
				tra.setTransactionid(rs.getString(1));
				tra.setProduct(rs.getString(2));
				tra.setBuyer(rs.getString(3));
				tra.setSeller(rs.getString(4));
				tra.setPrice(rs.getDouble(5));
				tra.setDate(rs.getString(6));
				tra.setTime(rs.getString(7));
				tra.setLocation(rs.getString(8));
				tr.add(tra);
			}
			
		}
		catch(Exception e) {
			System.out.println("WASSUP");
		}
		return tr;
	}
	public void inserttransact(String transid, String buyerid, String product, double price, String date, String time, String location) {
		String sellerid = maj.get(showind).getId();
		String query = "INSERT into transaction (transactionid, product, buyerid, sellerid, price, date, time, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, transid);
			ps.setString(2, product);
			ps.setString(3, buyerid);
			ps.setString(4, sellerid);
			ps.setFloat(5, (float)price);
			ps.setString(6, date);
			ps.setString(7, time);
			ps.setString(8, location);
			
			transactdata tra = new transactdata();
			tra.setTransactionid(transid);
			tra.setBuyer(buyerid);
			tra.setSeller(sellerid);
			tra.setProduct(product);
			tra.setDate(date);
			tra.setTime(time);
			tra.setPrice(price);
			tra.setLocation(location);
			
			
			int added = ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("transfer in...");
		}
	}
	public List<advertisedata> getexistad() {
		String query = "SELECT * FROM advertisments";
		List<advertisedata> adves = new ArrayList<advertisedata>(1000);
		
		Connection conn = db.getConnection(); 
		
		try {
			PreparedStatement state = conn.prepareStatement(query);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				advertisedata ave = new advertisedata();
				ave.setAdid(rs.getString(1));
				ave.setCategory(rs.getString(8));
				ave.setAmount(rs.getInt(3));
				ave.setCondition(rs.getString(4));
				ave.setPrice(rs.getDouble(5));
				ave.setUserid(rs.getString(6));
				ave.setProduct(rs.getString(2));
				ave.setSold(rs.getString(7));
				ave.setDatesold(rs.getString(8));
				adves.add(ave);
				adid++;
			}
		}
		catch (Exception e) {
			System.out.println("TRY AGAIN");
		}
		
		return adves;
	}
	public void add (main m) {
		//add a contact
		String query = "INSERT INTO olxuser " + "(olxuserid, username, Gender, pass, birthday, age, mobileno, location, email, olxgold, transactnum) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = db.getConnection();
		
		try {
			PreparedStatement state = conn.prepareStatement(query);
			
			//ResultSet rs = state.executeQuery();
		
			state.setString(1, m.getId());
			
			state.setString(2, m.getName());
			state.setString(3, m.getGender());
			state.setString(4, m.getPass());
			state.setString(5, m.getBirthday());
			state.setInt(6, m.getAge());
			state.setString(7, m.getNumber());
			
			state.setString(8, m.getLocation());
			state.setString(9, m.getEmail());
	
			state.setFloat(10, 0);
			state.setInt(11, 0);
			int added = state.executeUpdate();
			//return added;
		}
		catch (SQLException e) {
			System.out.println("FAIL");
		}
		//return false;
		maj.add(m);
	}
	public List<main> getAll() {
		//get contacts
		Connection connection = db.getConnection();
		List<main> contacts = new ArrayList(1000);
		
		String query = "SELECT * FROM olxuser";
		
		
		try {
			PreparedStatement state = connection.prepareStatement(query);
			ResultSet rs = state.executeQuery();
			int dex = 0;
			while (rs.next()) {
				main m = new main();
				m.setId(rs.getString(1));
				m.setName(rs.getString(2));
				m.setGender(rs.getString(3));
				m.setPass(rs.getString(4));
				m.setBirthday(rs.getString(5));
				m.setAge(rs.getInt(6));
				m.setNumber(rs.getString(7));
				m.setLocation(rs.getString(8));
				m.setEmail(rs.getString(9));
				m.setOlxgold(rs.getDouble(10));
				m.setItembought(rs.getString(11));
				m.setItemsold(rs.getString(12));
				m.setTransactnum(rs.getInt(13));
				contacts.add(m);
				ind++;
				//int hun = contacts.get(dex).getId();
				
				//System.out.println("A " + hun + " B " + ag);
				
			}
		}
		catch (SQLException er) {
			System.out.println("FAIL");
		}
		return contacts;
	}
	public void addadvslot(int func) {
		String query = "INSERT INTO advertisments (idadvertisments, amount, price, userid) VALUES (?, ?, ?, ?)";
		Connection conn = db.getConnection();
		String none = "n/a";
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			String idg = idgive("ADOLX", adid);
			
		
			ps.setString(1, idg);
			ps.setInt(2, 0);
			ps.setFloat(3, 0);
			if (func == 1) {
				ps.setString(4, maj.get(maj.size() - 1).getId());
			}
			else {
				
				ps.setString(4, maj.get(showind).getId());
			}
			
		
			advertisedata ade = new advertisedata();
			ade.setAdid(idg);
			ade.setProduct(null);
			ade.setPrice(0.00);
			
			if (func == 1) {
				ade.setUserid(maj.get(maj.size() - 1).getId());
			}
			else {
				ade.setUserid(maj.get(showind).getId());
			}
			ade.setAmount(0);
			ade.setCategory(null);
			ade.setSold(null);
			ade.setCondition(null);
			ade.setDatesold(null);
			asda.add(ade);
			
			int added = ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("WOAH");
		}
	}
	
	public boolean delete(int id) {
		//delete a contact
		double sav = 100;
		return false;
	}
	
	public void updatelocat () {
		//update a contact
		String state = "UPDATE olxuser SET location = ? WHERE username = ?";
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(state);
			ps.setString(1, maj.get(showind).getLocation());
			ps.setString(2, nameup);
			int added = ps.executeUpdate();
			
		}
		catch(Exception e) {
			
		}
		
	}
	public void setupf() {
		
		frame = new JFrame();
		frame.setLayout(null);
	}
	public void showfr() {
		frame.setVisible(true);
	}
	public void removecomp() {
		frame.getContentPane().removeAll();
	}
	public void login() {
		if (frame != null) {
			removecomp();
		}
		setlayout(1);
		frame.setContentPane(new JLabel());
		frame.setBounds(300, 200, 600, 400);
		
		text1 = new JTextArea();
		pass = new JPasswordField();
		lab1 = new JLabel();
		lab2 = new JLabel();
		log = new JButton("Log in");
		reg = new JButton("Register");
		JButton admi = new JButton("Admin");
		
		text1.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 150, 300, 30);
		pass.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 50, 300, 30);
		lab1.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 150, 100, 30);
		lab2.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 50, 100, 30);
		log.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 + 50, 150, 60);
		reg.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 + 50, 150, 60);
		admi.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 50, 150, 60);
		
		lab1.setText("User");
		lab2.setText("Pass");
		log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int found = 0;
				for (int i = 0; i < maj.size(); i++) {
				
					
					if (maj.get(i).getName().equals(text1.getText()) && maj.get(i).getPass().equals(pass.getText())) {
						nameup = text1.getText();
			
						showind = i;
						found++;
						reqinf = getrequests();
						chat = retrievechat();
						buyreq = getnotif();
						trans = gettransactions();
						rep = retrievereports();
						mntr = retrievemonthly(); 
						mainmenu();
						break;
					}
					
					
				}
				if (found == 0) {
					errormsg("Invalid username and/or password");
				}
			}
			
		});
		
		admi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				admintransacts ada = new admintransacts(cs, db);
				frame.setVisible(false);
			}
			
		});
		reg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				register();
			}
			
		});
		
		frame.add(text1);
		frame.add(pass);
		frame.add(lab1);
		frame.add(lab2);
		frame.add(reg);
		frame.add(log);
		frame.add(admi);
		
		frame.validate();
		frame.setVisible(true);
		repaint();
	}
	public void setlayout(int num) {
		if (num == 1) {
			frame.setLayout(null);
		}
		else {
			frame.setLayout(new BorderLayout());
		}
	}
	public int noofads() {
		int num = 0;
		for (int i = 0; i < asda.size(); i++) {
			
			if (maj.get(showind).getId().equals(asda.get(i).getUserid()) && asda.get(i).getProduct() == null) {
				
				num++;
				
			}
		}
		return num;
	}
	public void addchatusers() {
		//adds users in the chat contacts who requested for a 
		String checker = maj.get(showind).getName() + ", ";
		for (int i = 0; i < reqinf.size(); i++) {
			
			String buyer = reqinf.get(i).getBuyer();
			
			if (!(checker.contains(reqinf.get(i).getBuyer())) && reqinf.get(i).getRequeststat().equals("pending")) {
				
				
		
				checker = checker + "" + buyer + ", ";
				chatcom.addItem(buyer);
			}
		}
		for (int i = 0; i < chat.size(); i++) {
			String name = "";
			String sender = chat.get(i).getSender();
			String receiver = chat.get(i).getReceiv();
			for (int j = 0; j < maj.size(); j++) {
				String id = maj.get(j).getId();
				if (id.equals(sender) || id.equals(receiver)) {
					name = maj.get(j).getName();
					if (!(checker.contains(name))) {
						checker = checker + "" + name + ", ";
						chatcom.addItem(name);
					}
				}
			}
		}
	
	}
	public void mainmenu() {
		noofads = noofads();
		ImageIcon img = new ImageIcon("C:\\Users\\shine\\Documents\\java workspace\\sqlpractice\\src\\body\\Main Menu.jpg");
		frame.setContentPane(new JLabel(img));
		
		removecomp();
		setlayout(1);
		boundsetter(1);
		JButton notif = new JButton();
		olxgdisp1 = new JLabel("" + maj.get(showind).getOlxgold());
		chatcom = new JComboBox();
		logout = new JButton("");
		mainb1 = new JButton("");
		mainb2 = new JButton("");
		mainb3 = new JButton("");
		mainb4 = new JButton("");
		profile = new JButton("");
		mainad = new JLabel("" + noofads);
		
		/*chatcom.addItem("JUN");
		chatcom.addItem("JEN");
		chatcom.addItem("JAN");
		*/
		chatcom.setBounds(frame.getWidth() / 2 - 150, frame.getHeight() / 2 + 50, 120, 90);
		mainb1.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 100, 120, 90);
		mainb2.setBounds(frame.getWidth() / 2 - 150, frame.getHeight() / 2 - 100, 120, 90);
		profile.setBounds(frame.getWidth() / 2 + 155, frame.getHeight() / 2 - 100, 120, 90);
		mainb3.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 + 50, 120, 90);
		logout.setBounds(frame.getWidth() / 2 + 155, frame.getHeight() / 2 + 50 , 120, 90);
		notif.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 + 50, 120, 90);
		
		olxgdisp1.setBounds(frame.getWidth() / 2 - 170, frame.getHeight() / 2 - 235, 120, 90);
		mainad.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() / 2 - 215, 150, 90);
		mainb4.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 - 100, 120, 90);
		
		
		notif.setOpaque(false);
		notif.setContentAreaFilled(false);
		notif.setBorderPainted(false);
		mainb1.setOpaque(false);
		mainb1.setContentAreaFilled(false);
		mainb1.setBorderPainted(false);
		mainb2.setOpaque(false);
		mainb2.setContentAreaFilled(false);
		mainb2.setBorderPainted(false);
		mainb3.setOpaque(false);
		mainb3.setContentAreaFilled(false);
		mainb3.setBorderPainted(false);
		mainb4.setOpaque(false);
		mainb4.setContentAreaFilled(false);
		mainb4.setBorderPainted(false);
		profile.setOpaque(false);
		profile.setContentAreaFilled(false);
		profile.setBorderPainted(false);
		logout.setOpaque(false);
		logout.setContentAreaFilled(false);
		logout.setBorderPainted(false);
		chatcom.addItem("select user");
		addchatusers();
		chatcom.setSelectedIndex(0);
		
		notif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				requests();
			}
			
		});
		mainb4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				otheradtableview(" where username <> ?", 1 , "all categories", "all sellers", "all locations", 0, 0, 0);
			}
			
		});
		mainb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				olxpurchase();
			}
			
		});
	
		chatcom.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				gam++;
				if (gam == 2) {
					String receiv = chatcom.getSelectedItem().toString().trim();
					gam = 0;
					int index = 0;
					for (int i = 0; i < maj.size(); i++) {
						String name = maj.get(i).getName();
						if (name.equals(receiv)) {
							index = i;
							break;
						}
					}
					viewprofile(index, 1);
				}
			}
			
		});
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
			
		});
		profile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				viewprofile(showind, 1);
			}
			
		});
		mainb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buyads();
			}
			
		});
		mainb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selprod();
			}
			
		});
		int count = 0;
		for (int i = 0; i < reqinf.size(); i++) {
			if(reqinf.get(i).getRequeststat().equals("pending")) {
				count++;
			}
		}
		
		for (int i = 0; i < buyreq.size(); i++) {
			if(!(buyreq.get(i).getRequeststat().equals("pending"))) {
				count++;
			}
		}
		
		frame.add(notif);
		frame.add(olxgdisp1);
		frame.add(profile);
		frame.add(chatcom);
		frame.add(mainb1);
		frame.add(mainb2);
		frame.add(mainb3);
		frame.add(logout);
		frame.add(mainb4);
		frame.add(mainad);
		chatcom.setVisible(true);
		frame.validate();
		repaint();
	}
	public void bordersize() {
		frame.setSize(300, 500);
	}
	public void selprod() {
		//selling of the product ui
		removecomp();
		ImageIcon img = new ImageIcon("C:\\Users\\shine\\Documents\\java workspace\\sqlpractice\\src\\body\\Sell Product.jpg");
		frame.setContentPane(new JLabel(img));
		int noofads = noofads();
		JLabel condit = new JLabel("Condition");
		JTextField condinp = new JTextField();
		JLabel price = new JLabel("Price");
		JLabel left = new JLabel("" + noofads);
		JTextField pricetext = new JTextField();
		JTextField amttext = new JTextField();
		selprod = new JLabel("Product Name:");
		JLabel amtname = new JLabel("Amount:");
		selcat = new JLabel("Category");
		selprod1 = new JTextField();
		selcat1 = new JComboBox();
		selback = new JButton("");
		selok = new JButton("");
		
		selcat1.addItem("Cellphones");
		selcat1.addItem("Game Consoles");
		selcat1.addItem("Furniture");
		selcat1.addItem("Pets");
		selcat1.addItem("Tools");
		selcat1.addItem("Accessories");
		selcat1.addItem("Others");
		condit.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 - 100, 120, 40);
		condinp.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 30, 120, 40);
		selcat.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 - 150, 120, 40);
		selprod.setBounds(frame.getWidth() / 2 -250, frame.getHeight() / 2 - 200, 120, 40);
		selcat1.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 150, 120, 40);
		selprod1.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 130, 120, 40);
		selcat1.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 80, 120, 40);
		selok.setBounds(frame.getWidth() / 2 - 140, frame.getHeight() / 2 + 130, 130, 40);
		selback.setBounds(frame.getWidth() / 2 + 10, frame.getHeight() / 2 + 130, 130, 40);
		amttext.setBounds(frame.getWidth() / 2 + 120, frame.getHeight() / 2 - 130, 120, 40);
		amtname.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 - 80, 120, 40);
		price.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2- 150, 120, 40);
		pricetext.setBounds(frame.getWidth() / 2 + 120, frame.getHeight() / 2 - 80, 120, 40);
		left.setBounds(frame.getWidth() / 2 - 80, frame.getHeight() / 2 + 70, 150, 40);
		
		selback.setOpaque(false);
		selback.setContentAreaFilled(false);
		selback.setBorderPainted(false);
		selok.setOpaque(false);
		selok.setContentAreaFilled(false);
		selok.setBorderPainted(false);
		selback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainmenu();
			}
			
		});
		selok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					//check if the location is established, if not then no selling of ads
					if (noofads() > 0) {
						if (maj.get(showind).getLocation() == null) {
							errormsg("Please set your location first");
						}
						else {
							try {
								double val = Double.parseDouble(pricetext.getText().trim());
								int val2 = Integer.parseInt(amttext.getText().trim());
								String product = selprod1.getText();
								String categ = selcat1.getSelectedItem().toString();
								String condition = condinp.getText().trim();
								if (!(product.isEmpty()) && !(categ.isEmpty())) {
									updateadtable(selprod1.getText(), val, selcat1.getSelectedItem().toString(), val2, condition);
									int noofads = noofads();
									left.setText("" + noofads);
									repaint();
								}
								else {
									errormsg("Please place the required data");
								}
							}
							catch (Exception er) {
								errormsg("Invalid inputs");
							}
						}
					}
					else {
						errormsg("Not enough slots");
					}
					
				}
				catch (Exception er) {
					System.out.println("Invalid price");
				}
			}
			
		});
	
		frame.add(condinp);
		frame.add(selok);
		frame.add(selback);
		
	
		frame.add(selprod1);
		frame.add(selcat1);
	
		frame.add(left);
		frame.add(pricetext);
	
		frame.add(amttext);
		frame.validate();
		repaint();
	}
	
	public List<addeets> selectads(String addit, int num, String param1, String param2, String param3) {
		String query = "SELECT product, category, price, username, location, idadvertisments, olxuserid FROM olxuser olu, advertisments adv" + addit + " AND userid = olxuserid AND product <> \"n/a\" AND sold = \"no\"";
		
		if (num == 2) {
			query = query + " AND category = ?";
		}
		else if (num == 3) {
			query = query + " AND username = ?";
		}
		else if (num == 4){
			query = query + " AND category = ? AND username = ?";
		}
		else if (num == 5) {
			query = query + " AND location = ?";
		}
		else if (num == 6) {
			query = query + " AND category = ? AND location = ?";
		}
		else if (num == 7) {
			query = query + " AND username = ? AND location = ?";
		}
		else if (num == 8) {
			query = query + " AND category = ? AND username = ? AND location = ?";
		}
		
		Connection conn = db.getConnection();
		List<addeets> ade = new ArrayList<addeets>(1000);
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, maj.get(showind).getName());
			if (num == 2) {
				ps.setString(2, param1);
			}
			else if (num == 3) {
				ps.setString(2, param2);
			}
			else if (num == 4){
				ps.setString(2, param1);
				ps.setString(3, param2);
			}
			else if (num == 5) {
				ps.setString(2, param3);
			}
			else if (num == 6) {
				ps.setString(2, param1);
				ps.setString(3, param3);
			}
			else if (num == 7) {
				ps.setString(2, param2);
				ps.setString(3, param3);
			}
			else if (num == 8) {
				ps.setString(2, param1);
				ps.setString(3, param2);
				ps.setString(4, param3);
			}
			
			ResultSet rs = ps.executeQuery();
		
			while (rs.next()) {
				addeets ad = new addeets();
				ad.setCategory(rs.getString(2));
				ad.setProduct(rs.getString(1));
				ad.setPrice(rs.getDouble(3));
				ad.setSeller(rs.getString(4));
				ad.setLocation(rs.getString(5));
				ad.setSellerid(rs.getString(7));
				ad.setAdid(rs.getString(6));
				ade.add(ad);
				
			}
		}
		catch(Exception e) {
			System.out.println("CHOTO");
		}
		return ade;
	}
	public void otheradtableview(String cond, int num, String param1, String param2, String param3, int comb1ind, int comb2ind, int comb3ind) {
		removecomp();
		double money = maj.get(showind).getOlxgold();
		String[] col = {"product", "category", "price", "seller", "location"};
		setlayout(2);
		boundsetter(1);
		List<addeets> ade = selectads(cond, num, param1, param2, param3);
		dataad = new DefaultTableModel(ade.size(), 5);
		dataad.setColumnIdentifiers(col);
		adtable = new JTable(dataad);
		adpane = new JScrollPane(adtable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel pane = new JPanel();
		JComboBox locat = new JComboBox();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JButton buysel = new JButton("View Seller");
		JComboBox sell, cat;
		JButton buy, back;
		buy = new JButton("Buy(Money left:" + money + ")");
		back = new JButton("Back");
		sell = new JComboBox();
		cat = new JComboBox();
		
		adtable.setColumnSelectionAllowed(false);
		adtable.setRowSelectionAllowed(true);
		if (ade.size() != 0) {
			adtable.setRowSelectionInterval(0, 0);
		}
		cat.addItem("all categories");
		sell.addItem("all sellers");
		
		cat.addItem("Cellphones");
		cat.addItem("Game consoles");
		cat.addItem("Furniture");
		cat.addItem("Pets");
		cat.addItem("Tools");
		cat.addItem("Accessories");
		cat.addItem("Others");
		locat.addItem("all locations");
		locat.addItem("Manila"); 
		locat.addItem("Pasig");
		locat.addItem("Cavite");
		locat.addItem("Quezon");
		locat.addItem("Las Pinas");
		cat.setSelectedIndex(comb1ind);
		for (int i = 0; i < maj.size(); i++) {
			if (showind != i)
				sell.addItem(maj.get(i).getName());
		}
		sell.setSelectedIndex(comb2ind);
		locat.setSelectedItem(comb3ind);
		System.out.println("ASDFGGHJ " + comb2ind);
		
		for (int i = 0; i < ade.size(); i++) {
			for (int j = 0; j < 5; j++) {
				if (j == 0) {
					dataad.setValueAt(ade.get(i).getProduct(), i, j);
				}
				else if (j == 1) {
					
					dataad.setValueAt(ade.get(i).getCategory(), i, j);
				}
				else if (j == 2) {
					
					dataad.setValueAt(ade.get(i).getPrice(), i, j);
				}
				else if (j == 3){
					
					dataad.setValueAt(ade.get(i).getSeller(), i, j);
				}
				else{
					
					dataad.setValueAt(ade.get(i).getLocation(), i, j);
				}
			}
		}
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setlayout(1);
				frame.validate();
				mainmenu();
			}
			
		});
		buysel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int num = adtable.getSelectedRow();
				int ind = 0;
				String seller = adtable.getModel().getValueAt(num, 3).toString().trim();
				System.out.println(seller);
				for (int i = 0; i < maj.size(); i++) {
					if (maj.get(i).getName().trim().equals(seller)) {
						ind = i;
						break;
					}
					
				}
				setlayout(1);
				viewprofile(ind, 2);
				frame.validate();
				
			}
			
		});
		cat.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String filt1 = cat.getSelectedItem().toString().trim();
				String filt2 = sell.getSelectedItem().toString().trim();
				String filt3 = locat.getSelectedItem().toString().trim();
				int selectfiltin1 = cat.getSelectedIndex();
				int selectfiltin2 = sell.getSelectedIndex();
				int selectfiltin3 = locat.getSelectedIndex();
				int indfilt = 0;
				if (filt1.equals("all categories") && filt2.equals("all sellers") && filt3.equals("all locations")) {
					indfilt = 1;
					
				}
				else if (!(filt1.equals("all categories")) && filt2.equals("all sellers") && filt3.equals("all locations")) {
					indfilt = 2;
				}
				else if (filt1.equals("all categories") && !(filt2.equals("all sellers")) && filt3.equals("all locations")) {
					indfilt = 3;
				}
				else if (!(filt1.equals("all categories")) && !(filt2.equals("all sellers")) && filt3.equals("all locations")) {
					indfilt = 4;
				}
				else if (filt1.equals("all categories") && filt2.equals("all sellers") && !(filt3.equals("all locations"))) {
					indfilt = 5;
					
				}
				else if (!(filt1.equals("all categories")) && filt2.equals("all sellers") && !(filt3.equals("all locations"))) {
					indfilt = 6;
				}
				else if (filt1.equals("all categories") && !(filt2.equals("all sellers")) && !(filt3.equals("all locations"))) {
					indfilt = 7;
				}
				
				else {
					indfilt = 8;
				}
				
				otheradtableview(" where username <> ?", indfilt , filt1, filt2, filt3, selectfiltin1, selectfiltin2, selectfiltin3);
				
			}
			
		});
		locat.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String filt1 = cat.getSelectedItem().toString().trim();
				String filt2 = sell.getSelectedItem().toString().trim();
				String filt3 = locat.getSelectedItem().toString().trim();
				int selectfiltin1 = cat.getSelectedIndex();
				int selectfiltin2 = sell.getSelectedIndex();
				int selectfiltin3 = locat.getSelectedIndex();
				int indfilt = 0;
				if (filt1.equals("all categories") && filt2.equals("all sellers") && filt3.equals("all locations")) {
					indfilt = 1;
					
				}
				else if (!(filt1.equals("all categories")) && filt2.equals("all sellers") && filt3.equals("all locations")) {
					indfilt = 2;
				}
				else if (filt1.equals("all categories") && !(filt2.equals("all sellers")) && filt3.equals("all locations")) {
					indfilt = 3;
				}
				else if (!(filt1.equals("all categories")) && !(filt2.equals("all sellers")) && filt3.equals("all locations")) {
					indfilt = 4;
				}
				else if (filt1.equals("all categories") && filt2.equals("all sellers") && !(filt3.equals("all locations"))) {
					indfilt = 5;
					
				}
				else if (!(filt1.equals("all categories")) && filt2.equals("all sellers") && !(filt3.equals("all locations"))) {
					indfilt = 6;
				}
				else if (filt1.equals("all categories") && !(filt2.equals("all sellers")) && !(filt3.equals("all locations"))) {
					indfilt = 7;
				}
				
				else {
					indfilt = 8;
				}
				
				otheradtableview(" where username <> ?", indfilt , filt1, filt2, filt3, selectfiltin1, selectfiltin2, selectfiltin3);
			}
			
		});
		sell.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String filt1 = cat.getSelectedItem().toString().trim();
				String filt2 = sell.getSelectedItem().toString().trim();
				String filt3 = locat.getSelectedItem().toString().trim();
				int selectfiltin1 = cat.getSelectedIndex();
				int selectfiltin2 = sell.getSelectedIndex();
				int selectfiltin3 = locat.getSelectedIndex();
				int indfilt = 0;
				if (filt1.equals("all categories") && filt2.equals("all sellers") && filt3.equals("all locations")) {
					indfilt = 1;
					
				}
				else if (!(filt1.equals("all categories")) && filt2.equals("all sellers") && filt3.equals("all locations")) {
					indfilt = 2;
				}
				else if (filt1.equals("all categories") && !(filt2.equals("all sellers")) && filt3.equals("all locations")) {
					indfilt = 3;
				}
				else if (!(filt1.equals("all categories")) && !(filt2.equals("all sellers")) && filt3.equals("all locations")) {
					indfilt = 4;
				}
				else if (filt1.equals("all categories") && filt2.equals("all sellers") && !(filt3.equals("all locations"))) {
					indfilt = 5;
					
				}
				else if (!(filt1.equals("all categories")) && filt2.equals("all sellers") && !(filt3.equals("all locations"))) {
					indfilt = 6;
				}
				else if (filt1.equals("all categories") && !(filt2.equals("all sellers")) && !(filt3.equals("all locations"))) {
					indfilt = 7;
				}
				
				else {
					indfilt = 8;
				}
				
				otheradtableview(" where username <> ?", indfilt , filt1, filt2, filt3, selectfiltin1, selectfiltin2, selectfiltin3);
				
			}
			
		});
		
		buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = adtable.getSelectedRow();
				
				double price = ade.get(row).getPrice();
				if (price <= money) {
				String sellerid = ade.get(row).getSellerid();
				String productid = ade.get(row).getAdid();
				String categ = dataad.getValueAt(row, 2).toString().trim();
				String buyerid = maj.get(showind).getId();
				int found = 0;
				for (int i = 0; i < buyreq.size(); i++) {
					String idbuy = buyreq.get(i).getBuyerid();
					String idsell = buyreq.get(i).getSellerid();
					String idprod = buyreq.get(i).getAdid();
					System.out.println("idbuy" + idbuy);
					System.out.println("idsell" + idsell);
					System.out.println("idprod" + idsell);
					System.out.println(buyerid + " buyerid");
					System.out.println(sellerid + " sellerid");
					System.out.println(productid + " productid");
					if (idprod.equals(productid) && idsell.equals(sellerid) && idbuy.equals(buyerid)) {
						found++;
						break;
					}
				}
				if (found == 0) {
					insertrequest(sellerid, buyerid, productid);
				}
				else {
					errormsg("You already have that request");
				}
				}
				else {
					errormsg("Not enough money");
				}
			}
			
		});
		pane.add(cat, BorderLayout.WEST);
		
		pane.add(sell, BorderLayout.EAST);
		pan1.add(buy, BorderLayout.WEST);
		pan1.add(back, BorderLayout.EAST);
		pan2.add(buysel, BorderLayout.SOUTH);
		pan2.add(locat, BorderLayout.NORTH);
		
		frame.add(adpane, BorderLayout.CENTER);
		frame.add(pane, BorderLayout.NORTH);
		frame.add(pan1, BorderLayout.SOUTH);
		frame.add(pan2, BorderLayout.EAST);
		
	
		repaint();
		
		frame.validate();
		frame.setVisible(true);
		
	}
	public void storechat(String msg, String sender, String receiv) {
		//stores data in the chathistory table
		String query = "INSERT INTO chathistory (chat1userid, chat2userid, message) VALUES (?, ?, ?)";
		Connection conn = db.getConnection();
		int sendind = -1, recind = -1;
		
		for (int i = 0; i < maj.size(); i++) {
			String name = maj.get(i).getName();
			if (name.equals(sender)) {
				sendind = i;
			}
			else if (name.equals(receiv)) {
				recind = i;
			}
		}
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			String receivid = maj.get(recind).getId();
			String senderind = maj.get(sendind).getId();
			ps.setString(1, senderind);
			ps.setString(2, receivid);
			ps.setString(3, msg);
			
			System.out.println("Receiv " + receivid);
			System.out.println("Senderid " + sendind);
			int added = ps.executeUpdate();
			chathist ch = new chathist();
			ch.setChathist(msg);
			ch.setSender(senderind);
			ch.setReceiv(receivid);
			chat.add(ch);
		}
		catch (Exception e) {
			System.out.println("TU");
		}
	}
	public void insertrequest(String sellerid, String buyerid, String productid) {
		//inserts values into table requests
		String query = "INSERT INTO requests (advid, buyerid, sellerid, accepted) VALUES (?, ?, ?, ?)";
		Connection conn = db.getConnection();
		//needs an if statement if request already exists
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, productid);
			ps.setString(2, buyerid);
			ps.setString(3, sellerid);
			ps.setString(4, "pending");
			int added = ps.executeUpdate();
			requestinfo res = new requestinfo();
			int num1 = gettinguserind(buyerid); 
			int num = gettinguserind(sellerid);
			String seller = maj.get(num).getName();
			String buyer = maj.get(num1).getName();
			String product = retprod(productid);
			res.setSeller(seller);
			res.setBuyer(buyer);
			res.setProduct(product);
			res.setRequeststat("pending");
			buyreq.add(res);
			
		}
		catch(Exception e) {
			System.out.println("ASDFGHJ");
		}
		
	}
	public String retprod(String prodid) {
		for (int i = 0; i < asda.size(); i++) {
			String id = asda.get(i).getAdid();
			
			if (id.equals("prodid")) {
				return asda.get(i).getProduct();
			}
		}
		return null;
	}
	public String firstitem() {
		for (int i = 0; i < reqinf.size(); i++) {
			if (reqinf.get(i).getRequeststat().trim().equals("pending")) {
				return reqinf.get(i).getProduct();
			}
		}
		return null;
	}
	public void additemcomb() {
		String listit = "";
		for (int i = 0; i < reqinf.size(); i++) {
			if (!(listit.contains(reqinf.get(i).getProduct())) && reqinf.get(i).getRequeststat().equals("pending")) {
				items.addItem(reqinf.get(i).getProduct());
				listit = listit + reqinf.get(i).getProduct() + ", ";
			}
		}
	}
	
	public void addrequestcust(String item) {
		//adds the requesting customers
		for (int i = 0; i < reqinf.size(); i++) {
			if (reqinf.get(i).getProduct().equals(item)) {
				request.addItem(reqinf.get(i).getBuyer());
			}
		}
	}
	public int gettinguserind(String id) {
		for (int i = 0; i < maj.size(); i++) {
			String userid = maj.get(i).getId();
			
			if (userid.equals(id)) {
				return i;
			}
		}
		return -1;
	}
	public String gettingid(String name) {
		//for getting the id of a username
		for (int i = 0; i < maj.size(); i++) {
			String name2 = maj.get(i).getName();
			if (name2.equals(name)) {
				return maj.get(i).getId();
			}
		}
		return null;
	}
	public void chatbox(String receiv, int showind, int func) {
		removecomp();
		ImageIcon img = new ImageIcon("C:\\Users\\shine\\Documents\\java workspace\\sqlpractice\\src\\body\\Chat.jpg");
		
		System.out.println("List " + chat.size());
		boundsetter(1);
		setlayout(2);
		//frame.setContentPane(new JLabel(img));
		String receivid = gettingid(receiv);
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		int trueshowind = this.showind;
		chathist = new JTextArea();
		JTextField chatfield = new JTextField("                                                      ");
		items = new JComboBox();
		request = new JComboBox();
		
		JLabel lab = new JLabel("User: " + receiv);
		String item = firstitem();
		JPanel pane3 = new JPanel();
		JComboBox starthour = new JComboBox();
		JComboBox startmin = new JComboBox();
		JComboBox endhour = new JComboBox();
		JComboBox endmin = new JComboBox();
		
		JLabel lan = new JLabel("Item: " + item);
		JButton conf = new JButton("Confirm");
		JButton back = new JButton("back");
		JButton send = new JButton("send");
		JPanel pan4 = new JPanel();
		starthour.addItem("(Choose start hour");
		startmin.addItem("(Choose start minute)");
		endhour.addItem("(Choose end hour)");
		endmin.addItem("(Choose end minute)");
		for (int i = 0; i < 24; i++) {
			String hun = i + "";
			if (i < 10) {
				hun = "0" + hun;
			}
			starthour.addItem(hun);
			endhour.addItem(hun);
		}
		for (int i = 0; i < 60; i++) {
			String hun = i + "";
			if (i < 10) {
				hun = "0" + hun;
			}
			startmin.addItem(hun);
			endmin.addItem(hun);
		}
		endmin.setSelectedItem(0);
		endhour.setSelectedItem(0);
		request.addItem("(Select customer)");
		
		items.addItem("(Select Item)");
		additemcomb();
		items.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String item = items.getSelectedItem().toString();
				request.removeAllItems();
				request.addItem("(Select customer)");
				addrequestcust(item);
				repaint();
			}
			
		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (showind != trueshowind) {
					viewprofile(showind, func);
				}
				else {
					mainmenu();
				}
			}
			
		});
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String msg = maj.get(trueshowind).getName() + ": " + chatfield.getText().trim();
				chathist.append(msg + "\n");
				chatfield.setText("                                                      ");
				String user = maj.get(trueshowind).getName();
				storechat(msg, user, receiv);
			}
			
		});
		conf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String starth = starthour.getSelectedItem().toString().trim();
				String startm = startmin.getSelectedItem().toString().trim();
				String endh = endhour.getSelectedItem().toString().trim();
				String endm = endmin.getSelectedItem().toString().trim();
				String buyern = request.getSelectedItem().toString();
				if (!(starth.contains("start")) && !(startm.contains("start")) && !(endh.contains("end") && !(endm.contains("end")))) {
					String user = maj.get(trueshowind).getName();
					String product = items.getSelectedItem().toString().trim();
					double price = giveprice(product);
					double money = getmoney(request.getSelectedItem().toString());
					double usermon = getmoney(maj.get(trueshowind).getName());
					if (money >= price) {
						boolean check = confirmtime(starth, startm, endh, endm);
						if (check) {
							String idrec = gettingid(buyern);
							String idsend = gettingid(user);
							String prodid = getadid2(product);
							System.out.println(product);
							System.out.println(product);
							System.out.println(prodid + " heh");
							String location = maj.get(trueshowind).getLocation();
							int prodind = getadindex(prodid);
							System.out.println(prodind + " asd");
							String category = asda.get(prodind).getCategory();
							String time = starth + ":" + startm + " - " + endh + ":" + endm;
							System.out.println("EYYY");
							double deduct = money - price;
							usermon = usermon + price;
							updateolxg(buyern, deduct);
							updateolxg(maj.get(trueshowind).getName(), usermon);
							updaterequests(idrec, idsend, prodid);
							negupdaterequests(idrec, idsend, prodid);
							String dayshow = "";
							if (day < 10) {
								dayshow = dayshow + "0";
							}
							dayshow = dayshow + day;
							String date = "" + month + "-" + dayshow + "-" + year;
							String col = "M" + month;
							changemonthly(col, category);
							updateadpur(date, prodid);
							updateuserbuy(product, idrec);
							updateusersold(product, idsend);
							updaterequests(idrec, idsend, prodid);
							negupdaterequests(idrec, idsend, prodid);
							String transactid = idgive("TROLX", transid);
							inserttransact(transactid, idrec, product, price, date, time, location);
							errormsg("Successfully sold to " + buyern);
							
						}
						else {
							errormsg("Invalid start and end time");
						}
				}
				else {
					errormsg("User doesn't have enough money");
				}
				}
				else {
					errormsg("Please specify the start and end time for meeting");
				}
			}
			
		});
		pan4.add(starthour, BorderLayout.WEST);
		pan4.add(startmin, BorderLayout.EAST);
		showchathist(receivid);
		pane3.add(endhour, BorderLayout.NORTH);
		pane3.add(endmin, BorderLayout.SOUTH);
		pan2.add(request, BorderLayout.EAST);
		pan3.add(lab, BorderLayout.WEST);
		pan3.add(back, BorderLayout.CENTER);
		pan3.add(pan4, BorderLayout.EAST);
		pan1.add(chathist, BorderLayout.CENTER);
		
		pan1.add(chatfield, BorderLayout.WEST);
		pan1.add(send, BorderLayout.CENTER);
		pan1.add(pane3, BorderLayout.EAST);
		pan2.add(items, BorderLayout.CENTER);
		
		pan2.add(conf, BorderLayout.WEST);
		
		frame.add(pan3, BorderLayout.NORTH);
		frame.add(chathist, BorderLayout.CENTER);
		
		frame.add(pan2, BorderLayout.EAST);
		frame.add(pan1, BorderLayout.SOUTH);
		
		frame.validate();
		repaint();
	}
	public double getmoney(String user) {
		
		//returns the olxgold of the user
		for (int i = 0; i < maj.size(); i++) {
			String name = maj.get(i).getName();
			if (name.equals(user)) {
				return maj.get(i).getOlxgold();
			}
		}
		return -1;
	}
	public double giveprice(String item) {
		//returns the price of the product
		for (int i = 0; i < asda.size(); i++) {
			String prod = asda.get(i).getProduct();
			if (item.equals(prod)) {
				return asda.get(i).getPrice();
			}
		}
		return -1;
	}
	public void requests() {
		//shows each request of users to the product owned by a user
		removecomp();
		setlayout(2);
		JTextArea reqhist = new JTextArea();
		JButton back = new JButton("Back");
		JButton logout = new JButton("Logout");
		JPanel pan1 = new JPanel();
		
		int count = 0;
		for (int i = 0; i < buyreq.size(); i++) {
			String pend = buyreq.get(i).getRequeststat();
			String buyer = buyreq.get(i).getBuyer();
			String prod = buyreq.get(i).getProduct();
			String seller = buyreq.get(i).getSeller();
			if (pend.equals("no")) {
				reqhist.append("Seller " + seller + " has declined your request for the " + prod + "\n");
				count++;
			}
			else if (pend.equals("yes")) {
				reqhist.append("Seller " + seller + " has accepted your request for the " + prod + "\n");
				count++;
			}
		}
		for (int i = 0; i < reqinf.size(); i++) {
			String pend = reqinf.get(i).getRequeststat();
			String buyer = reqinf.get(i).getBuyer();
			String prod = reqinf.get(i).getProduct();
		
			
			if (pend.equals("pending")) {
				
				reqhist.append("User " + buyer + " requests to buy the " + prod + "\n");
				count++;
			}
			
		}
		if (count == 0) {
			reqhist.append("no new notifications");
		}
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainmenu();
			}
			
		});
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
			
		});
		pan1.add(back, BorderLayout.WEST);
		pan1.add(logout, BorderLayout.EAST);
		frame.add(reqhist, BorderLayout.CENTER);
		frame.add(pan1, BorderLayout.SOUTH);
		frame.validate();
		repaint();
	}
	public List<chathist> retrievechat() {
		String id = maj.get(showind).getId();
		String query = "SELECT * FROM chathistory WHERE chat1userid = ? OR chat2userid = ?";
		Connection conn = db.getConnection();
		
		List<chathist> chist = new ArrayList<chathist>(1000);
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				chathist ch = new chathist();
				ch.setSender(rs.getString(1));
				ch.setReceiv(rs.getString(2));
				ch.setChathist(rs.getString(3));
				chist.add(ch);
			}
		}
		catch(Exception e) {
			System.out.println("Chat error");
		}
		return chist;
	}
	public void showchathist(String receivid) {
		
		//shows the previous chathist
		String senderid = maj.get(showind).getId();
		
		for (int i = 0; i < chat.size(); i++) {
			int approve = 0;
			String id1 = chat.get(i).getSender();
			String id2 = chat.get(i).getReceiv(); 
			if (id1.equals(senderid)) {
				if (id2.equals(receivid)) {			//if chat1userid from the chathistory table is the same as the id of the current logged in user
					String msg = chat.get(i).getChathist();
					chathist.append(msg + "\n");
				}
			}
			else if (id1.equals(receivid)) {		//if chat1userid is equivalent to the chat receiver's userid
				if (id2.equals(senderid)) {
					String msg = chat.get(i).getChathist();
					chathist.append(msg + "\n");
				}
			}
		}
	}
	public List<requestinfo> getnotif() {
		String query =  "SELECT * FROM requests WHERE buyerid = ?";
		
		Connection conn = db.getConnection();
		List<requestinfo> req = new ArrayList<requestinfo>(1000);
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
		
			ps.setString(1, maj.get(showind).getId());
			
		
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String buyerid, sellerid, adid, pend, buyer = "", seller = "", product = "";
				requestinfo rei = new requestinfo();
				
				buyerid = rs.getString(2);
				sellerid = rs.getString(3);
				adid = rs.getString(1);
				pend = rs.getString(4);
				int stopbuy = 0, stopsel = 0;
				for (int i = 0; i < maj.size(); i++) {
					if (maj.get(i).getId().equals(buyerid)) {
						if (stopbuy == 0) {
							stopbuy = 1;
							buyer = maj.get(i).getName();
						}
						
					}
					else if (maj.get(i).getId().equals(sellerid)) {
						if (stopsel == 0) {
							stopsel = 1;
							seller = maj.get(i).getName();
						}
					}
				}
				for (int i = 0; i < asda.size(); i++) {
					if (asda.get(i).getAdid().equals(adid)) {
						product = asda.get(i).getProduct();
					}
				}
				rei.setBuyerid(buyerid);
				rei.setSellerid(sellerid);
				rei.setAdid(adid);
				rei.setBuyer(buyer);
				rei.setSeller(seller);
				rei.setProduct(product);
				rei.setRequeststat(pend);
				req.add(rei);
			}
		}
		catch(Exception e) {
			System.out.println("ZOOM");
		}
		return req;
	}
	public List<requestinfo> getrequests() {
		String query =  "SELECT * FROM requests WHERE sellerid = ?";
		
		Connection conn = db.getConnection();
		List<requestinfo> req = new ArrayList<requestinfo>(1000);
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
		
			ps.setString(1, maj.get(showind).getId());
			
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String buyerid, sellerid, adid, pend, buyer = "", seller = "", product = "";
				requestinfo rei = new requestinfo();
				
				buyerid = rs.getString(2);
				sellerid = rs.getString(3);
				adid = rs.getString(1);
				pend = rs.getString(4);
				int stopbuy = 0, stopsel = 0;
				for (int i = 0; i < maj.size(); i++) {
					if (maj.get(i).getId().equals(buyerid)) {
						if (stopbuy == 0) {
							stopbuy = 1;
							buyer = maj.get(i).getName();
						}
						
					}
					else if (maj.get(i).getId().equals(sellerid)) {
						if (stopsel == 0) {
							stopsel = 1;
							seller = maj.get(i).getName();
						}
					}
				}
				for (int i = 0; i < asda.size(); i++) {
					if (asda.get(i).getAdid().equals(adid)) {
						product = asda.get(i).getProduct();
					}
				}
				rei.setBuyerid(buyerid);
				rei.setSellerid(sellerid);
				rei.setAdid(adid);
				rei.setBuyer(buyer);
				rei.setSeller(seller);
				rei.setProduct(product);
				rei.setRequeststat(pend);
				req.add(rei);
			}
		}
		catch(Exception e) {
			System.out.println("ZOOM");
		}
		return req;
	}
	public void olxpurchase() {
		removecomp();
		ImageIcon img = new ImageIcon("C:\\Users\\shine\\Documents\\java workspace\\sqlpractice\\src\\body\\Buying of Gold.jpg");
		this.setlayout(2);
		this.setlayout(1);
		frame.setContentPane(new JLabel(img));
		
		
		String categ;
		
		int noofads = noofads();
		JTextField amount = new JTextField();
		JLabel label = new JLabel("Set amount");
		JButton buy = new JButton("");
		buyback = new JButton("");
		buylog = new JButton("");
		
		
		label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 140, 120, 30);
		amount.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 120, 120, 30);
		buy.setBounds(frame.getWidth() / 2 - 275, frame.getHeight() / 2 - 70, 120, 40);
		buyback.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 + 120, 120, 40);
		buylog.setBounds(frame.getWidth() / 2 + 140, frame.getHeight() / 2 + 120, 120, 40);
		
		buylog.setOpaque(false);
		buylog.setContentAreaFilled(false);
		buylog.setBorderPainted(false);
		buyback.setOpaque(false);
		buyback.setContentAreaFilled(false);
		buyback.setBorderPainted(false);
		buy.setOpaque(false);
		buy.setContentAreaFilled(false);
		buy.setBorderPainted(false);
		buy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					double amount2 = Double.parseDouble(amount.getText().trim());
					maj.get(showind).setOlxgold(maj.get(showind).getOlxgold() + amount2);
					addolxg(amount2);
				}
				catch (Exception er) {
					errormsg("Invalid amount");
				}
			}
			
		});
		buylog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
			
		});
		buyback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainmenu();
			}
			
		});
		
		
		
		frame.add(buyback);
		frame.add(buylog);
		
		frame.add(amount);
		frame.add(buy);
		frame.validate();
		repaint();
	}
	public void addolxg(double valu) {
		//changes the value of the olx gold of the user
		String query = "UPDATE olxuser SET olxgold = ? WHERE username = ?";
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, (float)valu);
			ps.setString(2, nameup);
			
			int added = ps.executeUpdate();
		}
		catch (Exception e) {
			
		}
	}
	public void addcondition(String adid, String cond) {
		String query = "UPDATE advertisments SET cond = ? WHERE idadvertisments = ?";
		Connection conn = db.getConnection();
		
		try {
			int index = adindex();
			
			String adidind = asda.get(index).getAdid();
			System.out.println(adidind);
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cond);
			ps.setString(2, adid);
			int added = ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("choto mate");
		}
	}
	public void updateadtable(String product, double price, String category, int amount, String cond) {
		
		if (noofads() == 0) {
			System.out.println("No more slots left");
		}
		else {
		String query = "UPDATE advertisments SET product = ?, price = ?, category = ?, amount = ?, sold = ? WHERE idadvertisments = ?";
		Connection conn = db.getConnection();
		
		try {
			int index = adindex();
			
			String adidind = asda.get(index).getAdid();
	
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, product);
			ps.setFloat(2, (float)price);//http://org.eclipse.ui.intro/showPage?id=tutorials&standby=false
			ps.setString(3, category);
			ps.setInt(4, amount);
			ps.setString(5, "no");

			ps.setString(6, adidind);
			
			int added = ps.executeUpdate();
			asda.get(index).setCategory(category);
			asda.get(index).setProduct(product);
			asda.get(index).setPrice(price);
			addcondition(adidind, cond);
		}
		catch(Exception e) {
			System.out.println("CHEK");
		}
		}
	}
	public String getadid2(String prodname) {
		for (int i = 0; i < asda.size(); i++) {
			String name = asda.get(i).getProduct();

			if (name == null) {
				
			}
			else if (name.equals(prodname)) {
				return asda.get(i).getAdid();
			}
		}
		return null;
	}
	public String getadid(String prodname) {
		for (int i = 0; i < asda.size(); i++) {
			String name = asda.get(i).getProduct();
			System.out.println("View name " + name);
			if (name == null) {
				return asda.get(i).getAdid();
			}
		}
		return null;
	}
	public int getadindex(String prodid) {
		for (int i = 0; i < asda.size(); i++) {
			String adid = asda.get(i).getAdid();
			if (adid.equals(prodid)) {
				return i;
			}
		}
		return -1;
	}
	public int adindex() {
		int adidind;
		for (int i = 0; i < asda.size(); i++) {
			if (asda.get(i).getUserid().equals(maj.get(showind).getId()) && asda.get(i).getProduct() == null) {
				
				adidind = i;
				return adidind;
			}
			
			
		}
		return -1;
	}
	public void boundsetter(int bound) {
		if (bound == 1) {
			frame.setBounds(300, 200, 600, 400);
		}
		else {
			frame.setBounds(300, 200, 1000, 700);
		}
	}
	public void viewprofile(int showind, int func) {
		
		//view profile interface
		String boug = "";
		String sold = "";
		this.setlayout(1);
		frame.setContentPane(new JLabel());
		if (maj.get(showind).getItembought() == null) {
			boug = "none";
		}
		else {
			boug = maj.get(showind).getItembought();
		}
		if (maj.get(showind).getItemsold() == null) {
			sold = "none";
		}
		else {
			sold = maj.get(showind).getItemsold();
		}
		noofads = noofads();
		String namer = maj.get(showind).getName();
		int trushowind = this.showind;
		removecomp();
		setlayout(1);
		boundsetter(2);
		JLabel age = new JLabel("Age: " + maj.get(showind).getAge());
		JLabel birt = new JLabel("Birthday: " + maj.get(showind).getBirthday());
		name = new JLabel();
		profback = new JButton("Back");
		JButton chat = new JButton("Chat");
		JButton report = new JButton("Report");
		JLabel itemsbought = new JLabel("Items bought: " + boug);
		JLabel itemssold = new JLabel("Items sold: " + sold);
		JLabel gen = new JLabel("Gender: " + maj.get(showind).getGender());
		JLabel em = new JLabel("Email: " + maj.get(showind).getEmail());
		profad = new JLabel("No. of free ad slots: " + noofads);
		
		
		proflog = new JButton("Log out");
		locser = new JComboBox();
		name.setText("Name: " + namer);
		locat = new JLabel("Location: " + maj.get(showind).getLocation());
		olxg = new JLabel("OLXGold: " + maj.get(showind).getOlxgold());
		mobile = new JLabel("Mobile: " + maj.get(showind).getNumber());
		
		birt.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 200, 120, 40);
		age.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 150, 120, 40);
		gen.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 100, 120, 40);
		em.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 50, 300, 40);
		itemssold.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 + 50, 300, 40);
		itemsbought.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2, 300, 40);
		locser.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 250, 150, 40);
		name.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 350, 120, 40);
		mobile.setBounds(frame.getWidth() /2 - 300, frame.getHeight() / 2 - 300, 200, 40);
		locat.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 250, 200, 40);
		olxg.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 + 100, 200, 40);
		profback.setBounds(frame.getWidth() / 2 + 100, frame.getHeight() / 2 + 50, 100, 40);
		proflog.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2 + 50, 150, 40);
		profad.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 + 150, 200, 40);
		chat.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 - 250, 100, 40);
		report.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 - 150, 120, 40);
		report.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String use = maj.get(showind).getName();
				String userid = maj.get(showind).getId();
				reportdialog("What reasons do you have to report user: " + use + "?", userid);
				
			}
			
		});
		locser.addItem("(choose location)");
		locser.addItem("Manila"); 
		locser.addItem("Pasig");
		locser.addItem("Cavite");
		locser.addItem("Quezon");
		locser.addItem("Las Pinas");
		locser.setSelectedIndex(0);
		locser.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				gam++;
				if (gam == 2) {
					if (!(locser.getSelectedItem().toString().equals("(choose location)"))) {
					gam = 0;
					maj.get(showind).setLocation(locser.getSelectedItem().toString());
					updatelocat();
					locat.setText("Location: " + locser.getSelectedItem().toString());
					
					repaint();
					}
				}
			}
			
		});
		proflog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
			
		});
		profback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (func == 1) {	//if showind == trushowind
					mainmenu();
				}
				else {
					otheradtableview(" where username <> ?", 1 , "all categories", "all sellers", "all locations", 0, 0, 0);
				}
			}
			
		});
		chat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				chatbox(namer, showind, func);
			}
			
		});
		frame.add(itemsbought);
		frame.add(proflog);
		frame.add(profad);
		frame.add(profback);
		frame.add(name);
		frame.add(locat);
		frame.add(mobile);
		frame.add(olxg);
		frame.add(locser);
		frame.add(age);
		frame.add(birt);
		frame.add(gen);
		frame.add(em);
		frame.add(itemssold);
		if (showind != this.showind) {
			frame.remove(locser);
			frame.add(report);
			frame.add(chat);
			frame.remove(olxg);
			frame.remove(profad);
			frame.remove(itemssold);
			frame.remove(itemsbought);
		}
		
		frame.validate();
		repaint();
	}
	public void repaint() {
		//changing of the view of the frame to place all the new components
		frame.repaint();
	}
	public int agecompute(int month, int day, int year) {
		int curyear = this.year;
		int less = 0;
		curyear = curyear - year;
		if (month > this.month) {
			less = 1;
		}
		else {
			if (this.day < day) {
				less = 1;
			}
		}
		if (less == 1) {
			curyear = curyear - 1;
		}
		
		return curyear;
	}
	public void adddays(int month, int year) {
		bday.removeAllItems();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		int num = cal.getActualMaximum(cal.DATE);
		for (int i = 1; i <= num; i++) {
			bday.addItem(i);
		}
		repaint();
	}
	public void register() {
		//register ui
		removecomp();
		this.setlayout(1);
		frame.setContentPane(new JLabel(new ImageIcon("C:\\Users\\shine\\Documents\\java workspace\\sqlpractice\\src\\body\\Register Page.jpg")));
		String [] genders = {"M", "F"};
		JLabel gend = new JLabel("Gender:");
		JComboBox genselect = new JComboBox(genders);
		bday = new JComboBox();
		byear = new JComboBox();
		bmonth = new JComboBox();
		text2 = new JTextArea();
		back = new JButton("Back");
		pass2 = new JPasswordField();
		passconf = new JPasswordField();
		mob = new JTextField();
		conf = new JButton("Finish");
		lab3 = new JLabel("Create Username");
		lab4 = new JLabel("Create Password");
		lab5 = new JLabel("Confirm Password");
		lab6 = new JLabel("Mobile Number");
		JLabel lab7 = new JLabel("Email");
		JLabel lab8 = new JLabel("Birthday(MM/DD/YY");
		JTextField emtext = new JTextField();
		
		gend.setBounds(frame.getWidth() / 2 + 100, frame.getHeight() / 2 - 100, 120, 30);
		genselect.setBounds(frame.getWidth() / 2 + 150, frame.getHeight() / 2 - 30, 120, 30);
		lab7.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 150, 120, 30);
		emtext.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 80, 120, 30);
		lab8.setBounds(frame.getWidth() / 2 + 100, frame.getHeight() / 2 - 200, 120, 30);
		lab6.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 100, 120, 30);
		lab3.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 200, 120, 30);
		lab4.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 50, 120, 30);
		lab5.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 , 120, 30);
		text2.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 120, 120, 30);
		pass2.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 20, 120, 30);
		passconf.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 60, 120, 30);
		conf.setBounds(frame.getWidth() / 2 - 180, frame.getHeight() / 2 + 110, 150, 50);
		back.setBounds(frame.getWidth() / 2 + 20, frame.getHeight() / 2 + 110, 150, 50);
		mob.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 40, 120, 30);
		bday.setBounds(frame.getWidth() / 2 + 160, frame.getHeight() / 2 - 80, 50, 30);
		bmonth.setBounds(frame.getWidth() / 2 + 100, frame.getHeight() / 2 - 80, 50, 30);
		byear.setBounds(frame.getWidth() / 2 + 220 , frame.getHeight() / 2 - 80, 70, 30);
		
		
		for (int i = 0; i < 31; i++) {
			byear.addItem(1988 + 30 - i);
		}
		for (int i = 0; i < 12; i++) {
			bmonth.addItem(i + 1);
		}
		bmonth.setSelectedItem(0);
		byear.setSelectedItem(0);
		bday.setSelectedItem(0);
		byear.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int month = Integer.parseInt(bmonth.getSelectedItem().toString());
				int year = Integer.parseInt(byear.getSelectedItem().toString());
				
				adddays(month, year);
			}
			
		});
		bmonth.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int month = Integer.parseInt(bmonth.getSelectedItem().toString());
				int year = Integer.parseInt(byear.getSelectedItem().toString());
				
				adddays(month, year);
			}
			
		});
		int year = Integer.parseInt(byear.getSelectedItem().toString());
		int month = Integer.parseInt(bmonth.getSelectedItem().toString()); 
		adddays(month, year);
		genselect.setSelectedItem(0);
		conf.addActionListener(new ActionListener() {
		
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String gam = mob.getText().trim(); //converts the string to an int, if not string has characters that are not numbers, then it will go to the catch statement below
				
					if (pass2.getText().equals(passconf.getText()) && !(pass2.getText().isEmpty())) {
						//matching passwords
					
						if (text2.getText().trim().isEmpty() || emtext.getText().trim().isEmpty() || mob.getText().trim().isEmpty()) {
							//empty username
							
							errormsg("Please enter the missing fields");
						}
						else {
						
							int found = 0;
							for (int i = 0; i < maj.size(); i++) {
								if (maj.get(i).getName().equals(text2.getText().trim())) {
									found = 1;
									break;
								}
								else if (maj.get(i).getNumber().equals(mob.getText().toString().trim())) {
									found = 2;
									break;
								}
								else if (maj.get(i).getEmail().trim().equals(emtext.getText().toString()) ) {
									found = 3;
								}
							}
							
							if (found == 1) {
								errormsg("Username already exists");
							}
							else if (found == 2) {
								errormsg("Phone number already exists");
							}
							else if (found == 3) {
								errormsg("Email already exists");
							}
							else {
								
								int month = Integer.parseInt(bmonth.getSelectedItem().toString());
								int year = Integer.parseInt(byear.getSelectedItem().toString());
								int day = Integer.parseInt(bday.getSelectedItem().toString());
								int age = agecompute(month, day, year);
								
								if (age >= 18 ) {
									String date = day + "";
									String month1 = month + "";
								if (day < 10) {
									date = "0" + date;
								}
								if (month < 10) {
									month1 = "0" + month;
								}
								String email = emtext.getText().trim();
								String birthday = month1 + "-" + date + "-" + year;
								String gender = genselect.getSelectedItem().toString().trim();
								errormsg("Congratulations, you have 5 free slots");
								main man = new main();
								String idh = idgive("OLX", ind);
							
								man.setId(idh);
								man.setName(text2.getText().trim());
								man.setPass(pass2.getText());
								man.setNumber(gam);
								man.setBirthday(birthday);
								man.setOlxgold(0.00);
								man.setAge(age);
								man.setEmail(email);
								man.setGender(gender);
								
								add(man);
								
								for (int i = 0; i < 5; i++)
									addadvslot(1);
									login();
								}
								else {
									errormsg("Underaged ");
								}
							}
						}
					}
					else {
						//mismatching or no password goes here
						errormsg("Missmatching passwords or no inputted password. Please try again");
					}
				}
				catch (Exception er) {
					//invalid phone number
					errormsg("Invalid Phone Number. Please try again");
					
				}
			} // end
			
		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
			
		});
		frame.repaint();
		
		frame.add(genselect);
		frame.add(mob);
		
		frame.add(pass2);
		frame.add(passconf);
		frame.add(text2);
		frame.add(conf);
		frame.add(back);
		frame.add(bday);
		frame.add(byear);
		frame.add(bmonth);
		frame.add(emtext);
		
		frame.validate();
		repaint();
		//regf.setVisible(true);
	}
	
	public String idgive(String idh, int ind) {
		String shov;
		
		if (ind <= 9 && ind >= 1) {
			shov = idh + "00" + ind;
			
			ind++;
			
			
		}
		else if (ind <= 99 && ind >= 10)  {
			shov = idh + "0" + ind;
			ind++;
		}
		else {
			shov = idh + ind;
			ind++;
		}
		
		if (idh.equals("OLX")) {
			this.ind = ind;
		}
		else if (idh.equals("ADOLX")) {
			this.adid = ind;
		}
		else if (idh.equals("TROLX")) {
			this.transid = ind;
		}
		else if (idh.equals("COLX")) {
			this.caseid = ind;
		}
		return shov;
	}
	public void buyads() {
		removecomp();
		this.setlayout(1);
		ImageIcon img = new ImageIcon("C:\\Users\\shine\\Documents\\java workspace\\sqlpractice\\src\\body\\Buy Ad Slots.jpg");
		frame.setContentPane(new JLabel(img));
		JLabel disc1 = new JLabel("10 ad slots 7.5% off 185 Gold");
		JLabel disc2 = new JLabel("20 ad slots 10% off 360 Gold");
		JLabel disc3 = new JLabel("50 ad slots 12.5% off 875 Gold");
		JLabel disc4 = new JLabel("100 ad slots 15% off 2700 Gold");
		JButton bronze = new JButton("");
		JButton silver = new JButton("");
		JButton gold = new JButton("");
		JButton diamond = new JButton("");
		noofads = noofads();
		buyadlab1 = new JLabel("20.00 OLX Gold");
		buyadlab2 = new JLabel("OLX Gold: " + maj.get(showind).getOlxgold() + " No. of free ad slot " + noofads);
		buyad1 = new JButton("");
		buyad2 = new JButton("");
		buyadlab1.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 - 150, 120, 70);
		buyadlab2.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 100, 250, 70);
		buyad1.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 100, 70, 70);
		buyad2.setBounds(frame.getWidth() / 2 - 300 , frame.getHeight() / 2 + 100, 70, 70);
		diamond.setBounds(frame.getWidth() / 2 - 210, frame.getHeight() / 2 - 100, 150, 60);
		gold.setBounds(frame.getWidth() / 2 - 210 , frame.getHeight() / 2 - 30, 150, 60);
		silver.setBounds(frame.getWidth() / 2 - 210, frame.getHeight() / 2 + 40, 150, 60);
		bronze.setBounds(frame.getWidth() / 2 - 210, frame.getHeight() / 2 + 110, 150, 60);
		disc1.setBounds(frame.getWidth() / 2 + 40, frame.getHeight() / 2 + 10, 350, 60);
		disc2.setBounds(frame.getWidth() / 2 + 40, frame.getHeight() / 2 - 60, 350, 60);
		disc3.setBounds(frame.getWidth() / 2 + 40, frame.getHeight() / 2 - 130, 350, 60);
		disc4.setBounds(frame.getWidth() / 2 + 40, frame.getHeight() / 2 - 200, 350, 60);
		
		bronze.setOpaque(false);
		bronze.setContentAreaFilled(false);
		bronze.setBorderPainted(false);
		buyad1.setOpaque(false);
		buyad1.setContentAreaFilled(false);
		buyad1.setBorderPainted(false);
		buyad2.setOpaque(false);
		buyad2.setContentAreaFilled(false);
		buyad2.setBorderPainted(false);
		silver.setOpaque(false);
		silver.setContentAreaFilled(false);
		silver.setBorderPainted(false);
		gold.setOpaque(false);
		gold.setContentAreaFilled(false);
		gold.setBorderPainted(false);
		diamond.setOpaque(false);
		diamond.setContentAreaFilled(false);
		diamond.setBorderPainted(false);
		buyad1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (maj.get(showind).getOlxgold() >= 20.00) {
					maj.get(showind).setOlxgold(maj.get(showind).getOlxgold() - 20.00);
					addolxg(maj.get(showind).getOlxgold());
					addadvslot(2);
					noofads = noofads();
					
					buyadlab2.setText("OLX Gold: " + maj.get(showind).getOlxgold() + " No. of free ad slot " + noofads);
					
					repaint();
				}
				else {
					errormsg("Insufficient gold");
				}
			}
			
		});
		buyad2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainmenu();
			}
			
		});
		bronze.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (maj.get(showind).getOlxgold() >= 185.00) {
					maj.get(showind).setOlxgold((double)maj.get(showind).getOlxgold() - 185.00);
					addolxg(maj.get(showind).getOlxgold());
					for (int i = 0; i < 10; i++) {
						addadvslot(2);
					}
					noofads = noofads();
					
					buyadlab2.setText("OLX Gold: " + maj.get(showind).getOlxgold() + " No. of free ad slot " + noofads);
					
					repaint();
				}
				else {
					errormsg("Insufficient gold");
				}
			}
			
		});
		silver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (maj.get(showind).getOlxgold() >= 360.00) {
					maj.get(showind).setOlxgold((double)maj.get(showind).getOlxgold() - 360.00);
					addolxg(maj.get(showind).getOlxgold());
					for (int i = 0; i < 20; i++) {
						addadvslot(2);
					}
					noofads = noofads();
					
					buyadlab2.setText("OLX Gold: " + maj.get(showind).getOlxgold() + " No. of free ad slot " + noofads);
					
					repaint();
				}
				else {
					errormsg("Insufficient gold");
				}
			}
			
		});
		gold.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (maj.get(showind).getOlxgold() >= 875.00) {
					maj.get(showind).setOlxgold((double)maj.get(showind).getOlxgold() - 875.00);
					addolxg(maj.get(showind).getOlxgold());
					for (int i = 0; i < 50; i++) {
						addadvslot(2);
					}
					noofads = noofads();
					
					buyadlab2.setText("OLX Gold: " + maj.get(showind).getOlxgold() + " No. of free ad slot " + noofads);
					
					repaint();
				}
				else {
					errormsg("Insufficient gold");
				}
			}
			
		});
		diamond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (maj.get(showind).getOlxgold() >= 1700.00) {
					maj.get(showind).setOlxgold((double)maj.get(showind).getOlxgold() - 1700.00);
					addolxg(maj.get(showind).getOlxgold());
					for (int i = 0; i < 100; i++) {
						addadvslot(2);
					}
					noofads = noofads();
					
					buyadlab2.setText("OLX Gold: " + maj.get(showind).getOlxgold() + " No. of free ad slot " + noofads);
					
					repaint();
				}
				else {
					errormsg("Insufficient gold");
				}
			}
			
		});
		
		frame.add(buyad1);
		frame.add(buyad2);
		frame.add(diamond);
		frame.add(bronze);
		frame.add(silver);
		frame.add(gold);
		frame.validate();
		repaint();
	}
	public void getdate() {
		Calendar cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH) + 1;
		year = cal.get(Calendar.YEAR);
	}
	public contactservice(contactdb db) {
		getdate();

		this.db = db;
		
		this.maj = getAll();
		
		this.asda = getexistad();
		setupf();
		
		//this.logview = new loginviewer(frame, maj);
		login();
		
		showfr();
		
	}
	public void errormsg(String msg) {
		//error message
		JFrame errframe = new JFrame();
		errframe.setBounds(200, 300, 700, 400);
		errframe.setLayout(null);
		
		JLabel mesg = new JLabel(msg);
		JButton but = new JButton("OK");
		
		mesg.setBounds(frame.getWidth() / 2, frame.getHeight() /2 - 100, 300, 40);
		but.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 - 25, 75, 40);
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				errframe.setVisible(false);
			}
			
		});
		errframe.add(mesg);
		errframe.add(but);
		errframe.setVisible(true);
	}
	public void updateolxg(String name, double money) {
		String query = "UPDATE olxuser set olxgold = ? WHERE username = ?";
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, (float)money);
			ps.setString(2, name);
			int added = ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("Initiate fail");
		}
	}
	public void updaterequests(String receivid, String senderid, String prodid) {
		//updates the request to a confirmed buyer of the item in requests table
		String query = "UPDATE requests SET accepted = \"yes\" WHERE sellerid = ? AND buyerid = ? AND advid = ?";
		Connection conn = db.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, senderid);
			ps.setString(2, receivid);
			ps.setString(3, prodid);
			
			int added = ps.executeUpdate();
		}
		catch(Exception e) {
			
		}
		
	}
	public void negupdaterequests(String receivid, String senderid, String prodid) {
		//updates the request to a not confirmed buyer of the item in requests table
		System.out.println(receivid + " rec");
		System.out.println(senderid + " send");
		System.out.println(prodid + " prod");
		String query = "UPDATE requests SET accepted = \"no\" WHERE sellerid = ? AND buyerid <> ? AND advid = ?";
		Connection conn = db.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, senderid);
			ps.setString(2, receivid);
			ps.setString(3, prodid);
			
			int added = ps.executeUpdate();
		}
		catch(Exception e) {
			
		}
		
	}
	public void updateadpur(String date, String adid) {
		//updates the advertisments table to update the status and date of the product
		System.out.println(adid + " look");
		String query = "UPDATE advertisments SET sold = \"yes\", datesold = ? WHERE idadvertisments = ?";
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, date);
			ps.setString(2, adid);
			int added = ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("Purchag");
		}
	}
	public void updateuserbuy(String item, String receivid) {
		String query = "UPDATE olxuser SET itemsbought = ?, transactnum = ?  WHERE olxuserid = ?";
		Connection conn = db.getConnection();
		int save = gettinguserind(receivid);
		int transactn = maj.get(save).getTransactnum() + 1;
		try {
			
			String fin = maj.get(save).getItembought();
			PreparedStatement ps = conn.prepareStatement(query);
			
			if (fin == null) {
				fin = item;
			}
			else {
				fin = fin + ", " + item;
			}
			ps.setString(1, fin);
			ps.setInt(2, transactn);
			ps.setString(3, receivid);
			int added = ps.executeUpdate();
			maj.get(save).setItembought(fin);
			maj.get(save).setTransactnum(transactn);
		}
		
		catch (Exception e) {
			System.out.println("hey now");
		}
	}
	public void updateusersold(String item, String userid) {
		String query = "UPDATE olxuser SET itemssold = ?, transactnum = ? WHERE olxuserid = ?";
		Connection conn = db.getConnection();
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
			String fin = maj.get(showind).getItemsold();
			int num = maj.get(showind).getTransactnum() + 1;
			if (fin == null) {
				fin = item;
			}
			else {
				fin = fin + ", " + item;
			}
			ps.setString(1, fin);
			ps.setInt(2, num);
			ps.setString(3, userid);
			maj.get(showind).setItemsold(fin);
			maj.get(showind).setTransactnum(num);
			int added = ps.executeUpdate();
		}
		
		catch (Exception e) {
			System.out.println("watch your step");
		}
	}
	public void setappointment() {
		JFrame frame2 = new JFrame("Set appointment");
		
		frame2.setLayout(null);
		frame2.setVisible(true);
		
	}
	public boolean confirmtime(String starth, String startm, String endh, String endm) {
		int hstart = Integer.parseInt(starth);
		int mstart = Integer.parseInt(startm);
		int hend = Integer.parseInt(endh);
		int mend = Integer.parseInt(endm);
		
		if (hstart > hend) {
			return false;
		}
		else if (hstart < hend) {
			return true;
		}
		else {
			if (mstart >= mend) {
				return false;
			}
			
		}
		return true;
	}
	public List<reportdata> retrievereports() {
		String query = "SELECT * FROM report";
		Connection conn = db.getConnection();
		List<reportdata> ret = new ArrayList<reportdata>(1000);
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				caseid++;
				reportdata red = new reportdata();
				red.setCaseid(rs.getString(1));
				red.setViolator(rs.getString(2));
				red.setEmail(rs.getString(3));
				red.setContact(rs.getString(4));
				red.setViolation(rs.getString(5));
				
				ret.add(red);
			}
		}
		catch(Exception e) {
			System.out.println("retrieve fail");
		}
		return ret;
	}
	public void reportcase(String violator, String violation) {
		String query = "INSERT INTO report (caseid, violatorid, reporterid, contactno, violation) VALUES(?, ?, ?, ?, ?)";
		String reportid = maj.get(showind).getId();
		String contact = maj.get(showind).getNumber();
		String idcase = idgive("COLX", caseid);
		System.out.println(reportid);
		System.out.println(violator);
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, idcase);
			ps.setString(2, violator);
			ps.setString(3, reportid);
			ps.setString(4, contact);
			ps.setString(5, violation);
			int added = ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("report case read");
		}
	}
	public void reportdialog(String dia, String user) {
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(300, 200, 600, 400);
		JLabel label = new JLabel(dia);
		JButton rep = new JButton("Report");
		JTextField inf = new JTextField();
		
		label.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 200, 400, 40);
		rep.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2, 120, 40);
		inf.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 100, 300, 40);
		rep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String violation = inf.getText().toString().trim();
				System.out.println("as " + user);
				reportcase(user, violation);
				label.setText("User: " + user + " has been reported. Thank you for the report");
				frame.remove(rep);
				frame.remove(inf);
				repaint();
			}
			
		});
		frame.add(label);
		frame.add(rep);
		frame.add(inf);
		frame.setVisible(true);
	}
	public List<monthlytransactdata> retrievemonthly() {
		String query = "SELECT * FROM monthlytransact ORDER BY Total DESC";
		Connection conn = db.getConnection();
		List<monthlytransactdata> mon = new ArrayList<monthlytransactdata>(1000);
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				monthlytransactdata mt = new monthlytransactdata();
				mt.setCategory(rs.getString(1));
				mt.setM1(rs.getInt(2));
				mt.setM2(rs.getInt(3));
				mt.setM3(rs.getInt(4));
				mt.setM4(rs.getInt(5));
				mt.setM5(rs.getInt(6));
				mt.setM6(rs.getInt(7));
				mt.setM7(rs.getInt(8));
				mt.setM8(rs.getInt(9));
				mt.setM9(rs.getInt(10));
				mt.setM10(rs.getInt(11));
				mt.setM11(rs.getInt(12));
				mt.setM12(rs.getInt(13));
				mt.setTotal(rs.getInt(14));
				mon.add(mt);
			}
		}
		
		catch (Exception e) {
			System.out.println("montg");
		}
		return mon;
	}
	public String gettingcateg(String adid) {
		for (int i = 0; i < asda.size(); i++) {
			String id = asda.get(i).getAdid();
			
			if (id.equals(adid)) {
				String categ = asda.get(i).getCategory();
				return categ;
			}
		}
		return null;
	}
	public int retmonthlyind(String category) {
		for (int i= 0; i < mntr.size(); i++) {
			
			String categ = mntr.get(i).getCategory().trim();
			System.out.println(categ);
			if (categ.equals(category)) {
				return i;
			}
		}
		return -1;
	}
	public void changemonthly(String mon, String category) {
		String query = "UPDATE monthlytransact SET " + mon + " = ?, Total = ? WHERE Category = ?";
		Connection conn = db.getConnection();
		int catind = retmonthlyind(category);
		int monnum = 1;
		System.out.println("cHECKER " + category);
		if (mon.equals("M1")) {
			monnum = monnum + mntr.get(catind).getM1();
			mntr.get(catind).setM1(monnum);
		}
		else if (mon.equals("M2")) {
			monnum = monnum + mntr.get(catind).getM1();
			mntr.get(catind).setM2(monnum);
		}
		else if (mon.equals("M3")) {
			monnum = monnum + mntr.get(catind).getM3();
			mntr.get(catind).setM3(monnum);
		}
		else if (mon.equals("M4")) {
			monnum = monnum + mntr.get(catind).getM4();
			mntr.get(catind).setM4(monnum);
		}
		else if (mon.equals("M5")) {
			monnum = monnum + mntr.get(catind).getM5();
			mntr.get(catind).setM5(monnum);
		}
		else if (mon.equals("M6")) {
			monnum = monnum + mntr.get(catind).getM6();
			mntr.get(catind).setM6(monnum);
		}
		else if (mon.equals("M7")) {
			monnum = monnum + mntr.get(catind).getM7();
			mntr.get(catind).setM7(monnum);
		}
		else if (mon.equals("M8")) {
			monnum = monnum + mntr.get(catind).getM8();
			mntr.get(catind).setM8(monnum);
		}
		else if (mon.equals("M9")) {
			monnum = monnum + mntr.get(catind).getM9();
			mntr.get(catind).setM9(monnum);
		}
		else if (mon.equals("M10")) {
			monnum = monnum + mntr.get(catind).getM10();
			mntr.get(catind).setM10(monnum);
		}
		else if (mon.equals("M11")) {
			monnum = monnum + mntr.get(catind).getM11();
			mntr.get(catind).setM11(monnum);
		}
		else if (mon.equals("M12")) {
			monnum = monnum + mntr.get(catind).getM12();
			mntr.get(catind).setM12(monnum);
		}
		
		int totalm = mntr.get(catind).getM1() + mntr.get(catind).getM12() + mntr.get(catind).getM2() + mntr.get(catind).getM3() + mntr.get(catind).getM4() + mntr.get(catind).getM5() + mntr.get(catind).getM6() + mntr.get(catind).getM7() + mntr.get(catind).getM8() + mntr.get(catind).getM9() + mntr.get(catind).getM10() + mntr.get(catind).getM11();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, monnum);
			ps.setInt(2, totalm);
			ps.setString(3, category);
			
			System.out.println(query);
			int added = ps.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("tures");
		}
		
	}
}
