package message.games.SiM;

import java.io.Serializable;
import java.util.List;

import jocs.SiM.SiMplayer;
import message.CtipusMissatge;
import message.enums.TipusMissatge;

public class SCSiMPlayers extends CtipusMissatge implements Serializable {
	public List<SiMplayer> SiMplayers;
	public SiMplayer banca;
	public SCSiMPlayers() {
		super(TipusMissatge.SCSiMPlayers);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -924636009170939071L;

}
