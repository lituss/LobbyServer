package message.games;

import java.io.Serializable;

import message.CtipusMissatge;
import message.enums.TipusMissatge;

public class SCgameCreated extends CtipusMissatge implements Serializable {
public int id;
	/**
	 * 
	 */
	public SCgameCreated(){super(TipusMissatge.SCgameCreated);}
	private static final long serialVersionUID = 4964481319628495140L;

}
