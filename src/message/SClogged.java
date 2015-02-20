package message;

import java.io.Serializable;

import message.enums.EnumJoin;
import message.enums.TipusMissatge;

public class SClogged extends CtipusMissatge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1810355011895451898L;
public EnumJoin login;
public long token;
public SClogged(){super(TipusMissatge.SClogged);}
}
