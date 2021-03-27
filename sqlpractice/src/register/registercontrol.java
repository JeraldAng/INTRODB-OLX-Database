package register;

import javax.swing.JFrame;

public class registercontrol {
	private JFrame frame;
	public registercontrol(JFrame frame) {
		this.frame = frame;
	}
	public void removecomp() {
		frame.getContentPane().removeAll();
	}
}
