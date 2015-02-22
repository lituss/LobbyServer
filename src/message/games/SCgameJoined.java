package message.games;

import java.io.Serializable;

import message.CtipusMissatge;
import message.enums.TipusMissatge;

public class SCgameJoined extends CtipusMissatge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7608506682830856742L;
	public SCgameJoined(){super(TipusMissatge.SCgameJoined);}

}
