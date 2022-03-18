package GUI;

import javax.swing.JFrame;

public class JFVentanaPrincipal1 extends JFrame {

    public JFVentanaPrincipal1() {
    	this.setSize(400, 400);
		this.setTitle("Chat 1");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new JPChat1());
		this.setVisible(true);
    }
}
