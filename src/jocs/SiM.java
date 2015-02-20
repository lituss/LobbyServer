package jocs;

import java.util.ArrayList;
import java.util.List;

import lobbyserver.Joc;
import lobbyserver.Player;
import message.enums.EnumTipusPlayer;

public class SiM extends Joc {
	Cartes40 cartes = new Cartes40();
	ArrayList <SiMplayer> simPlayers = new ArrayList<SiMplayer>();
	SiMplayer torn;
	SiMplayer banca;
	int apostaMinima,apostaMaxima;
	
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
		//banca = simPlayers.get((int)Math.round(Math.random()*simPlayers.size()));
		banca = enviaSorteigBanca();
		torn = banca.next();
		enviaBanca();

		reparteix();
		
		
		
		enviaTorn();
		
	}
	
		//for (SiMplayer auxSiM : )
	void enviaBanca(){
		
	}
	void enviaTorn(){
		
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
		SiMplayer player = torn;
		Carta carta;
		
		do{
			carta = cartes.get();
			player.cartes.add(carta);
			enviaCarta(player,carta);
			player = player.next();
		} while (player != torn);
		
	}
	
	void finalPartida(){
		//destapar , saldar , començar seguent ma
	}
	/* metodes cridats desde el client
	 * 
	 */
	void rebAposta(int aposta,Player player){
		SiMplayer sim = getSiMplayer(player);
		if (player.getSaldo() - aposta < 0) enviaErrorAposta(player);
		player.setSaldo(player.getSaldo() - aposta);
		sim.aposta+=aposta;
	}
	void rebDemanaCarta(boolean tapada, Player player){
		
	}
	void rebPlanta(Player player){
		torn = torn.next();
		if (torn == banca) enviaDestapa(torn);
		if (torn == banca.next()) finalPartida();
		enviaTorn();
	}
	

	
	/* -----------------------------------
	 * 
	 */
	
	public class SiMplayer {

		Player player;
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
		
	}

}
