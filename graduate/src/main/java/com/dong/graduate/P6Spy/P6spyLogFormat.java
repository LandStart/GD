package com.dong.graduate.P6Spy;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6spyLogFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int i, String s, long l, String s1, String s2, String sql, String url) {
        return StringUtils.isNotEmpty(sql) ? new StringBuilder().append(" Execute SQL：=====>").append(sql.replaceAll("[\\s]+", StringPool.SPACE)).toString() : null;
    }
}
