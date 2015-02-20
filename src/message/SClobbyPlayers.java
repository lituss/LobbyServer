package message;

import java.io.Serializable;
import java.util.List;

import message.enums.TipusMissatge;

public class SClobbyPlayers extends CtipusMissatge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4878979131654331789L;
	
	public List<String> players ;

	public SClobbyPlayers(){super(TipusMissatge.SClobbyPlayers);}
}
