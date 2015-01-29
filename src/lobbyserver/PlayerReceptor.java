package lobbyserver;

import java.io.IOException;
import java.io.ObjectInputStream;

public class PlayerReceptor implements Runnable {
	private ObjectInputStream objectInput;
	private Player player;
	//private Boolean interruption = false;
	
	PlayerReceptor(ObjectInputStream objectInput,Player player){
		this.objectInput = objectInput;
		this.player = player;
	}

	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		player.threadPlayerReceptor = thread;
		try {
	           while ( !thread.isInterrupted()) {
	               Object missatge = objectInput.readObject();
	               if (missatge == null)
	                   break;
	               player.tractaMissatge(missatge);
	           }
	        } catch (IOException ioex) {
	           // Problem reading from socket (communication is broken)
	        	player.interrupt();
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	//private boolean isInterrupted() {
		// TODO Auto-generated method stub
	//	return interruption;
	//}

}
