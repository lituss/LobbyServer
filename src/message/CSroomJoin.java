package message;

import java.io.Serializable;

public class CSroomJoin extends CtipusMissatge implements Serializable{
	private static final long serialVersionUID = -5383728769342092191L;
	private int id;
	
	public int getId(){
		return (id);
	}
	public void setId(int id){
		this.id = id;
	}

/**
	 * 
	 */



public  CSroomJoin(){ super(TipusMissatge.CSroomJoin);}
}