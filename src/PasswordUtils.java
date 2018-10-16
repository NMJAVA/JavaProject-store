import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	public static String getSalt() {
		return new String("EqdmPh53c9x33EygXpTpcoJvc4VXLK");
	}
	public static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		} finally {
			spec.clearPassword();
		}
	}
	public static String generateSecurePassword(String password, String salt) {
		String returnValue = null;
		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

		returnValue = Base64.getEncoder().encodeToString(securePassword);

		return returnValue;
	}

	public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt){
		try {
			// Generate New secure password with the same salt
			String newSecurePassword = generateSecurePassword(providedPassword, salt);
	
			// Check if two passwords are equal
			boolean returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
			return returnValue;
		}catch( Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}