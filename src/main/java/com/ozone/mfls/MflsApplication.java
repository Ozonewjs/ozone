package com.ozone.mfls;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.ozone.mfls.mapper")
@EnableCaching
public class MflsApplication  implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(MflsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MflsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("=========启动成功后执行=====");
    }

}
