package message.games;

import java.io.Serializable;

import message.CtipusMissatge;
import message.enums.TipusMissatge;

public class CSgameJoin extends CtipusMissatge implements Serializable {
public int id;
	/**
	 * 
	 */
	public CSgameJoin(){super(TipusMissatge.CSgameJoin);}
	private static final long serialVersionUID = 383476845789834879L;

}
