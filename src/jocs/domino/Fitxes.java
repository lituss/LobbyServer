package jocs.domino;

import java.util.ArrayList;
import java.util.List;

public class Fitxes {
List <Fitxa> fitxes = new ArrayList<>();

public Fitxes(){}

public Fitxes (int num){
	for (int i = 0 ; i < num ; i++)
		for (int j = 0 ; j <= i ; j++){
			Fitxa fitxa = new Fitxa();
			fitxa.id = i*num + j;
			fitxa.lvalue = i;
			fitxa.rvalue = j;
			fitxes.add(fitxa);
		}
}
Fitxa getFitxa(){
	synchronized(this){
		Fitxa fitxa = null;
		int index = (int) Math.round(Math.random()*fitxes.size());
		fitxa = fitxes.remove(index);
		return fitxa;
	}
}
public void print(){
	for (Fitxa fitxa : fitxes) System.out.println(fitxa.id+" :: "+fitxa.lvalue+" : "+fitxa.rvalue);
	
}

	public class Fitxa {
		int id;
		int lvalue,rvalue;
		Fitxa lFitxa=null,rFitxa=null;
		
		public void swap(){
			int aux = lvalue;
			lvalue = rvalue;
			rvalue = aux;
		}
	}
}
