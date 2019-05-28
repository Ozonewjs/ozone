package com.ozone.mfls;

import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TestConfig
 * @Author Ozone
 * @Description 测试spring容器启动初始化
 * @Date 2019/5/28 15:12
 * @Version 1.0
 **/
@Configuration
public class TestConfig {
    public TestConfig() {
        System.out.println("spring容器启动初始化。。。");
    }
}
