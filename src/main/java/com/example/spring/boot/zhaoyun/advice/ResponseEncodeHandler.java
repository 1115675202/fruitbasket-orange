package com.example.spring.boot.zhaoyun.advice;

import com.example.spring.boot.zhaoyun.util.encrypt.Encryptor;

import java.util.Objects;

/**
 * 加密响应
 *
 * @author LiuBing
 * @date 2020/12/9
 */
public class ResponseEncodeHandler {

	private String data;

	private String secretKeyStr;

	private String encodedData;

	public ResponseEncodeHandler(String data) {
		this.data = data;
	}

	public ResponseEncodeHandler secretKeyStr(String secretKeyStr) {
		this.secretKeyStr = secretKeyStr;
		return this;
	}

	public ResponseEncodeHandler encodData() throws Exception {
		if(Objects.nonNull(this.data)) {
			encodedData = Encryptor.encryptToString(data, secretKeyStr);
		}
		return this;
	}

	public String getEncodedData() {
		return encodedData;
	}
}
