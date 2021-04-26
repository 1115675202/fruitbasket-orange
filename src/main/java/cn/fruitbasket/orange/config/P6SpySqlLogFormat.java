package cn.fruitbasket.orange.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;

/**
 * 使用 P6Spy 打印完整 SQL 语句，方便开发调试
 * 这里设置日志监控格式
 *
 * @author LiuBing
 * @date 2020/12/18
 */
public class P6SpySqlLogFormat implements MessageFormattingStrategy {

    /**
     * @param connectionId 连接ID
     * @param now          执行时间
     * @param elapsed      花费时间（毫秒）
     * @param category     执行类型 error,info,batch,debug,statement,commit,rollback,result,resultset.
     * @param prepared     预提交SQL
     * @param sql          待参SQL
     * @param url          数据库连接地址
     * @return 格式化后的日志
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
                                String prepared, String sql, String url) {
        return String.format("连接ID：%s-%s ， 花费时间(ms): %s", category, connectionId, elapsed) +
                FormatStyle.BASIC.getFormatter().format(sql);
    }
}
