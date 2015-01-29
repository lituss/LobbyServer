

package lobbyserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.*;

import message.EnumJoin;
import message.SClobbyPlayers;
import message.SClogged;
import message.SCrooms;

public class Player {
	String alias;
	private EnumEstats estat;
	private Room room = null;
	private Joc joc = null;
	PlayerEmisor playerEmisor;
	Thread threadPlayerEmisor;
	public PlayerReceptor playerReceptor;
	public Thread threadPlayerReceptor;
	Lobby lobby;
	static Lock bloqueja;
	long token;
	static ConcurrentMap<Long,Player> tokens = new ConcurrentHashMap<Long,Player>();
	
	
	public String getAlias() {
		return alias;
	}
	public Player (String alias,ObjectInputStream objectInput, ObjectOutputStream objectOutput, Lobby lobby){
		this.alias = alias;
		this.playerEmisor = new PlayerEmisor(objectOutput, this);
		this.playerReceptor = new PlayerReceptor(objectInput,this);
		this.lobby = lobby;
		setEstat(EnumEstats.LOGIN);
		token = generaToken();
		SClogged sclogged = new SClogged();
		sclogged.login = EnumJoin.OK.ordinal();
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
}
