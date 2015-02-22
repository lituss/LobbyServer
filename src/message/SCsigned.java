package message;

import java.io.Serializable;

import message.enums.EnumJoin;
import message.enums.TipusMissatge;

public class SCsigned extends CtipusMissatge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8089969656634477545L;
public EnumJoin join;
public SCsigned() { super(TipusMissatge.CSsigned);}
}
