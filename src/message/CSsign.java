package message;

import java.io.Serializable;

import message.enums.TipusMissatge;



public class CSsign extends CtipusMissatge  implements Serializable {


	private static final long serialVersionUID = 5722379616537993309L;
	public String user;
	public String pass;
	
	public CSsign(){
	super(TipusMissatge.CSsign);
	}
}
