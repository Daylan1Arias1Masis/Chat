package Servidor;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Domain.Paquete;

public class Servidor extends JFrame implements Runnable {

	private JTextArea jtaChat;

	private JLabel jlblNick;
	private JLabel jlblMensaje;

	public Servidor() {
		this.setSize(400, 400);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(189, 33, 33));
		this.setTitle("Servidor");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		new Thread(this).start();
		init();
		this.setVisible(true);
	} // Servidor

	private void init() {

		this.jtaChat = new JTextArea();
		this.jtaChat.setBounds(10, 10, 363, 341);
		this.jtaChat.setEditable(false);
		this.jtaChat.setBackground(new Color(227, 235, 226));
		this.add(jtaChat);

	} // init

	public JLabel initjlblNick(String nombre) {

		this.jlblNick = new JLabel(nombre + " : ");
		this.setForeground(new Color(255, 255, 0));

		return this.jlblNick;

	}

	public JLabel initjlblMensaje(String mensaje) {

		this.jlblMensaje = new JLabel(mensaje);
		this.setForeground(new Color(255, 255, 0));

		return this.jlblMensaje;

	}

	public void cliente2() {

		try {

			ServerSocket serverSocketCliente2 = new ServerSocket(9997); // revisar

			Paquete paqueteCliente2;

			while (true) {

				Socket socketCliente2 = serverSocketCliente2.accept();

				ObjectInputStream objectInputStreamCliente2 = new ObjectInputStream(socketCliente2.getInputStream());

				paqueteCliente2 = (Paquete) objectInputStreamCliente2.readObject();

				this.jtaChat.append(paqueteCliente2.toString() + "\n");

				Socket socketCliente22 = new Socket("192.168.1.7", 9996);

				ObjectOutputStream paqueteRenvioCliente2 = new ObjectOutputStream(socketCliente22.getOutputStream());

				paqueteRenvioCliente2.writeObject(paqueteCliente2);

				socketCliente22.close();

			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} // cliente2

	public void cliente1() {
		try {

			ServerSocket serverSocketCliente1 = new ServerSocket(9999); // revisar

			Paquete paqueteCliente1;

			while (true) {

				Socket socketCliente1 = serverSocketCliente1.accept();

				ObjectInputStream objectInputStreamCliente1 = new ObjectInputStream(socketCliente1.getInputStream());

				paqueteCliente1 = (Paquete) objectInputStreamCliente1.readObject();

				this.jtaChat.append(paqueteCliente1.toString() + "\n");

				Socket socketCliente12 = new Socket("192.168.1.7", 9998);

				ObjectOutputStream paqueteRenvioCliente1 = new ObjectOutputStream(socketCliente12.getOutputStream());

				paqueteRenvioCliente1.writeObject(paqueteCliente1);

				socketCliente12.close();

				socketCliente1.close();

			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} // cliente1

	public void run() {
		cliente2();
		cliente1();

	} // run
} // Servidor
