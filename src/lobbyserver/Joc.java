package lobbyserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import message.EnumTipusPlayer;

public abstract class Joc {
	protected List<Player> players = new ArrayList<Player>();
	Lock lock = new ReentrantLock();
	protected Player torn;
	long id;
	
public synchronized void playerRemove (Player player){
	lock.lock();
	players.remove(player);
	lock.unlock();
}
public synchronized void playerAdd(Player player, EnumTipusPlayer tipus){
	lock.lock();
	player.setTipus(tipus);
	players.add(player);
	lock.unlock();
}
public abstract void inicia();
protected void enviaTorn(Player player){

}
}
