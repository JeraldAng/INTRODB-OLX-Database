package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import body.main;

public class registerviewer {
	private JTextArea text2;
	private JButton back, conf;
	private JPasswordField pass2, passconf;
	private JLabel lab3, lab4, lab5, lab6;
	private JTextField mob;
	private registercontrol regcon;
	private JFrame frame;
	public registerviewer(JFrame frame) {
		this.frame = frame;
		this.regcon = new registercontrol(frame);
	}
	public void register(JFrame frame) {
		/*
		regcon.removecomp();
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
		
		lab6.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 100, 120, 30);
		lab3.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 200, 120, 30);
		lab4.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 - 50, 120, 30);
		lab5.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 , 120, 30);
		text2.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 190, 120, 30);
		pass2.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 40, 120, 30);
		passconf.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 10, 120, 30);
		conf.setBounds(frame.getWidth() / 2 - 300, frame.getHeight() / 2 + 90, 70, 30);
		back.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 90, 70, 30);
		mob.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 90, 120, 30);
		conf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int gam = Integer.parseInt(mob.getText());
					if (pass2.getText().equals(passconf.getText())) {
						//matching passwords
						System.out.println("YUHAN");
						if (text2.getText().isEmpty()) {
							//empty username
							System.out.println("Empty");
						}
						else {
							int found = 0;
							for (int i = 0; i < maj.size(); i++) {
								if (maj.get(i).getName().equals(text2.getText().trim())) {
									found = 1;
									break;
								}
							}
							if (found == 1) {
								System.out.println("Username already exists");
							}
							else {
								System.out.println("Registered");
								main man = new main();
								String idh = idgive();
								man.setId(idh);
								man.setName(text2.getText().trim());
								man.setPass(pass2.getText());
								man.setNumber(gam);
								man.setLocation("n/a");
								man.setOlxgold(0.21);
								System.out.println(man.getOlxgold());
								add(man);
								login();
							}
						}
					}
					else {
						//mismatching or no password goes here
					}
				}
				catch (Exception er) {
					//invalid phone number
					System.out.println("MAGH");
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
		frame.add(mob);
		frame.add(lab6);
		frame.add(lab3);
		frame.add(lab4);
		frame.add(lab5);
		frame.add(pass2);
		frame.add(passconf);
		frame.add(text2);
		frame.add(conf);
		frame.add(back);
		
		repaint();
		//regf.setVisible(true);
		 
		 */
	}
}
