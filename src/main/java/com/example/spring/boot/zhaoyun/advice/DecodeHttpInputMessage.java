package com.example.spring.boot.zhaoyun.advice;

import com.example.spring.boot.zhaoyun.util.encrypt.Encryptor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * HTTP 请求解密消息
 *
 * @author LiuBing
 * @date 2020/12/9
 */
public class DecodeHttpInputMessage implements HttpInputMessage {

	HttpHeaders headers;

	InputStream body;

	public DecodeHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
		this.headers = inputMessage.getHeaders();
		this.body = inputMessage.getBody();
	}

	DecodeHttpInputMessage decodeWithThe(String secretKeyStr) throws Exception {
		String dataStr = IOUtils.toString(body, "UTF-8");
		byte[] dataBytes = Encryptor.decryptToBytes(dataStr, secretKeyStr);
		this.body = new ByteArrayInputStream(dataBytes);
		return this;
	}

	@Override
	public InputStream getBody() throws IOException {
		return this.body != null ? this.body : StreamUtils.emptyInput();
	}

	@Override
	public HttpHeaders getHeaders() {
		return this.headers;
	}
}