package message.games.SiM;

import java.io.Serializable;

import message.CtipusMissatge;
import message.enums.TipusMissatge;

public class CSSiMJugada extends CtipusMissatge implements Serializable {
                 public boolean novaCarta,tapada,passa;
                 public int aposta;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4934029801547175047L;

	public CSSiMJugada() {
		super(TipusMissatge.CSSiMJugada);
		// TODO Auto-generated constructor stub
	}

}
