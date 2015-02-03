package lobbyserver;

import java.io.Console;

public class Server {

	public static Lobby lobby = new Lobby();
	public static sqlDB sql = new sqlDB();
	public static final int port = 8889; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadPooledServer server = new ThreadPooledServer(port,lobby);
		
		
		new Thread(server).start();
		//Console console = System.console();
		//console.printf("Server online, listening on port %i ...",port);
		//console.flush();
		System.out.println("New Server online on port : "+port);
		System.out.flush();
		try {
		    Thread.sleep(2000 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop();
	}

}
