package com.ozone.mfls.configer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozone.mfls.server.RedisService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.time.Duration;


/**
 * @ClassName RedisLockConfiguration
 * @Author Ozone
 * @Description redis配置类
 * @Date 2019/5/27 15:36
 * @Version 1.0
 **/
@Configuration
@EnableCaching
public class RedisLockConfiguration {
    /**
     * @Author Ozone
     * @Description 选择redis作为默认缓存工具
     * @Date 2019/5/31 10:25
     * @Param [redisConnectionFactory]
     * @return org.springframework.cache.CacheManager
     **/
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 设置缓存有效期一小时
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }
    /**
     * @Author Ozone
     * @Description RedisLockRegistry实现Redis分布式锁
     * @Date 2019/6/4 14:46
     * @Param [redisConnectionFactory]
     * @return org.springframework.integration.redis.util.RedisLockRegistry
     **/
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "spring-cloud", 5000L);
    }
    /**
     * @Author Ozone
     * @Description RedisTemplate相关配置
     * @Date 2019/5/31 10:25
     * @Param [factory]
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
     **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> jacksonSerial = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSerial.setObjectMapper(om);

        // 值采用json序列化
        template.setValueSerializer(new StringRedisSerializer());
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        /**   template.setHashValueSerializer(jacksonSerial);**/
        template.setHashValueSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
    /**
     * @Author Ozone
     * @Description 对hash类型的数据操作
     * @Date 2019/5/31 10:25
     * @Param [redisTemplate]
     * @return org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,java.lang.Object>
     **/
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * @Author Ozone
     * @Description 对redis字符串类型数据操作
     * @Date 2019/5/31 10:26
     * @Param [redisTemplate]
     * @return org.springframework.data.redis.core.ValueOperations<java.lang.String,java.lang.Object>
     **/
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * @Author Ozone
     * @Description 对链表类型的数据操作
     * @Date 2019/5/31 10:26
     * @Param [redisTemplate]
     * @return org.springframework.data.redis.core.ListOperations<java.lang.String,java.lang.Object>
     **/
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * @Author Ozone
     * @Description 对无序集合类型的数据操作
     * @Date 2019/5/31 10:26
     * @Param [redisTemplate]
     * @return org.springframework.data.redis.core.SetOperations<java.lang.String,java.lang.Object>
     **/
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * @Author Ozone
     * @Description 对有序集合类型的数据操作
     * @Date 2019/5/31 10:27
     * @Param [redisTemplate]
     * @return org.springframework.data.redis.core.ZSetOperations<java.lang.String,java.lang.Object>
     **/
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
    /**
     * @Author Ozone
     * @Description RedisService
     * @Date 2019/5/31 13:49
     * @Param [redisTemplate]
     * @return com.scorpios.tokenauthentication.service.RedisService
     **/
    @Bean
    public RedisService getRedisService(RedisTemplate<String, Object> redisTemplate){
        return new RedisService(redisTemplate);
    }
}
