package message;

public enum Classes { CSlogin (1) , CSconnect (2), CSsign (3), SClogged (4), CSsigned (5), SCrooms (6) , SClobbyPlayers (7) ,
	CSlobbyChat (8) , SClobbyChat (9);

	int classe;
	Classes(int p){classe = p;}
	public int showClasse(){return classe;}
	
}
