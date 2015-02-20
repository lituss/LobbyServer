package message.games.SiM;

import java.io.Serializable;

import lobbyserver.Player;
import message.CtipusMissatge;
import message.enums.TipusMissatge;

public class SCSiMTorn extends CtipusMissatge implements Serializable{
public Player torn;
	public SCSiMTorn() {
		super(TipusMissatge.SCSiMTorn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -305935342251963255L;
	
}
