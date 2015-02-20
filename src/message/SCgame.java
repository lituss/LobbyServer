package message;

import java.io.Serializable;

import message.enums.EnumEstatJoc;
import message.enums.TipusMissatge;

public class SCgame extends CtipusMissatge implements Serializable {

	public int id;
	public EnumEstatJoc estat;
	public int minPlayers,maxPlayers;
	public int players;
	public SCgame() {
		super(TipusMissatge.SCgame);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1201639008799388134L;

}
