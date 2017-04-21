package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class ClientConnector {

	/** The address of the server */
	private String address;

	/** The port of the server */
	private int serverPort;

	/** The DataInputStream in is used to read data from socket */
	private DataInputStream in;

	/** The DataOutputStream out is used to write data to socket */
	private DataOutputStream out;

	/** The login. */
	private String login;

	/** The password. */
	private String password;

	/** True if the logged in user is admin. */
	private boolean isAdmin = false;

	/** True if the logged in user is guest. */
	private boolean isGuest = false;
	
	/** True if the logged in user is user. */
	private boolean isUser = false;
	
	private Socket socket;	
	
	public ClientConnector(String login, String password) {
		super();

		serverPort = 7777;
		address = "127.0.0.1";

		this.login = login;
		this.password = password;
	}
	
	/**
	 * Writes request to the server and sends it
	 *
	 * @param <T>
	 *            the type of data to send
	 * @param t
	 *            the data to send
	 */
	private <T> void write(T t) {
		String serializedObj = new Serialization.SerializeManager<T>().serialize(t);
		try {
			out.writeUTF(serializedObj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Read.
	 *
	 * @param <T>
	 *            the type of data to read
	 * @param field
	 *            the variable to store read data
	 * @return the read data
	 */
	private <T> T read(T field) {
		String serializedObj = new String();
		try {
			serializedObj = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Serialization.SerializeManager<T>().deserialize(serializedObj, field);
	}
	
	public String setConnection() {
		try {
			InetAddress ipAddress = InetAddress.getByName(address);
			//@SuppressWarnings("resource")
			socket = new Socket(ipAddress, serverPort);

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		write(login);


		Integer pswd = new Integer( password.hashCode()   );  // + rnd);
		write(pswd);

		
		String authorization = new String();
		String userName=null;
		authorization = read(authorization);
		System.out.println(authorization);
		if (authorization.equals("wrong user")) {
			return null;
		} else if (authorization.equals("admin")) {
			isAdmin = true;
			userName=read(userName);
			return userName;
		} else if (authorization.equals("guest")) {
			isGuest = true;
			userName=read(userName);
			return userName;
		}
		 else if (authorization.equals("user")) {
				isUser = true;
				userName=read(userName);
				return userName;
		 }
		return null;
	}

}
