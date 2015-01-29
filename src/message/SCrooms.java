package message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SCrooms implements Serializable,Classe{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3839207309625723678L;
	Classes classe = Classes.Rooms;
	
	public List <SCroom> rooms = new ArrayList<SCroom>();
	
	public class SCroom{
		public String nom;
		public EnumTipusSales tipusSala;
		public int maxJugadors;
		public int numJugadors = 0;
	}

}
