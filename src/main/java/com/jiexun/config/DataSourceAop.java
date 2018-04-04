package com.jiexun.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by dengtianguang on 2018/4/4.
 */

@Aspect
@Component
public class DataSourceAop {

    @Before("execution(* com.jiexun.mapper.*.find*(..)) " +
            "|| execution(* com.jiexun.mapper.*.get*(..))" +
             "|| execution(* com.jiexun.mapper.*.query*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        System.out.println("dataSource切换到：Read");
    }

    @Before("execution(* com.jiexun.mapper.*.insert*(..)) " +
            "|| execution(* com.jiexun.mapper.*.update*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        System.out.println("dataSource切换到：write");
    }
}
