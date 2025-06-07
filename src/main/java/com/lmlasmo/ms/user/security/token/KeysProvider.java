package com.lmlasmo.ms.user.security.token;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public abstract class KeysProvider {

	private static KeyPair refleshKeys;
	private static KeyPair accessKeys;

	static {
		try {
			updateKeys();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static void updateKeys() throws NoSuchAlgorithmException {
		refleshKeys = generateKeyPair();
		accessKeys = generateKeyPair();
	}

	private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		return KeyPairGenerator.getInstance("RSA").generateKeyPair();
	}

	public static KeyPair getRefleshKeys() {
		return refleshKeys;
	}

	public static KeyPair getAccessKeys() {
		return accessKeys;
	}

}
