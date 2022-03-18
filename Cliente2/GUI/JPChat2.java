package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Domain.BordeRedeondo;
import Domain.Paquete;

public class JPChat2 extends JPanel implements Runnable, ActionListener {

	private JButton jbtEnviar;
	private JTextField jtfTexto, jtfNick, jtfIp;
	private JLabel jlblCliente, jlblNick, jlblIp;
	private JTextArea jtaChat;
	private Paquete paquete;

	public JPChat2() {
		this.setSize(400, 400);
		this.setLayout(null);
		this.setBackground(new Color(189, 33, 33));
		init();
		new Thread(this).start();
	} // JPChat1

	public void init() {

		this.jlblCliente = new JLabel("Chat");
		this.jlblCliente.setBounds(172, 4, 100, 25);
		this.jlblCliente.setFont(new Font("BOLD", 1, 25));
		this.jlblCliente.setForeground(new Color(255, 255, 255));
		this.add(jlblCliente);

		this.jlblNick = new JLabel("Nick");
		this.jlblNick.setBounds(35, 5, 100, 20);
		this.jlblNick.setFont(new Font("BOLD", 1, 25));
		this.jlblNick.setForeground(new Color(255, 255, 255));
		this.add(jlblNick);

		this.jlblIp = new JLabel("IP");
		this.jlblIp.setBounds(322, 6, 100, 20);
		this.jlblIp.setFont(new Font("BOLD", 1, 25));
		this.jlblIp.setForeground(new Color(255, 255, 255));

		this.add(jlblIp);

		this.jtfTexto = new JTextField();
		this.jtfTexto.setBounds(150, 30, 100, 20);
		this.jtfTexto.setBackground(new Color(227, 235, 226));
		this.jtfTexto.setBorder(null);
		this.add(jtfTexto);

		this.jtfNick = new JTextField();
		this.jtfNick.setBounds(10, 30, 100, 20);
		this.jtfNick.setBackground(new Color(227, 235, 226));
		this.jtfNick.setBorder(null);
		this.add(jtfNick);

		this.jtfIp = new JTextField();
		this.jtfIp.setBounds(280, 30, 100, 20);
		this.jtfIp.setBackground(new Color(227, 235, 226));
		this.jtfIp.setBorder(null);
		this.add(jtfIp);

		this.jbtEnviar = new JButton("Enviar");
		this.jbtEnviar.setBounds(150, 60, 100, 20);
		this.jbtEnviar.addActionListener(this);
		this.jbtEnviar.setBorder(new BordeRedeondo(20));
		this.jbtEnviar.setBackground(new Color(189, 33, 33));
		this.add(jbtEnviar);

		this.jtaChat = new JTextArea();
		this.jtaChat.setBounds(10, 100, 365, 250);
		this.jtaChat.setEditable(false);
		this.jtaChat.setBackground(new Color(227, 235, 226));
		this.jtaChat.setBorder(null);
		this.add(this.jtaChat);

	} // init

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.jbtEnviar) {


			if (e.getSource() == this.jbtEnviar) {

				try {
	
					Socket socket = new Socket("192.168.1.7", 9998);
	
					this.paquete = new Paquete(jtfNick.getText(), jtfIp.getText(), jtfTexto.getText());
	
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
	
					objectOutputStream.writeObject(this.paquete);
	
					socket.close();
	
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	
			}

		}

	} // actionPerformed

	public void run() {

		try {

			ServerSocket servidorCliente = new ServerSocket(9998);

			Socket cliente;

			Paquete paquete;

			while (true) {

				cliente = servidorCliente.accept();

				ObjectInputStream objectInputStream = new ObjectInputStream(cliente.getInputStream());

				paquete = (Paquete) objectInputStream.readObject();

				this.jtaChat.append(paquete.getNick() + ":" + paquete.getMensaje() + "\n");

			} // while

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	} // run

} // class
