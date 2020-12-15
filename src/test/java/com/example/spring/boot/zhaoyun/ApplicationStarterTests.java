package com.example.spring.boot.zhaoyun;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ApplicationStarterTests {

	@Test
	void contextLoads() throws IOException {
		String a = IOUtils.toString(Base64.decodeBase64("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"), "UTF-8");
		String b = IOUtils.toString(Base64.decodeBase64("eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCJdLCJhdGkiOiI4YmYzZTVlNy00YTcxLTQxODMtYTM4ZC1iYWI2M2ExZmIyMTQiLCJleHAiOjE2MTAxMjAxMTksImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJiNTdkNjFhOC0wMmJlLTRkN2MtOTZkMS0wYzdkNTBlNWM0YTYiLCJjbGllbnRfaWQiOiJjbGllbnRhcHAifQ"), "UTF-8");
		String c = IOUtils.toString(Base64.decodeBase64("jzhd3v2q1u1um7foGsB-VYUa81qdbcTKNVJSCTBkqfU"), "UTF-8");
	}


}
