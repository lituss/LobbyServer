package message;

import java.io.Serializable;

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
