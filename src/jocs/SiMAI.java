package jocs;

import java.util.ArrayList;
import java.util.List;

import jocs.SiM.SiMplayer;

public class SiMAI {
	Cartes40 cartes;
	SiMplayer player;
	List<SiMplayer> otherPlayers;
	int apostaMinima,apostaMaxima;
	
	public SiMAI(SiM sim, SiMplayer player){
	try {
		cartes = sim.cartes.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.player = player;
	apostaMinima = sim.apostaMinima;
	apostaMaxima = sim.apostaMaxima;
	
	for (SiMplayer auxPlayer : sim.simPlayers ){
		if (auxPlayer.passa) continue; 
		otherPlayers.add(auxPlayer);
		for (Carta auxCarta : auxPlayer.cartes)
			if (auxCarta.tapada = true && auxPlayer != player) cartes.munt.add(auxCarta);
	}
	}
	
	private float valorCarta (Carta carta){
		if (carta.num > 7) return (0.5f);
		else return (carta.num);
	}
	
	private void calcula(float suma,float mitja,float valorTapada, SiMplayer playerCurrent){
		float valorActual = 0;
		
		for ( Carta auxCarta : playerCurrent.cartes) 
			valorActual+=valorCarta(auxCarta);
		
		suma = 0;
		mitja = 0;
		valorTapada = 0;
		for (Carta auxCarta : cartes.munt){
			float nouValor = valorCarta(auxCarta)+valorActual;
			if (nouValor > 7.5f) suma+=-playerCurrent.aposta;
			else if (nouValor == 7.5f) suma+=2*playerCurrent.aposta;
			else suma+=playerCurrent.aposta;
			mitja+=valorCarta(auxCarta);
			if (auxCarta.tapada) valorTapada = valorCarta(auxCarta);
		}
		mitja/=cartes.size();
	}
	
	private void calculaBanca(float suma, SiMplayer playerCurrent){
		float valorActual = 0;
		int numCartes = 0;
		
		if (playerCurrent != player){ // no es banca
			for ( Carta auxCarta : playerCurrent.cartes) 
				valorActual+=valorCarta(auxCarta);
		
			suma = 0;
	
			for (Carta auxCarta : cartes.munt){
				float nouValor = valorCarta(auxCarta)+valorActual;
				if (nouValor > 7.5f) continue; // aquesa carta no la pot tenir perque es passaria
				numCartes++;
				suma+=nouValor;
			}
			suma/=numCartes;
		}
		else {
			for ( Carta auxCarta : playerCurrent.cartes) 
				valorActual+=valorCarta(auxCarta);
		
			suma = 0;
	
			for (Carta auxCarta : cartes.munt){
				float nouValor = valorCarta(auxCarta)+valorActual;
				suma+=nouValor;
				numCartes++;
			}
			suma/=numCartes;
		}
	}
	
	public void jugada( boolean esTres, boolean esQuatre, boolean demanaCarta, boolean tapada , int novaAposta){
		if (player.cartes.size() == 1){
			if (player.cartes.get(0).num == 3) {esTres = true;return;}
			if (player.cartes.get(0).num == 4) {esQuatre = true;return;}
		}
		esTres = false;
		esQuatre = false;
		float suma=0,mitja=0,valorTapada=0;
		calcula(suma,mitja,valorTapada,player);
		
		if (suma > 0 ){
			demanaCarta = true;
			if (valorTapada > mitja) tapada = false; else tapada = true;
			float aux = (suma/player.aposta)*apostaMinima;
			if (aux > apostaMaxima) novaAposta = apostaMaxima;
			else novaAposta = Math.round(aux);
		}
		else{
			demanaCarta = false;
			tapada = false;
			novaAposta =0;
		}
	}
	
	public void jugadaBanca(boolean demanaCarta, boolean esTres, boolean esQuatre){
		if (player.cartes.size() == 1){
			if (player.cartes.get(0).num == 3) {esTres = true;return;}
			if (player.cartes.get(0).num == 4) {esQuatre = true;return;}
		}
		esTres = false;
		esQuatre = false;
		float suma=0,valorBanca,valorBancaNou=0;
		
		// calcularem el valor de les jugades dels adversaris (la esperança de totes les possibles)
		// i compararem amb la de la nostra jugada i amb la de la que seria si demanes carta.
		// si el balanç es positiu, demano carta, sino em planto.
		
		//retornem al munt la carta tapada (ja ho ha fet el constructor) 
		// si algu passa , es igual perue no te cartes tapades.
		
		
		int contador = 0;
		for (SiMplayer auxPlayer : otherPlayers) if (!auxPlayer.passa) contador++;
		float[] valors = new float[contador];
		int[] apostes = new int[contador];
		contador = 0;
		for (SiMplayer auxPlayer : otherPlayers ){
			calculaBanca(suma,auxPlayer);
			valors[contador] = suma;
			apostes[contador++] = auxPlayer.aposta;
		}
		
		valorBanca = 0;
		for (Carta myCarta : player.cartes){
			valorBanca+=valorCarta(myCarta);
		}
		calculaBanca(valorBancaNou,player);
	
		// fem balanç amb les cartes actuals de la banca
		
		float actual = 0,futur = 0;
		
		for (int f = 0 ; f < contador ; f++)
			if (valorBanca >= valors[f]) actual+=apostes[f];
			else 
				if (valors[f] == 7.5f) actual-=2*apostes[f];
				else actual -=apostes[f];
		
		for (int f = 0 ; f < contador ; f++)
			if (valorBancaNou >= valors[f]) futur+=apostes[f];
			else 
				if (valors[f] == 7.5f) futur-=2*apostes[f];
				else futur -=apostes[f];
		
		if (futur > actual) demanaCarta = true;
		else demanaCarta = false;
	}
}
