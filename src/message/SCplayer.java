package message;

import java.io.Serializable;

import message.enums.EnumEstats;
import message.enums.EnumTipusPlayer;
import message.enums.TipusMissatge;

public class SCplayer extends CtipusMissatge implements Serializable  {
	public String alias;
	public long token;
	public EnumEstats estat;
	public EnumTipusPlayer tipus;
	
	public SCplayer() {
		super(TipusMissatge.SCplayer);
		// TODO Auto-generated constructor stub
	}

}
