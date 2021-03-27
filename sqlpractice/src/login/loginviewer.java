package login;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import body.main;

public class loginviewer {
	private JFrame frame;
	private logincontrol cont;
	private JTextArea text1;
	private JPasswordField pass;
	private JLabel lab1, lab2, lab3, lab4, lab5;
	private JButton reg, log;
	public loginviewer(JFrame frame, List<main> maj) {
		this.frame = frame;
		this.cont = new logincontrol(frame);
		login();
		cont.loglistener(log, text1, pass, maj);
	}
	public void login() {
		if (frame != null) {
			cont.removecomp(frame);
		}
		frame.setBounds(300, 500, 600, 400);
		
		
		text1 = new JTextArea();
		pass = new JPasswordField();
		lab1 = new JLabel();
		lab2 = new JLabel();
		log = new JButton("Log in");
		reg = new JButton("Register");
		
		
		text1.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 150, 300, 30);
		pass.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 50, 300, 30);
		lab1.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 150, 100, 30);
		lab2.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 - 50, 100, 30);
		log.setBounds(frame.getHeight() / 2 - 200, frame.getHeight() / 2 + 50, 150, 60);
		reg.setBounds(frame.getHeight() / 2 + 200, frame.getHeight() / 2 + 50, 150, 60);
		
		
		lab1.setText("User");
		lab2.setText("Pass");
		
		
		
		frame.add(text1);
		frame.add(pass);
		frame.add(lab1);
		frame.add(lab2);
		frame.add(reg);
		frame.add(log);
		
		
		frame.setVisible(true);
		cont.repaint(frame);
	}
}
