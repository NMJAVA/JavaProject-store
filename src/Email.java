
public class Email {
//make sure that email is ok
	String email;

	public Email(String email) {
		this.email = email;
	}

	/**
	 * check if email is Valid
	 * @param email
	 * @return true on valid | false on failed
	 */
	boolean isValid(String email) {
		int iOfAt=email.indexOf('@');
			if((iOfAt)>0)
				if(iOfAt<(email.length()-3))
				{
					this.email=email;
					return true;
				}
			return false;
	}
	
	public String toString(){
		return email;
	}
}
