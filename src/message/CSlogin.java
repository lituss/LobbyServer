package message;

import java.io.Serializable;

public class CSlogin extends CtipusMissatge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5567502799190850354L;
public String user;
public String pass;

public CSlogin(){super(TipusMissatge.CSlogin);}
}
