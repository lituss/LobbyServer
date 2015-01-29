package lobbyserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Joc {
	List<Player> players = new ArrayList<Player>();
	Lock lock = new ReentrantLock();
	
public synchronized void playerRemove (Player player){
	lock.lock();
	players.remove(player);
	lock.unlock();
}
public synchronized void playerAdd(Player player){
	lock.lock();
	players.add(player);
	lock.unlock();
}
}
