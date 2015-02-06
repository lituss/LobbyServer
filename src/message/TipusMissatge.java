package message;


public enum TipusMissatge { CSlogin (1) , CSconnect (2), CSsign (3), SClogged (4), CSsigned (5), SCrooms (6) , SClobbyPlayers (7) ,
	CSlobbyChat (8) , SClobbyChat (9);

	int tm;
	TipusMissatge(int p){tm = p;}
	public int showClasse(){return tm;}
}
