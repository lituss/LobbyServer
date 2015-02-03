package lobbyserver;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.net.Socket;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import message.EnumEstats;
import message.EnumTipusSales;
import message.SClobbyChat;



public class Lobby {

	BlockingQueue <Room> rooms = new LinkedBlockingQueue <Room>(); 
	BlockingQueue <Socket> sockets = new LinkedBlockingQueue <Socket>();
	BlockingQueue <Player> players = new LinkedBlockingQueue <Player>();
	sqlDB sql= new sqlDB();
	static Lock bloqueja;
	
	public Lobby()
	{
		bloqueja = new ReentrantLock();
		rooms.add(new Room("SetiMig",EnumTipusSales.SetIMig,1024));
	}
	
	synchronized void addPlayer(Player player){
	
			bloqueja.lock();
			players.add(player);
			bloqueja.unlock();
			notificaNewPlayer(player);
			
	}
	public synchronized void removePlayer(Player player){
		bloqueja.lock();
		players.remove(player);
		bloqueja.unlock();
		notificaRemovedPlayer(player);
	}
	public synchronized void PlayerToRoom(){
		
	}
	
	synchronized List<String> listLobbyPlayers(){
		
		List<String> res = new LinkedList<String>();
		bloqueja.lock();
		for (Player player : players) res.add(player.getAlias());
		//Iterator <Player> it = players.iterator();
		//while(it.hasNext()) { res.add(it.next().alias);}
		bloqueja.unlock();
		return (res);
	}
	
	List<Room> listRooms(){
		List<Room> res = new LinkedList<Room>();
		bloqueja.lock();
		for(Room room : rooms) res.add(room);
		//Iterator <Room> it = rooms.iterator();
		//while(it.hasNext()) { res.add(it.next());}
		bloqueja.unlock();
		return (res);
	}
	void notificaNewPlayer(Player player)
	// enviem a tots que s'ha incorporat un nou jugador
	{
		String string = player.alias+" acaba de entrar :)";
		broadChat(string);
		
	}
	void notificaRemovedPlayer(Player player){
		// notifiquem a tots que el jugador a marxat
		String string = player.alias + "Ha marxat :(";
		broadChat(string);
	}
    public synchronized void broadChat(String texte){
    	// enviem missatge a tots els players que estan al lobby
    	bloqueja.lock();
    	for (Player player : players){
    		if (player.getEstat() == EnumEstats.LOBBY) {
    			SClobbyChat scLobbyChat = new SClobbyChat();
    			scLobbyChat.setTexte(texte);
    			player.playerEmisor.messageEnqueue(scLobbyChat);
    		}
    	}
    	bloqueja.unlock();
	}
	
}
