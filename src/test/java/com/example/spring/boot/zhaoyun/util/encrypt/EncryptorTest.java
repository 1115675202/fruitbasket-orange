package com.example.spring.boot.zhaoyun.util.encrypt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncryptorTest {

	String inputStr = "{\"code\":\"00000\",\"message\":\"处理成功\",\"data\":{\"localDateTime\":\"2020-12-09 09:33:43\",\"localDate\":\"2020-12-09\",\"localTime\":\"09:33:43\",\"str\":\"中文\"}}";

	@Test
	void decrypt() throws Exception {
		byte[] inputData = inputStr.getBytes();
		System.err.println("原文:\t" + inputStr);
//		 初始化密钥
		String keyStr = Encryptor.initKey();
		System.err.println("密钥:\t" + keyStr);
//		 加密
		String encryptedStr = Encryptor.encryptToString(inputData, keyStr);
		System.err.println("加密后:\t" + encryptedStr);
//		 解密


		encryptedStr = "lIIhEfb2Lz6v+nqjpeMQ8Y3intxx2axh+yHOZsqE5r67WpHFBmtsH5W29Vr1dM/7+d4oknlmGWL6ODp2183K5Z6ZyR9YEBxVG3d49oyt8iE=";
		keyStr = "WW7Inwxq1rPPRMFSdJbD/3h270Qd5XrJmETeboDSy8g=";
		byte[] outputData = Encryptor.decryptToBytes(encryptedStr, keyStr);
		String outputStr = new String(outputData);
		System.err.println("解密后:\t" + outputStr);
//		 校验
		assertEquals(inputStr, outputStr);
	}

	@Test
	void encrypt() throws Exception {
		byte[] inputData = inputStr.getBytes();
		System.err.println("原文:\t" + inputStr);
//		 初始化密钥
		String keyStr = Encryptor.initKey();
		System.err.println("密钥:\t" + keyStr);
//		 加密
		System.err.println("加密后:\t" + Encryptor.encryptToString(inputData, keyStr));
	}

	@Test
	void initKey() throws Exception {
		String keyStr = Encryptor.initKey();
		System.err.println("密钥:\t" + keyStr);
	}
}