package message;

import java.io.Serializable;

import message.enums.TipusMissatge;

public class SClobbyChat extends CtipusMissatge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4068826145532356470L;
	
	private String texte;
	public SClobbyChat(){super(TipusMissatge.SClobbyChat);}
	
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
}
