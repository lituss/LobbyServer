package jocs;

import java.util.ArrayList;
import java.util.List;

public class Cartes40 implements Cloneable{
	protected List <Carta> cartes = new ArrayList<Carta>(40);
	protected List <Carta> munt = new ArrayList<Carta>();

	public Cartes40(){
		int contador = 0;
		for ( Pal auxPal : Pal.values())
			for (int f = 1 ; f < 13 ; f++) cartes.add (new Carta(auxPal,f,++contador));
			munt.addAll(cartes);
		}
	public Carta get(){
		if (munt.size() == 0) return null;
		int index = (int) Math.round(Math.random()*munt.size());
		return get(index);
	}
	public Carta get(int index){
		return (munt.remove(index));
	}
	public void reset(){
		munt.clear();
		munt.addAll(cartes);
	}
	public int size(){ return munt.size();}
	
	@Override
	protected Cartes40 clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Cartes40 auxCartes = new Cartes40();
		auxCartes.cartes.addAll(cartes);
		auxCartes.munt.addAll(cartes);
		return auxCartes;
	}
}
