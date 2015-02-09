

package lobbyserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.*;

import message.CSlobbyChat;
import message.CSlogin;
import message.CtipusMissatge;
import message.EnumEstats;
import message.EnumJoin;
import message.EnumTipusPlayer;
import message.SClobbyPlayers;
import message.SClogged;
import message.SCrooms;
import message.TipusMissatge;

public class Player {
	String alias;
	private EnumEstats estat;
	private EnumTipusPlayer tipus;
	public EnumTipusPlayer getTipus() {
		return tipus;
	}
	public void setTipus(EnumTipusPlayer tipus) {
		this.tipus = tipus;
	}
	private long saldo;
	private Room room = null;
	private Joc joc = null;
	PlayerEmisor playerEmisor;
	Thread threadPlayerEmisor;
	public PlayerReceptor playerReceptor;
	public Thread threadPlayerReceptor;
	private ObjectOutputStream objectOutput;
	private ObjectInputStream objectInput;
	Lobby lobby;
	static Lock bloqueja;
	long token;
	static ConcurrentMap<Long,Player> tokens = new ConcurrentHashMap<Long,Player>();
	private BlockingQueue <Object> missatgesEntrants;
	private boolean actiu;
	
	
	public String getAlias() {
		return alias;
	}
	public Player (String alias,ObjectInputStream objectInput, ObjectOutputStream objectOutput, Lobby lobby){
		this.alias = alias;
		this.playerEmisor = new PlayerEmisor(objectOutput, this);
		this.playerReceptor = new PlayerReceptor(objectInput,this);
		threadPlayerEmisor = new Thread(playerEmisor);
		threadPlayerReceptor = new Thread(playerReceptor);
		threadPlayerEmisor.start();
		threadPlayerReceptor.start();
		this.lobby = lobby;
		this.objectInput = objectInput;
		this.objectOutput = objectOutput;
		setEstat(EnumEstats.LOGIN);
		enviaLogin();
		lobby.addPlayer(this);
		setEstat(EnumEstats.LOBBY);
		missatgesEntrants = playerReceptor.getMissatgesEntrants();
		actiu = true;
		while (actiu) tractaMissatge(messageDequeue());
	}
	
	private synchronized Object messageDequeue(){
		Object aux;
		
		while(missatgesEntrants.size()==0 )
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (!actiu) return null;
		aux = missatgesEntrants.remove();
		return aux;
	}
	
	private void enviaLogin(){
		token = generaToken();
		tokens.put(token, this);
		SClogged sclogged = new SClogged();
		sclogged.login = EnumJoin.OK;
		sclogged.token = token;
		SCrooms scrooms = new SCrooms();
		//Room auxRoom;
		List<Room> auxRooms = lobby.listRooms();
		SCrooms.SCroom auxSCroom = scrooms.new SCroom();
		for (Room auxRoom : auxRooms){
			auxSCroom.nom = auxRoom.getNom();
			auxSCroom.numJugadors = auxRoom.getNumJugadors();
			auxSCroom.tipusSala = auxRoom.getTipusSala();
			auxSCroom.maxJugadors = auxRoom.getMaxJugadors();
			scrooms.rooms.add(auxSCroom);
		}
		SClobbyPlayers sclobbyPlayers = new SClobbyPlayers();
		sclobbyPlayers.players = lobby.listLobbyPlayers();
		
		try {
			objectOutput.writeObject(sclogged);
			objectOutput.writeObject(scrooms);
			objectOutput.writeObject(sclobbyPlayers);
			objectOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	synchronized void tractaMissatge(Object missatge){
		TipusMissatge tm = null;
	
		if (!actiu) return;
		try{
    		tm = ((CtipusMissatge) missatge).tipusM;
    		} catch (ClassCastException e){
    			System.out.println("Entra brossa al socket"+missatge.toString());
    			e.printStackTrace();
    		}
		switch (estat) {
			case LOBBY :{
				switch(tm) {
		
    		    			case CSlobbyChat : lobby.broadChat(((CSlobbyChat) missatge).getTexte());
    		    			break;
				}
    		}
    	}
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public EnumEstats getEstat() {
		return estat;
	}
	public void setEstat(EnumEstats estat) {
		this.estat = estat;
	}
	public synchronized void interrupt (){
		threadPlayerEmisor.interrupt();
		threadPlayerReceptor.interrupt();
		actiu = false;
		
		lobby.removePlayer(this);
		if (room != null) room.playerRemove(this);
		if (joc != null) joc.playerRemove(this);
		
	}
	private synchronized long generaToken(){
    	boolean trovat = false;
    	long valor = 0;
    	while (!trovat){
    		valor = (long) (Math.random()*Long.MAX_VALUE);
    		if (!tokens.containsKey(valor)) trovat = true;
    		tokens.put(valor, this);
    		}
    	return valor;
		}
	public long getSaldo() {
		return saldo;
	}
	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
}
