package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

import Server.User.UserDescription;
import Server.User.UserDescription.Roles;
import Server.User.UserRepository;

/*import server.User;
import server.User.Roles;
import server.Users;
import tools.Request;
import tools.Request.Requests;
import tools.Response;
import tools.SerializeManager;
*/


/**
 * The Class ServerThreadCommunication.
 * The object of this class is created every time new client connects to the server.
 * For every client there is personal thread in server
 */
public class NewThreadCommunication implements Runnable {

	/** The socket. */
	private Socket socket;
	
	/** The Thread t. */
	private Thread t;
	
	/** The logged in user. */
	private UserDescription user;
	
	/** Users last operation time. */
	private long lastOperationTime;
	
	/** The in. */
	DataInputStream in;
	
	/** The out. */
	DataOutputStream out;

	/** The alive threads. */
	private static Vector<NewThreadCommunication> aliveThreads = new Vector<NewThreadCommunication>();;

	/**
	 * Instantiates a new server thread communication.
	 *
	 * @param socket the socket
	 */
	public NewThreadCommunication(Socket socket) {
		super();
		this.socket = socket;
		t = new Thread(this);
		aliveThreads.addElement(this);
		t.start();
	}

	public void run() {

		try {
			lastOperationTime = System.currentTimeMillis();

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			ServerStart.loggerServer.info("New client has attached to the server");

			String login = new String();
			login = read(login);
			ServerStart.loggerServer.info(new String("Client entered login: " + login));


			Integer password = null;
			password = read(password);
			ServerStart.loggerServer.info(new String("Clients secured password: " + password));

			user = UserRepository.checkByLoginAndPassword(login, password);

			if (user == null) {
				write("wrong user");
				ServerStart.loggerServer.info("Wrong login or password. Client disconnected");
				return;
			} else if (user.getRole() == Roles.ADMINISTRATOR) {
				write("admin");
				write(user.getName());
				ServerStart.loggerServer.info("Administrator authorized successfully");
			} else if (user.getRole() == Roles.GUEST) {
				write("guest");
				write(user.getName());
				ServerStart.loggerServer.info("Guest " +login+" authorized successfully");
			} else if(user.getRole()==Roles.USER){
				write("user");
				write(user.getName());
				ServerStart.loggerServer.info(new String("Client " + login + " authorized successfully"));
			}/*
			Request request = null;
			Response responce = null;

			while (true) {
				lastOperationTime = System.currentTimeMillis();
				request = new Request();
				request = read(request);

				if(!aliveThreads.contains(this)){
					ServerMain.loggerServer.info(new String("Client " + login + " is disconnected from a server"));
					break;
				}
				
				if (request.getRequest() == Requests.EXIT) {
					if(aliveThreads.remove(this)){
						ServerMain.loggerServer.info(new String("Client " + login + " finished its work"));
					}
					break;
				}

				try {
					responce = new ServeRequest(request).serveRequest();
					write(responce);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}

			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Write.
	 *
	 * @param <T> the generic type
	 * @param t the t
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
	 * @param <T> the generic type
	 * @param field the field
	 * @return the t
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

	/**
	 * Gets the random.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the random
	 */
	private int getRandom(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	/**
	 * Gets the t.
	 *
	 * @return the t
	 */
	public Thread getT() {
		return t;
	}

	/**
	 * Gets the last operation time.
	 *
	 * @return the last operation time
	 */
	public long getLastOperationTime() {
		return lastOperationTime;
	}

	/**
	 * Gets the alive threads.
	 *
	 * @return the alive threads
	 */
	public static Vector<NewThreadCommunication> getAliveThreads() {
		return aliveThreads;
	}

}
