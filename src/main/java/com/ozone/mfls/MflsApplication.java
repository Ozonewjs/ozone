package com.ozone.mfls;



import org.mybatis.spring.annotation.MapperScan;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;



/**
 * @Author Ozone
 * @Description 入口函数
 * @Date 2019/5/28 11:04
 **/
@SpringBootApplication
@MapperScan("com.ozone.mfls.mapper")
@EnableCaching
@EnableScheduling
public class MflsApplication  implements CommandLineRunner {
    private Logger logger = Logger.getLogger(MflsApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MflsApplication.class, args);
    }
    @Override
    public void run(String... args) {
        logger.info("=========启动成功后执行=====");
    }

}
