package message;

public enum Classes { CSlogin (1) , CSconnect (2), CSsign (3), CSlogged (4), CSsigned (5), Rooms (6) , LobbyPlayers (7) ;

	int classe;
	Classes(int p){classe = p;}
	int showClasse(){return classe;}
	
}
