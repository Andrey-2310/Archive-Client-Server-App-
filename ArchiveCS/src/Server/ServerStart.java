package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import Server.Communication.NewThreadCommunication;
import Server.User.UserRepository;
import Person.Person;

public class ServerStart {
	
		//static Cataloger cataloger = new Cataloger();
		final static int PORT = 7777;
		public final static Logger loggerServer = Logger.getLogger(ServerStart.class);

		public static void main(String args[]) {
			ServerSocket ss = null;
			Socket socket = null;
		//	UserRepository.setupUsers();
		//	Person.setUpPersons();
			// new IsClientAlive();
			try {

				ss = new ServerSocket(PORT);
			} catch (IOException e) {
				e.printStackTrace();

			}
			UserRepository.loadData();
			Cataloguer.loadData();
		//	Cataloguer.getAll();
			System.out.println("Vse Norm");
			while (true) {
				try {
					
					socket = ss.accept();
					if(socket!=null) System.out.println("Новый клиент");
				} catch (IOException e) {
					System.out.println("I/O error: " + e);
				}
				new NewThreadCommunication(socket);
			}
		}
}
