package message;

import java.io.Serializable;

public class SClobbyChat implements Serializable, Classe {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4068826145532356470L;
	public Classes classe = Classes.SClobbyChat;
	private String texte;
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
}
