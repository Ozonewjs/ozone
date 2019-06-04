package com.ozone.mfls.configer;



import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLockConfiguration
 * @Author Ozone
 * @Description RedisLockRegistry实现Redis分布式锁
 * @Date 2019/5/27 15:36
 * @Version 1.0
 **/
@Configuration
public class RedisLockConfiguration {
//    private Logger logger = Logger.getLogger(RedisLockConfiguration.class);
//    public void testConfiguration(){
//        logger.info("spring容器启动初始化。。。");
//    }
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "spring-cloud", 5000L);
    }
//    @Bean
//    public RedisLockRegistry redisLockRegistry2(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisLockRegistry(redisConnectionFactory, "spring-fliter", 5000L);
//    }

}
