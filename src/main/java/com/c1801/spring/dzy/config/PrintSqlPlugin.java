package com.c1801.spring.dzy.config;


import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * user：少
 * dateTime: 2019/8/5 9:08
 */
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
})
public class PrintSqlPlugin implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        
        //拦截目标
        RoutingStatementHandler target = (RoutingStatementHandler) invocation.getTarget();
        //拦截SQL + 参数
        BoundSql boundSql = target.getBoundSql();
        String sql = boundSql.getSql();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
        String topLine = "------------------------------------------------------------------------------------------------------------------------------";
        System.out.printf("\n\n" + topLine + "\n" + dateFormat.format(new Date(System.currentTimeMillis())) + "\n" + "SQL:\n%s \n%s\n", sql,"参数\t" + boundSql.getParameterObject() + "\n" + topLine + "\n\n");

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("properties："+properties);
    }
}
