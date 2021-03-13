package com.goaudits.business.util;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public  class EncrypterHelper {
	  Random rand = new Random((new Date()).getTime());

	public  String encrypt(String str) {

		BASE64Encoder encoder = new BASE64Encoder();

		byte[] salt = new byte[8];

		rand.nextBytes(salt);

		return encoder.encode(salt) + encoder.encode(str.getBytes());
	}

	public  String decrypt(String encstr) {

		if (encstr.length() > 12) {

			String cipher = encstr.substring(12);

			BASE64Decoder decoder = new BASE64Decoder();

			try {

				return new String(decoder.decodeBuffer(cipher));

			} catch (Exception e) {

				// throw new InvalidImplementationException(

				// Fail

			}

		}
		return encstr;

	}

}