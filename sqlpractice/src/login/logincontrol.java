package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import body.main;
import register.registerviewer;

public class logincontrol {
	private loginmodel logm;
	private JFrame frame;
	private registerviewer regview;
	public logincontrol(JFrame frame) {
		this.frame = frame;
		this.regview = new registerviewer(frame);
		this.logm = new loginmodel();
		
	}
	public void storemodel() {
		
	}
	public void removecomp(JFrame frame) {
		frame.getContentPane().removeAll();
	}
	public void repaint(JFrame frame) {
		frame.repaint();
	}
	public void loglistener(JButton log, JTextArea text1, JPasswordField pass, List<main> maj) {
		log.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logm.setUsername(text1.getText());
				logm.setPassword(pass.getText());
				String user = logm.getUsername();
				String passw = logm.getPassword();
				for (int i = 0; i < maj.size(); i++) {
					
					if (maj.get(i).getName().equals(user) && maj.get(i).getPass().equals(passw)) {
						System.out.println("Authent");
						break;
						//mainmenu();
					}
					else {
						System.out.println("Faye");
					}
				}
			}
			
		});
	}
	
	public void reglistener(JButton reg) {
		reg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
