package com.example.spring.boot.zhaoyun.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * 可重复读的请求对象
 *
 * @author LiuBing
 * @date 2020/12/2
 */
public class MultiReadHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;

	public MultiReadHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		initBody(request.getInputStream());
	}

	private void initBody(InputStream input) throws IOException {
		body = IOUtils.toByteArray(input);
	}

	@Override
	public ServletInputStream getInputStream() {
		return new ServletInputStream() {
			private ByteArrayInputStream input = new ByteArrayInputStream(body);

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
			}

			@Override
			public int read() throws IOException {
				return input.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}
}
