package com.goaudits.business.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.goaudits.business.config.DatabaseConfig;

import io.jsonwebtoken.Jwts;

@SuppressWarnings("restriction")
public class Utils {

	private static String jwtSecret = "goaconsoleSecretKey";

	private static int workload = 6;

	public static byte[] Base64ToBytes(String imageString) throws IOException {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] decodedBytes = decoder.decode(imageString);
		return decodedBytes;
	}

	public static byte[] scale(byte[] fileData, int width, int height) {
		ByteArrayInputStream in = new ByteArrayInputStream(fileData);
		try {
			BufferedImage img = ImageIO.read(in);
			if (height == 0) {
				height = (width * img.getHeight()) / img.getWidth();
			}
			if (width == 0) {
				width = (height * img.getWidth()) / img.getHeight();
			}
			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			ImageIO.write(imageBuff, "jpg", buffer);

			return buffer.toByteArray();
		} catch (IOException e) {
			return fileData;
		}
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

	public static String hashMyPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);
		return (hashed_password);
	}

	public static String ConvertToBase64(byte[] imgBytes) {
		try {
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(imgBytes);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	public static String formatDateMysqltoUser(String mysqldate) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date d = format.parse(mysqldate);
		DateFormat df = new SimpleDateFormat("dd, MMM yyyy");
		String newDate = df.format(d);
		return newDate;
	}

	public static Properties getProperties() {

		DatabaseConfig dbConfig = new DatabaseConfig();
		Properties prop = null;
		try {
			prop = dbConfig.getProperties();
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		return prop;
	}

	public static String splitJoinStringsAtIndex(String sourceStr, String insertText, int indexPos,
			String delimiterStr) {
		indexPos = 5;
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

	public static String getClearText(String encrypted) {

		String plaintext = "";

		if (encrypted != null) {

			// Get the passphrase, salt, IV and msg

			String data[] = encrypted.split(":");

			String passphrase = "POGDKMjLVUcgtyRT";

			String salt_hex = data[0];

			String iv_hex = data[1];

			String msg64 = data[2];

			String jskey_hex = data[3];

			byte[] jskey = hexStringToByteArray(jskey_hex);

			byte[] iv = hexStringToByteArray(iv_hex);

			byte[] salt = hexStringToByteArray(salt_hex);

//	        BASE64Decoder decoder = new BASE64Decoder();

			Base64.Decoder decoder = Base64.getDecoder();

			byte[] msg = decoder.decode(msg64);

			try {

				SecretKey key = new SecretKeySpec(jskey, "AES");

				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

				cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

				plaintext = new String(cipher.doFinal(msg), "UTF-8");

			} catch (Exception e) {

				e.printStackTrace();

			}

//			System.out.println("in filter, decrypted: " + plaintext);

		}

		return plaintext;

	}

	/* s must be an even-length string. */

	public static byte[] hexStringToByteArray(String s) {

		int len = s.length();

		byte[] data = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {

			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));

		}

		return data;

	}

	public static String getGuid(String token) {
		EncrypterHelper EncrypterHelper = new EncrypterHelper();
		token = EncrypterHelper.decrypt(token);

		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getId();
	}

	public static String getUid(String token) {
		EncrypterHelper EncrypterHelper = new EncrypterHelper();
		token = EncrypterHelper.decrypt(token);
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getIssuer();
	}

}
