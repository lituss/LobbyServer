package lobbyserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PlayerReceptor implements Runnable {
	private BlockingQueue <Object> missatgesEntrants = new LinkedBlockingQueue <Object>();
	private ObjectInputStream objectInput;
	private Player player;
	//private Boolean interruption = false;
	
	PlayerReceptor(ObjectInputStream objectInput,Player player){
		this.objectInput = objectInput;
		this.player = player;
	}
	synchronized void messageEnqueue(Object missatge){
		missatgesEntrants.add(missatge);
		player.notify();
	}
	
	public BlockingQueue <Object> getMissatgesEntrants(){
		return missatgesEntrants;
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
	               
	               messageEnqueue(missatge);
	               //player.tractaMissatge(missatge);
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
