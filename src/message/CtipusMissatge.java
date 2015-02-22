package message;

import java.io.Serializable;

import message.enums.TipusMissatge;

public class CtipusMissatge implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3129007534557772973L;
	public TipusMissatge tipusM;
	public CtipusMissatge(TipusMissatge tm){
		tipusM = tm;
	}

}
