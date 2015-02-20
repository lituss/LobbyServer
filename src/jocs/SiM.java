package jocs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lobbyserver.Joc;
import lobbyserver.Player;
import message.enums.EnumEstatJoc;
import message.enums.EnumTipusPlayer;
import message.games.SiM.CSSiMJugada;
import message.games.SiM.SCSiMPlayers;
import message.games.SiM.SCSiMTorn;
import message.utils.CircularDoubleLinkedList;

public class SiM extends Joc {
	Cartes40 cartes = new Cartes40();
	List<SiMplayer> simPlayers = new ArrayList<SiMplayer>();
	SiMplayer ma;
	SiMplayer banca;
	SiMplayer torn;
	int apostaMinima,apostaMaxima;
	boolean primeraMa = true;
	
	public SiMplayer getSiMplayer(Player player){
		return (simPlayers.get(simPlayers.indexOf(player)));
		}
	
	@Override
	public void inicia() {
		// TODO Auto-generated method stub
		// carreguem jugadors reals
		// sortegem torn inicial (banca)
		// repartim 
		
	
		for (Player auxPlayer : players){
			if (auxPlayer.getTipus() == (EnumTipusPlayer.HUMAN ) ||
					auxPlayer.getTipus() == EnumTipusPlayer.BOT) {
						simPlayers.add(new SiMplayer(auxPlayer));
					}
		}
		if (simPlayers.size() < minPlayers) {
			estat = EnumEstatJoc.wait;
			return ;
		}
		if (primeraMa){
			estat = EnumEstatJoc.play;
			//banca = simPlayers.get((int)Math.round(Math.random()*simPlayers.size()));
			banca = null;
			while ((banca = enviaSorteigBanca()) != null);
			ma = banca.next();
			torn = ma;
		}
		enviaJugadors();
		reparteix();
		enviaTorn();
		
	}
	
	void enviaJugadors(){
		SCSiMPlayers sc = new SCSiMPlayers();
		sc.SiMplayers = simPlayers;
		sc.banca = banca;
		broadcast(sc);
	}
	
	void enviaTorn(){
		SCSiMTorn scTorn = new SCSiMTorn();
		scTorn.torn = torn.player;
		broadcast(scTorn);
	
		
	}
	SiMplayer enviaSorteigBanca(){
		return torn; // canviar!!! perque no doni error
	}
	void enviaCarta(SiMplayer player, Carta carta){
		// enviem a tots , a player destapada
	}
	void enviaErrorAposta(Player player){
		
	}
	void enviaDestapa(SiMplayer simPlayer){
		
	}
	void reparteix(){
		SiMplayer player = ma;
		Carta carta;
		cartes.reset();
		
		do{
			carta = cartes.get();
			player.cartes.add(carta);
			enviaCarta(player,carta);
			player = player.next();
		} while (player != ma);
		
	}
	void enviaRecullirCartes(){
		
	}
	
	void finalPartida(){
		//destapar , saldar , veure si hi ha canvi jugador banca, començar seguent ma
		Player novaBanca = null;
	}
	
	void passaTorn(){
		torn = torn.next();
		if (torn == ma) finalPartida();
	}
	/* metodes cridats desde el client
	 * 
	 */
	
	void rebJoc (Player player,CSSiMJugada jugada){
		SiMplayer sim = getSiMplayer(player);
		player.setSaldo(player.getSaldo() - jugada.aposta);
		sim.incAposta(jugada.aposta);
		if (jugada.passa) passaTorn();
	}
	
			
	/* -----------------------------------
	 * 
	 */
	
	public class SiMplayer {

		public Player player;
		List <Carta> cartes;
		int aposta;
		boolean capTapada = false;
		boolean passa = false;
		
		public SiMplayer(Player auxPlayer) {
			// TODO Auto-generated constructor stub
			this.player = player;
		}
		public SiMplayer next(){
			int index = simPlayers.indexOf(this);
			if (++index > simPlayers.size()) index = 0;
			return simPlayers.get(index);
		}
		public void incAposta(int aposta){
			this.aposta+=aposta;
		}
		
	}

}
