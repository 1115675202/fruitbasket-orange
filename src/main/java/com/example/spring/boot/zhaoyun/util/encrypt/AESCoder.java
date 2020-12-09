package com.example.spring.boot.zhaoyun.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * AES 加密
 *
 * @author LiuBing
 * @date 2020/12/7
 */
class AESCoder {

	/**
	 * 密钥长度，AES 要求密钥长度为128位、192位或256位
	 */
	private static final int KEY_LENGTH = 256;

	/**
	 * 密钥算法
	 */
	public static final String KEY_ALGORITHM = "AES";

	/**
	 * 加密/解密算法 / 工作模式 / 填充方式
	 */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 生成密钥
	 *
	 * @return 二进制密钥
	 * @throws Exception
	 */
	public static byte[] initKey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(KEY_LENGTH);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * 加密
	 *
	 * @param data     待加密数据
	 * @param keyBytes 密钥
	 * @return 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] keyBytes) throws Exception {
		Key key = toKey(keyBytes);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 *
	 * @param data     待解密数据
	 * @param keyBytes 密钥
	 * @return 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] keyBytes) throws Exception {
		Key key = toKey(keyBytes);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * 转换密钥
	 *
	 * @param keyBytes 二进制密钥
	 * @return Key 密钥
	 */
	private static Key toKey(byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, KEY_ALGORITHM);
	}

	private AESCoder() {
	}
}
