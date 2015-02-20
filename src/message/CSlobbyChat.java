package message;

import java.io.Serializable;

import message.enums.TipusMissatge;

public class CSlobbyChat extends CtipusMissatge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1812338742162654737L;
	
	private String texte;
	
	public CSlobbyChat(){super(TipusMissatge.CSlobbyChat);}
	public String getTexte() {
		return texte;
	}
}
