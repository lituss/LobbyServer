package lobbyserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PlayerEmisor implements Runnable {
private ObjectOutputStream objectOutput;
private Player player;
private BlockingQueue <Object> missatges = new LinkedBlockingQueue <Object>();

	PlayerEmisor (ObjectOutputStream objectOutput, Player player){
		//this.socket = socket;
		this.objectOutput = objectOutput;
		this.player = player;
	}
	public synchronized void messageEnqueue(Object object){
		missatges.add(object);
		notify();
	}
	private synchronized Object messageDequeue(){
		Object aux;
		
		while(missatges.size()==0 )
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		aux = missatges.remove();
		return aux;
	}
	private synchronized void messageEnvia(Object missatge){
		try {
			objectOutput.writeObject(missatge);
			objectOutput.flush();
		} catch (IOException e) {
			// sha tancat el socket
			//e.printStackTrace();
			player.interrupt();
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Thread thread = Thread.currentThread();
		while (!Thread.interrupted()) {
			Object missatge = messageDequeue();
			messageEnvia(missatge);
		}
	}
}
