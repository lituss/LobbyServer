package lobbyserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import message.enums.*;
import message.SCgame;

public abstract class Joc {
	protected List<Player> players = new ArrayList<Player>();
	Lock lock = new ReentrantLock();
	protected Player torn;
	protected int id;
	protected EnumEstatJoc estat;
	protected int minPlayers , maxPlayers;
	protected int nPlayers;
	
public synchronized void playerRemove (Player player){
	lock.lock();
	players.remove(player);
	nPlayers--;
	lock.unlock();
}
public synchronized void playerAdd(Player player, EnumTipusPlayer tipus){
	lock.lock();
	player.setTipus(tipus);
	players.add(player);
	nPlayers++;
	lock.unlock();
	if (nPlayers == maxPlayers) inicia();
}
public synchronized void startPlay(){
	if (nPlayers >= minPlayers) inicia();
}
public synchronized void broadcast(Object object){
	for (Player player : players){
		player.playerEmisor.messageEnqueue(object);
	}
}
public abstract void inicia();
protected void enviaTorn(Player player){

}
public SCgame pack(){
	SCgame aux = new SCgame();
	aux.id = id;
	aux.estat = estat;
	aux.minPlayers = minPlayers;
	aux.maxPlayers = maxPlayers;
	aux.players = nPlayers;
	return aux;
}
}
