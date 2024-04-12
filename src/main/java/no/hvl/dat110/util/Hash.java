package no.hvl.dat110.util;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import javax.security.auth.login.CredentialException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	
	public static BigInteger hashOf(String entity) {
		/*
		Task: Hash a given string using MD5 and return the result as a BigInteger.
		we use MD5 with 128 bits digest
		compute the hash of the input 'entity'
		convert the hash into hex format
		convert the hex into BigInteger
		return the BigInteger
		 */

		BigInteger hashint = null;

		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashedBytes = md.digest(entity.getBytes());
			StringBuilder hexString = new StringBuilder();
			for(byte b : hashedBytes) {
				String hex = Integer.toHexString(0xff & b);
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			hashint = new BigInteger(hexString.toString(), 16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		return hashint;
	}


	public static BigInteger addressSize() {
		/*
		Task: compute the address size of MD5
	 	* compute the number of bits = bitSize()
	 	* compute the address size = 2 ^ number of bits
	 	* return the address size
		*/

		BigInteger bigInteger = BigInteger.valueOf(2);
		return bigInteger.pow(128);
	}
	
	public static int bitSize() {
		// find the digest length

		int digestlen = 0;
		MessageDigest md = null;

		try {
			 md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		digestlen = md.getDigestLength();
		return digestlen*8; //konverterer fra bytes til bits
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
