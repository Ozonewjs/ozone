package com.ozone.mfls.controller;

import com.ozone.mfls.annotation.UserAuthenticate;
import com.ozone.mfls.annotation.UserId;
import com.ozone.mfls.annotation.UserMobile;
import com.ozone.mfls.beans.RespBean;
import com.ozone.mfls.beans.Urls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName TestAuthController
 * @Author Ozone
 * @Description 自定义注解
 * @Date 2019/5/31 15:39
 * @Version 1.0
 **/
@RestController
public class TestAuthController {
    private Logger logger = LoggerFactory.getLogger(TestAuthController.class);
    private RedisLockRegistry redisLockRegistry;
    @Autowired
    public TestAuthController(RedisLockRegistry redisLockRegistry){
        this.redisLockRegistry = redisLockRegistry;
    }
    @UserAuthenticate
    @GetMapping(value = Urls.TEST_FILTER)
    public RespBean testAuth(@UserId Long userId, @UserMobile String userMobile)  {
        logger.info("userId : "+ userId + "  userMobile :" + userMobile);
        Lock lock = redisLockRegistry.obtain("lock");
        try {
            boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
            logger.info("b1 is : "+ b1);
            TimeUnit.SECONDS.sleep(5);
            boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
            logger.info("b2 is : "+ b2);
            return RespBean.ok("1", "ok");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            lock.unlock();
        }
        return RespBean.error("aa");
    }

}
