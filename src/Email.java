
public class Email {
	
	boolean getEmail(String email) {
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
