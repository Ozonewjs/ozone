package com.ozone.mfls.configer;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisLockConfiguration
 * @Author Ozone
 * @Description Redis分布式锁
 * @Date 2019/5/27 15:36
 * @Version 1.0
 **/
@Configuration
public class RedisLockConfiguration {
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "spring-cloud", 5000L);
    }

}
