
public class Email {

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
	String email;
}
