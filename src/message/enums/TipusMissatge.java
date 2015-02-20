package message.enums;


public enum TipusMissatge { CSlogin (1) , CSconnect (2), CSsign (3), 
	SClogged (4), CSsigned (5), SCrooms (6) , SClobbyPlayers (7) ,
	CSlobbyChat (8) , SClobbyChat (9), CSroomJoin (10) , SCroomGames (11),
	CSgameCreate (12), CSgameJoin (13), CSgameVisit (14), CSgameExit (15),
	CSgameReady (16), CSgameStart (17), SCplayerTurn (18) , CSgamePlay (19),
	SCgamePlay(20), SCroomJocs (21), SCplayer (22) , SCgame (23), SCgameCreated (24),
	SCgameJoined (25);

	int tm;
	TipusMissatge(int p){tm = p;}
	public int showClasse(){return tm;}
}
