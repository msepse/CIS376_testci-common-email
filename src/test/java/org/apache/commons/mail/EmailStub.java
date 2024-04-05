//Name: Michael P. Sepsey
//Date Created: 3/23/24
//Date Modified: 3/26/24

package org.apache.commons.mail;

public class EmailStub extends Email {

	//sub-class of the Email class intended for
	//testing certain Email class methods
	
	//setMsg method of Email class must be overridden
	//since it was defined as an abstract method
	@Override
	public Email setMsg(String msg) throws EmailException {
		// TODO Auto-generated method stub
		return null;
	}

}
