package GUI;

import javax.swing.JFrame;

public class JFVentanaPrincipal2 extends JFrame {

	public JFVentanaPrincipal2() {
		this.setSize(400, 400);
		this.setTitle("Chat 2");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new JPChat2());
		this.setVisible(true);
	}
}
