package jocs;


public class Carta {
	Pal pal;
	int num;
	int id;
	boolean tapada;

	public Carta( Pal pal, int num, int id){
		this.pal = pal;
		this.num = num;
		this.id = id;
		tapada = true;
	}
}
