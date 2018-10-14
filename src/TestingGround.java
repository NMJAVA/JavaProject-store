
public class TestingGround {

	public static void main(String[] args) {
		String test1="Hello";
		String test2="He23asd";
		String test3="HY7&*RF";
		
		
		for (char c : test1.toCharArray()) {
		    if (!Character.isLetterOrDigit(c)) {
		    	System.out.print(c);
		        break;
		    }
		}
		
		
		for (char c : test2.toCharArray()) {
		    if (!Character.isLetterOrDigit(c)) {
		       System.out.print(c);
		        break;
		    }
		}
		
		
		for (char c : test3.toCharArray()) {
		    if (!Character.isLetterOrDigit(c)) {
		    	System.out.print(c);
		        break;
		    }
		}
		
		
		
		
	}
}
