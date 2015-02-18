package lobbyserver;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import message.EnumTipusSales;
import message.SCplayer;
import message.SCroomGames;

public class Room {
	public String nom;
	EnumTipusSales tipusSala;
	transient BlockingQueue <Player> jugadors = new LinkedBlockingQueue <Player>();
	transient BlockingQueue <Joc> jocs;
	int maxJugadors;
	int numJugadors = 0;
	int id;
	
	static int conta = 0;
	
	private int putId(){
		return ++conta;
	}
	
	public int getId(){
		return id;
	}
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
	
	
	
	
	
	public Room(String nom, EnumTipusSales tipusSala, int maxJugadors){
		this.nom = nom;
		this.tipusSala = tipusSala;
		this.maxJugadors = maxJugadors;
		id = putId();
	}
	public synchronized void playerAdd(Player player){
		jugadors.add(player);
	}
	public synchronized void playerRemove(Player player){
		jugadors.remove(player);
	}
	public synchronized List<SCplayer> playerList(){
		List <SCplayer> llista = new LinkedList<>();
		for (Player player : jugadors) {
			SCplayer scplayer = new SCplayer();
			scplayer.alias = player.getAlias();
			scplayer.estat = player.getEstat();
			scplayer.tipus = player.getTipus();
			scplayer.token = player.token;
			
			llista.add(scplayer);
		}
		return llista;
	}
	public synchronized void addJoc(Joc joc){
		jocs.add(joc);
	}
	public synchronized void removeJoc(Joc joc){
		jocs.remove(joc);
	}
	public SCroomGames pack(){
		SCroomGames packet = new SCroomGames();
		packet.maxJugadors = maxJugadors;
		packet.nom = nom;
		packet.players = playerList();
		packet.tipusSala = tipusSala;
		return (packet);
	}
}
