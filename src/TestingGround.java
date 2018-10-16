
public class TestingGround {


	public static void main(String[] args) {
		Member memberToRegister   = new Member( "Niv", "Noiman", "Rishon Lezzion, Maccabi Zahir, 6", "0524011331" , "ziv@gmail.com" );
		Member employeeToRegister = new Employee( memberToRegister );
		if( employeeToRegister.register( "123456" ) != null ){
			System.out.println("Employee Successfully Registered ");
		} else{
			System.out.println("Register Failed: Member Already Registered");
		}
	}
		
		
}
