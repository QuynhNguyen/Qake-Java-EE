package helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneralUtils {

public static String md5Hashing(String value){
		
		try {
			//Initiate Java MD5 instance
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] byteArray = value.getBytes();
			digest.update(byteArray, 0, byteArray.length);
			BigInteger bigInt = new BigInteger(1, digest.digest());
			//Format bigInt into a string for storage
			return String.format("%1$032X", bigInt);
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm");
			return null;
		}
	}
	
}
