package lobbyserver;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.*;

import message.*;
import message.enums.EnumEstats;
import message.enums.EnumJoin;
import message.enums.TipusMissatge;


	public class WorkerRunnable implements Runnable{

	    protected Socket clientSocket = null;
	    protected String serverText   = null;
	    protected EnumEstats estat = EnumEstats.INICIAL;
	    protected InputStream input = null;
	    protected OutputStream output = null;
	    protected ObjectInputStream objectInput ;
	    protected ObjectOutputStream objectOutput;
	    protected Lobby lobby;
	    protected String Alias;
	    protected boolean active = true;

	    public WorkerRunnable(Socket clientSocket, String serverText, Lobby lobby) {
	        this.clientSocket = clientSocket;
	        this.serverText   = serverText;
	        this.lobby = lobby;
	    }

	    public void run() {
	        try {
	        	System.out.println("Server :: tenim nou socket (nou client)");
	        	output = clientSocket.getOutputStream();
	            objectOutput = new ObjectOutputStream(output);
	            objectOutput.flush();
	            input  = clientSocket.getInputStream();
	            objectInput = new ObjectInputStream(input);
	            
	            //estatInicial();
	            dispatcher();
	            /*long time = System.currentTimeMillis();
	            /output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
	                    this.serverText + " - " +
	                    time +
	                    "").getBytes());
	            output.close();
	            input.close();
	            System.out.println("Request processed: " + time);
	            */
	        } catch (IOException | ClassNotFoundException e) {
	            //report exception somewhere.
	            e.printStackTrace();
	        }
	    }
	    
	    void dispatcher() throws ClassNotFoundException, IOException{
	    	Object aux;
	    	CSsign prova = new CSsign();
	    	prova.user = "aaa";
	    	prova.pass = "bbb";
	    	//aux = (Object) prova;
	    	TipusMissatge tm = null;
	    	
	    	while (active){
	    		aux = objectInput.readObject();
	    		try{
	    		tm = ((CtipusMissatge) aux).tipusM;
	    		} catch (ClassCastException e){
	    			System.out.println("Entra brossa al socket"+aux.toString());
	    			continue;
	    		}
	    		switch (tm){
	    			case  CSlogin : onLogin((CSlogin)aux);
	    				break;
	    			case CSsign : onJoin(((CSsign)aux));
	    				break;
	    		}
	    	}
	    }
	    
	 /*   void estatInicial()	{
	    	try {
	    		while 
				Object aux = objectInput.readObject();
	    	
				if (aux instanceof CSlogin) { onLogin((CSlogin)aux);}
				else if (aux instanceof  CSsign) {  onJoin ((CSsign)aux);}
	   
	    	
			} catch (Exception e) {e.printStackTrace();
				// TODO: handle exception
			}
	    }
	   */ 
		void onLogin (CSlogin cslogin)
		{
			SClogged sclogged = new SClogged();
		//	Player player;
			
			if (lobby.sql.login(cslogin.user, cslogin.pass)){
				new Player(cslogin.user,objectInput,objectOutput,lobby);
				estatFinal();
			}
			else {
				if (lobby.sql.existeixUsuari(cslogin.user)) sclogged.login = EnumJoin.KO_BAD_PASS;
				else sclogged.login = EnumJoin.KO_BAD_USER;
			
				try {
					objectOutput.writeObject(sclogged);
					objectOutput.flush();
					estatFinal();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					  e.printStackTrace();
					  }
				   }
			}
	    void onJoin( CSsign csjoin)
	    {
	    	SCsigned scjoined = new SCsigned();
	    	System.out.println("Rebut CSsign amb parametres : "+csjoin.user+" , "+csjoin.pass);
	    	if (lobby.sql.join(csjoin.user, csjoin.pass)) {
	    		
	    		CSlogin cslogin = new CSlogin();
	    		cslogin.user = csjoin.user;
	    		cslogin.pass = csjoin.pass;
	    		onLogin(cslogin);
	    	}
	    	
	    	else {
	    		scjoined.join = EnumJoin.KO_BAD_USER;
	    	}
	    	try {
				objectOutput.writeObject(scjoined);
				objectOutput.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	estatFinal();	
	   	}
	    	
	    
	    void estatFinal(){
	    	try{
	    
	    
	    	objectOutput.close();
	    	output.close();
	    	objectInput.close();
	    	input.close();
	    	clientSocket.close();
	    	active = false;
	    	} catch (IOException e) {e.printStackTrace();}
	    }
	    
	    	
	    
	
	}
