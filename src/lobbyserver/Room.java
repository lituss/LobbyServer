package lobbyserver;
import java.util.concurrent.*;
import message.EnumTipusSales;

public class Room {
	public String nom;
	EnumTipusSales tipusSala;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public EnumTipusSales getTipusSala() {
		return tipusSala;
	}
	public void setTipusSala(EnumTipusSales tipusSala) {
		this.tipusSala = tipusSala;
	}
	public int getMaxJugadors() {
		return maxJugadors;
	}
	public void setMaxJugadors(int maxJugadors) {
		this.maxJugadors = maxJugadors;
	}
	public int getNumJugadors() {
		return numJugadors;
	}
	public void setNumJugadors(int numJugadors) {
		this.numJugadors = numJugadors;
	}
	public BlockingQueue<Player> getJugadors() {
		return jugadors;
	}
	public void setJugadors(BlockingQueue<Player> jugadors) {
		this.jugadors = jugadors;
	}
	int maxJugadors;
	int numJugadors = 0;
	
	transient BlockingQueue <Player> jugadors = new LinkedBlockingQueue <Player>();
	
	
	public Room(String nom, EnumTipusSales tipusSala, int maxJugadors){
		this.nom = nom;
		this.tipusSala = tipusSala;
		this.maxJugadors = maxJugadors;
	}
	public synchronized void playerAdd(Player player){
		jugadors.add(player);
	}
	public synchronized void playerRemove(Player player){
		jugadors.remove(player);
	}
}
