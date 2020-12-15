package com.example.spring.boot.zhaoyun;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.SingleLineFormat;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;

/**
 * @author LiuBing
 * @date 2020/12/15
 */
public class P6LogFormat extends SingleLineFormat {
	private static BasicFormatterImpl formatter = new BasicFormatterImpl();
	private int i = 0;

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
		if (i == 0) {
			i++;
			return now + "|" + "" + elapsed + "|"  + "" + category + "|connection " + "" + connectionId + "|" + "\nAfter Prepared SQL:" +
					formatter.format(P6Util.singleLine(prepared)) + "\nBefore Prepared SQL:" + formatter.format(P6Util.singleLine(sql));
		} else {
			i =  0;
			return now + "|" + category + "|" + "Time Consumin:" + elapsed + "ms";
		}
	}
}