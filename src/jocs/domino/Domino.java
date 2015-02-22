package jocs.domino;

import java.util.ArrayList;
import java.util.List;

public class Domino {
	Fitxes fitxesJoc;
	Fitxes.Fitxa primera=null,left=null,right=null;
	List <Dplayer> players;
	Dplayer torn;
	boolean end = false;
	
	public Domino(int tipus){
		fitxesJoc = new Fitxes(tipus);
		players = new ArrayList<>();
	}
	public void posa(Fitxes.Fitxa fitxa, int valor){
		if (primera == null) {
			primera = fitxa;
			left = fitxa;
			right = fitxa;
		}
		else {
			if (valor == left.lvalue){
				if (fitxa.lvalue == valor) fitxa.swap();
				left.lFitxa = fitxa;
				fitxa.rFitxa = left;
				left = fitxa;
			}
			else{
				right.rFitxa = fitxa;
				fitxa.lFitxa = right;
				right = fitxa;
			}
		}
	}
	
	void evaluaTaulell(){
		if (end || tancat()) acabar();
		passaTorn();
		
	}
	
	boolean tancat(){
		for (Dplayer player : players)
			for (Fitxes.Fitxa fitxa : player.myFitxes.fitxes) 
				if (fitxa.lvalue == left.lvalue || fitxa.rvalue == right.rvalue ) return false;
		return true;
	}
	
	void acabar(){
		
	}
	
	void passaTorn(){
		int index = players.indexOf(torn) + 1;
		if (index == players.size()) index = 0;
		torn = players.get(index);
	}
	public void inicia(){ // creem jugadors
		
	}
	public class Dplayer {
		Fitxes myFitxes;
		Dplayer(int nFitxes){
			for (int i = 0 ; i < nFitxes ; i++) myFitxes.fitxes.add(fitxesJoc.getFitxa());
		}
		
		void putFitxa(Fitxes.Fitxa fitxa, int value){
			int index = myFitxes.fitxes.indexOf(fitxa);
			myFitxes.fitxes.remove(index);
			posa (fitxa,value);
			if (myFitxes.fitxes.size() == 0) end = true;
		}
	}

}
