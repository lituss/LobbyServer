package message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import message.enums.EnumTipusSales;
import message.enums.TipusMissatge;

public class SCrooms extends CtipusMissatge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3839207309625723678L;
	
	public List <SCroom> rooms = new ArrayList<SCroom>();
	
	public class SCroom{
		public String nom;
		public EnumTipusSales tipusSala;
		public int maxJugadors;
		public int numJugadors = 0;
		public int id;
	}
	public SCrooms(){
		super(TipusMissatge.SCrooms);
	}

}
