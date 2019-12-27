package com.capillary.VisitorMatrix.qa.framework.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil {

	public static String md5Encrypt(String password) throws NoSuchAlgorithmException {
	     final byte[] defaultBytes = password.getBytes();
	        try {
	            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
	            md5MsgDigest.reset();
	            md5MsgDigest.update(defaultBytes);
	            final byte messageDigest[] = md5MsgDigest.digest();
	            final StringBuffer hexString = new StringBuffer();
	            for (final byte element : messageDigest) {
	                final String hex = Integer.toHexString(0xFF & element);
	                if (hex.length() == 1) {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }
	            password = hexString + "";
	        } catch (final NoSuchAlgorithmException nsae) {
	            nsae.printStackTrace();
	        }
	        return password;
	}
	public static String authorizationHeader(String userName, String password) {
		String encodedText = createEncodedText(userName, password);
		return "Basic " + encodedText.toString();
	}

	public static String createEncodedText(final String username, final String password) {
		String pair = username + ":" + password;
		byte[] encodedBytes = Base64.encodeBase64(pair.getBytes());
		return new String(encodedBytes);
	}
}
