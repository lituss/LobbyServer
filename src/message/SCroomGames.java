package message;

import java.io.Serializable;
import java.util.List;

import message.enums.EnumTipusSales;
import message.enums.TipusMissatge;

public class SCroomGames extends CtipusMissatge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7172705471416439223L;
	public List <SCplayer> players;
	public List <SCgame> jocs;
	public String nom;
	public EnumTipusSales tipusSala;
	public int maxJugadors;
	public SCroomGames(){ super(TipusMissatge.SCroomGames);}
}
