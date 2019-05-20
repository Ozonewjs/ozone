package com.ozone.mfls.startuprunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = -1)
public class StartupRunner3 implements ApplicationRunner {
    @Override
    public void run (ApplicationArguments applicationArguments) throws Exception{
        System.err.println(">>服务启动执行，执行加载数据等操作<333333");
    }
}
