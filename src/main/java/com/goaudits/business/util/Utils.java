package com.goaudits.business.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class Utils {

	public static byte[] Base64ToBytes(String imageString) throws IOException {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(imageString);
		return decodedBytes;
	}

	@SuppressWarnings("static-access")
	public static UUID generateUID() {
		UUID uid = new UUID(4242L, 4242L);
		return uid.randomUUID();
	}

	public static String generateToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

//	public static String hashMyPassword(String password_plaintext) {
//		String salt = BCrypt.gensalt(workload);
//		String hashed_password = BCrypt.hashpw(password_plaintext, salt);
//		return (hashed_password);
//	}

	public static String ConvertToBase64(byte[] imgBytes) {
		try {
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(imgBytes);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	public static String splitJoinStringsAtIndex(String sourceStr, String insertText, int indexPos,
			String delimiterStr) {

		// split the sourceStr into array
		String splitArr[] = sourceStr.split(delimiterStr);

		ArrayList<String> myList = new ArrayList<>(Arrays.asList(splitArr));
		myList.add(indexPos, insertText);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < myList.size(); i++) {
			sb.append(myList.get(i));
			if (i < myList.size() - 1) {
				sb.append("/");
			}
		}

		// System.out.println(sb.toString());

		return sb.toString();
	}

	public static String removeJoinStringsAtIndex(String sourceStr, int indexPos, String delimiterStr) {

		// split the sourceStr into array
		String splitArr[] = sourceStr.split(delimiterStr);

		ArrayList<String> myList = new ArrayList<>(Arrays.asList(splitArr));
//	    myList.add(indexPos, insertText);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < myList.size(); i++) {
			if (i > indexPos) {
				if (i < myList.size() - 1) {
					sb.append(myList.get(i));
					sb.append("/");
				} else {
					sb.append((myList.get(i)).split("\\.")[0]);
				}
			}
		}

		// System.out.println(sb.toString());

		return sb.toString();
	}



}
