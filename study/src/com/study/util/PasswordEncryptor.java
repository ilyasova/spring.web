package com.study.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class PasswordEncryptor {

	public final static String SALT = "lang";

	public static String getEncryptedPassword(String password) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
		}
		try {
			messageDigest.update((password + SALT).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte raw[] = messageDigest.digest();
		String hash = (new BASE64Encoder()).encode(raw);
		return hash;
	}
}
