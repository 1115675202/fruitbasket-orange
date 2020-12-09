package com.example.spring.boot.zhaoyun.util.encrypt;

import org.apache.commons.codec.binary.Base64;

/**
 * 加解密工具类
 *
 * @author LiuBing
 * @date 2020/12/7
 */
public class Encryptor {

	/**
	 * 生成密钥
	 *
	 * @return 密钥
	 * @throws Exception
	 */
	public static String initKey() throws Exception {
		byte[] keyBytes = AESCoder.initKey();
		return Base64.encodeBase64String(keyBytes);
	}

	/**
	 * 加密
	 *
	 * @param dataBytes 待加密数据
	 * @param keyStr    密钥
	 * @return 加密数据
	 * @throws Exception
	 */
	public static String encryptToString(byte[] dataBytes, String keyStr) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		byte[] encryptedBytes = encrypt(dataBytes, keyBytes);
		return Base64.encodeBase64String(encryptedBytes);
	}

	/**
	 * 加密
	 *
	 * @param dataStr 待加密数据
	 * @param keyStr  密钥
	 * @return 加密数据
	 * @throws Exception
	 */
	public static String encryptToString(String dataStr, String keyStr) throws Exception {
		byte[] dataBytes = Base64.decodeBase64(dataStr);
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		dataBytes = encrypt(dataBytes, keyBytes);
		return Base64.encodeBase64String(dataBytes);
	}

	/**
	 * 加密
	 *
	 * @param dataBytes 待加密数据
	 * @param keyStr    密钥
	 * @return 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptToBytes(byte[] dataBytes, String keyStr) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		return encrypt(dataBytes, keyBytes);
	}

	/**
	 * 加密
	 *
	 * @param dataStr 待加密数据
	 * @param keyStr  密钥
	 * @return 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptToBytes(String dataStr, String keyStr) throws Exception {
		byte[] dataBytes = Base64.decodeBase64(dataStr);
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		return encrypt(dataBytes, keyBytes);
	}

	/**
	 * 解密
	 *
	 * @param dataStr 待解密数据
	 * @param keyStr  秘钥
	 * @return 解密数据
	 * @throws Exception
	 */
	public static String decryptToString(String dataStr, String keyStr) throws Exception {
		byte[] dataBytes = Base64.decodeBase64(dataStr);
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		dataBytes = decrypt(dataBytes, keyBytes);
		return Base64.encodeBase64String(dataBytes);
	}

	/**
	 * 解密
	 *
	 * @param dataBytes 待解密数据
	 * @param keyStr    秘钥
	 * @return 解密数据
	 * @throws Exception
	 */
	public static String decryptToString(byte[] dataBytes, String keyStr) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		dataBytes = decrypt(dataBytes, keyBytes);
		return Base64.encodeBase64String(dataBytes);
	}

	/**
	 * 解密
	 *
	 * @param dataStr 待解密数据
	 * @param keyStr  秘钥
	 * @return 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptToBytes(String dataStr, String keyStr) throws Exception {
		byte[] dataBytes = Base64.decodeBase64(dataStr);
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		return decrypt(dataBytes, keyBytes);
	}

	/**
	 * 解密
	 *
	 * @param dataBytes 待解密数据
	 * @param keyStr    秘钥
	 * @return 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptToBytes(byte[] dataBytes, String keyStr) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(keyStr);
		return decrypt(dataBytes, keyBytes);
	}

	private static byte[] encrypt(byte[] dataBytes, byte[] keyBytes) throws Exception {
		return AESCoder.encrypt(dataBytes, keyBytes);
	}

	private static byte[] decrypt(byte[] dataBytes, byte[] keyBytes) throws Exception {
		return AESCoder.decrypt(dataBytes, keyBytes);
	}

	private Encryptor() {
	}
}
