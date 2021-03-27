package body;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.*;
import java.util.List;
public class admintransacts {
	private List<monthlytransactdata> mont;
	private List<main> olxuser;
	private List<promodata> prom;
	private contactdb db;
	private contactservice cs;
	private JFrame frame;
	private JTable tab1;
	private JScrollPane scrpane;
	private DefaultTableModel mod;
	private JButton logout;
	private JComboBox choosetab;
	private List<reportdata> rep;
	private List<transactdata> trs;
	public void createframe() {
		frame = new JFrame();
	}
	public admintransacts(contactservice cs, contactdb db) {
		this.db = db;
		this.cs = cs;
		createframe();
		adminlogin();
	}
	public void setlarge() {
		frame.setBounds(300, 200, 1200, 400);
	}
	public void adminlogin() {
		
	    
		frame.setLayout(null);
		frame.setBounds(300, 200, 500, 400);
		ImageIcon img = new ImageIcon("C:\\Users\\shine\\Documents\\java workspace\\sqlpractice\\src\\body\\Admin Login.jpg");
		frame.setContentPane(new JLabel(img));
		JButton log = new JButton("");
		JButton back = new JButton("");
	
		JLabel user = new JLabel("Username:");
		JLabel pass = new JLabel("Password:");
		JTextField usert = new JTextField();
		JPasswordField passt = new JPasswordField();
		user.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 200, 120, 40);
		pass.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 150, 120, 40);
		pass.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 130, 120, 40);
		usert.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() / 2 - 80, 200, 30);
		back.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() / 2 + 110, 120, 40);
		log.setBounds(frame.getWidth() / 2 - 80, frame.getHeight() / 2 + 60, 200, 40);
		passt.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() / 2 - 30, 150, 30);
		
		
		log.setOpaque(false);
		log.setContentAreaFilled(false);
		log.setBorderPainted(false);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cs.login();
				cs.showfr();
			}
			
		});
		log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user = usert.getText().trim();
				String passw = passt.getText();
				boolean go = confirm(user, passw);
				if (go) {
					olxuser = cs.getAll();
					rep = cs.retrievereports();
					trs = cs.gettransactions();
					retrievepromos();
					mont = cs.retrievemonthly();
					mainlayout(1, 0);
				}
				else {
					cs.errormsg("Invalid");
				}
			}
			
		});
		frame.add(log);
		frame.add(back);
		
		frame.add(usert);
		frame.add(passt);
		frame.validate();
		frame.setVisible(true);
	}
	
	
	public void settinglayout(int func) {
		if (func == 1) {
			frame.setLayout(null);
		}
		else {
			frame.setLayout(new BorderLayout());
		}
	}
	public void removecomp() {
		frame.getContentPane().removeAll();
	}
	public void mainlayout(int func, int select) {
		removecomp();
		setlarge();
		this.settinglayout(2);
		JButton logout = new JButton("Log out");
		choosetab = new JComboBox();
		JPanel pan1 = new JPanel();
		choosetab.addItem("(SELECT report)");
		choosetab.addItem("Transaction list");
		choosetab.addItem("OLX Accounts");
		choosetab.addItem("Reported Users");
		choosetab.addItem("Promo Ads");
		choosetab.addItem("Monthly analytical report");
		choosetab.setSelectedItem(select);
		
		choosetab.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int gyu = choosetab.getSelectedIndex();
				if (gyu != 0) {
					mainlayout(gyu, gyu);
				}
			}
			
		});
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adminlogin();
			}
			
		});
		if (func == 1) {
			String[] transcol = {"transactid", "product", "buyerid", "sellerid", "price", "date", "time", "location"};
			System.out.println(trs.size() + " kun");
			mod = new DefaultTableModel(trs.size(), 8);
			mod.setColumnIdentifiers(transcol);
			for (int i = 0; i < trs.size(); i++) {
				
				for (int j = 0; j < mod.getColumnCount(); j++) {
					if (j == 0) {
						mod.setValueAt(trs.get(i).getTransactionid(), i, j);
					}
					else if (j == 1) {
						mod.setValueAt(trs.get(i).getProduct(), i, j);
					}
					else if (j == 2) {
						mod.setValueAt(trs.get(i).getBuyer(), i, j);
					}
					else if (j == 3) {
						mod.setValueAt(trs.get(i).getSeller(), i, j);
					}
					else if (j == 4) {
						mod.setValueAt(trs.get(i).getPrice(), i, j);
					}
					else if (j == 5) {
						mod.setValueAt(trs.get(i).getDate(), i, j);
					}
					else if (j == 6) {
						mod.setValueAt(trs.get(i).getTime(), i, j);
					}
					else if (j == 7) {
						mod.setValueAt(trs.get(i).getLocation(), i, j);
					}
				}
			}
		}
		else if (func == 2) {
			String[] col = {"Username", "UserID", "Email", "Mobile Number", "No. of transactions", "Current Item/s Sold", "Current Item/s bought"};
			mod = new DefaultTableModel(olxuser.size(), 7);
			mod.setColumnIdentifiers(col);
			for (int i = 0; i < olxuser.size(); i++) {
				for (int j = 0; j < 7; j++) {
					if (j == 0) {
						mod.setValueAt(olxuser.get(i).getName(), i, j);
					}
					else if (j == 1) {
						mod.setValueAt(olxuser.get(i).getId(), i, j);
					}
					else if (j == 2) {
						mod.setValueAt(olxuser.get(i).getEmail(), i, j);
					}
					else if (j == 3) {
						mod.setValueAt(olxuser.get(i).getNumber(), i, j);
					}
					else if (j == 4) {
						mod.setValueAt(olxuser.get(i).getTransactnum(), i, j);
					}
					else if (j == 5) {
						mod.setValueAt(olxuser.get(i).getItemsold(), i, j);
					}
					else if (j == 6) {
						mod.setValueAt(olxuser.get(i).getItembought(), i, j);
					}
				}
			}
		}
		else if (func == 3) {
			String[] col = {"CaseID", "violatorid", "reporterid", "contact no. of reporter", "violation"};
			mod = new DefaultTableModel(rep.size(), 5); 
			mod.setColumnIdentifiers(col);
			for (int i = 0; i < rep.size(); i++) {
				for (int j = 0; j < 5; j++) {
					if (j == 0) {
						mod.setValueAt(rep.get(i).getCaseid(), i, j);
					}
					else if (j == 1) {
						mod.setValueAt(rep.get(i).getViolator(), i, j);
					}
					else if (j == 2) {
						mod.setValueAt(rep.get(i).getEmail(), i, j);
					}
					else if (j == 3) {
						mod.setValueAt(rep.get(i).getContact(), i, j);
					}
					else if (j == 4) {
						mod.setValueAt(rep.get(i).getViolation(), i, j);
					
				}
			}
			}
		}
		else if (func == 4) {
			String[] col = {"Package name", "slotnum", "discount", "price"};
			mod = new DefaultTableModel(prom.size(), 4);
			
			for (int i = 0; i < prom.size(); i++) {
				for (int j = 0; j < 4; j++) {
					if (j == 0) {
						mod.setValueAt(prom.get(i).getPackagename(), i, j);
					}
					else if (j == 1) {
						mod.setValueAt(prom.get(i).getSlotnum(), i, j);
					}
					else if (j == 2) {
						mod.setValueAt(prom.get(i).getDiscount(), i, j);
					}
					else if (j == 3) {
						mod.setValueAt(prom.get(i).getPrice(), i, j);
					}
				}
			}
			mod.setColumnIdentifiers(col);
		}
		else if (func == 5) {
			String[] col = {"Category", "M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10", "M11", "M21", "Total"};
			mod = new DefaultTableModel(mont.size(), 14);
			mod.setColumnIdentifiers(col);
			for (int i = 0; i < mont.size(); i++) {
				for (int j = 0; j < 14; j++) {
					if (j == 0) {
						mod.setValueAt(mont.get(i).getCategory(), i, j);
					}
					else if (j == 1) {
						mod.setValueAt(mont.get(i).getM1(), i, j);
					}
					else if (j == 2) {
						mod.setValueAt(mont.get(i).getM2(), i, j);
					}
					else if (j == 3) {
						mod.setValueAt(mont.get(i).getM3(), i, j);
					}
					else if (j == 4) {
						mod.setValueAt(mont.get(i).getM4(), i, j);
					}
					else if (j == 5) {
						mod.setValueAt(mont.get(i).getM5(), i, j);
					}
					else if (j == 6) {
						mod.setValueAt(mont.get(i).getM6(), i, j);
					}
					else if (j == 7) {
						mod.setValueAt(mont.get(i).getM7(), i, j);
					}
					else if (j == 8) {
						mod.setValueAt(mont.get(i).getM8(), i, j);
					}
					else if (j == 9) {
						mod.setValueAt(mont.get(i).getM9(), i, j);
					}
					else if (j == 10) {
						mod.setValueAt(mont.get(i).getM10(), i, j);
					}
					else if (j == 11) {
						mod.setValueAt(mont.get(i).getM11(), i, j);
					}
					else if (j == 12) {
						mod.setValueAt(mont.get(i).getM12(), i, j);
					}
					else if (j == 13) {
						mod.setValueAt(mont.get(i).getTotal(), i, j);
					}
					
				}
			}
		}
		
		tab1 = new JTable(mod);
		pan1.add(logout, BorderLayout.WEST);
		pan1.add(choosetab, BorderLayout.EAST);
		scrpane = new JScrollPane(tab1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scrpane, BorderLayout.CENTER);
		frame.add(pan1, BorderLayout.SOUTH);
		frame.validate();
		repaint();
	}
	public void repaint() {
		frame.repaint();
	}
	
	public boolean confirm(String user, String pass) {
		String query = "SELECT * FROM admin";
		Connection conn = db.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String user2 = rs.getString(2);
				String pass2 = rs.getString(3);
				
				if (user2.equals(user) && pass.equals(pass2)) {
					return true;
				}
			}
			
		}
		catch (Exception e) {
			
		}
		return false;
	}
	public void retrievepromos() {
		String query = "SELECT * FROM promoads";
		Connection conn = db.getConnection();
		prom = new ArrayList<promodata>(1000);
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				promodata pm = new promodata();
				pm.setPackagename(rs.getString(1));
				pm.setSlotnum(rs.getInt(2));
				pm.setDiscount(rs.getString(3));
				pm.setPrice(rs.getDouble(4));
				prom.add(pm);
			}
		}
		catch (Exception e) {
			System.out.println("PROMO");
		}
	}
	
}
