package Domain;

import java.io.Serializable;

public class Paquete implements Serializable {

	private String nick, ip, mensaje;

	public Paquete(String nick, String ip, String mensaje) {
		this.nick = nick;
		this.ip = ip;
		this.mensaje = mensaje;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String toString() {
		return "nick= " + nick + ", ip=" + ip + ", mensaje=" + mensaje;
	}

}
