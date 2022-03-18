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
		
		this.jlblNick = new JLabel(nombre +	" : ");
		this.setForeground(new Color(255,255,0));

		return this.jlblNick;

	}

	public JLabel initjlblMensaje(String mensaje) {
		
		this.jlblMensaje = new JLabel(mensaje);
		this.setForeground(new Color(255,255,0));

		return this.jlblMensaje;

	}

	public void run() {

		try {

			ServerSocket serverSocket = new ServerSocket(9999); // revisar

			String nick, ip, mensaje;

			Paquete paquete;

			while (true) {

				Socket socket = serverSocket.accept();

				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

				paquete = (Paquete) objectInputStream.readObject();
				
				this.jtaChat.append(paquete.toString());
				

				Socket socket2 = new Socket("192.168.1.7", 9998);

				ObjectOutputStream paqueteRenvio = new ObjectOutputStream(socket2.getOutputStream());

				paqueteRenvio.writeObject(paquete);

				socket2.close();
				socket.close();

			} // while
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	} // run
} // Servidor
