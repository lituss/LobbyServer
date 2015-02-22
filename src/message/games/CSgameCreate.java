package message.games;

import java.io.Serializable;

import message.CtipusMissatge;
import message.enums.TipusMissatge;

public class CSgameCreate extends CtipusMissatge implements Serializable{

	/**
	 * 
	 */
	CSgameCreate(){super(TipusMissatge.CSgameCreate);}
	private static final long serialVersionUID = 2508396713490380635L;

}
