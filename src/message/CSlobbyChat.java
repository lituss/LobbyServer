package message;

import java.io.Serializable;

public class CSlobbyChat implements Serializable, Classe {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1812338742162654737L;
	public Classes classe = Classes.CSlobbyChat;
	private String texte;
	
	public String getTexte() {
		return texte;
	}
}
