package com.ozone.mfls.controller;

import com.ozone.mfls.beans.RespBean;
import com.ozone.mfls.beans.SA_USERS;
import com.ozone.mfls.server.RedisService;
import com.ozone.mfls.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
/**
 * @Author Ozone
 * @Description 测试
 * @Date 2019/5/28 11:08
 **/
@RestController
public class HelloControllerdemo {
    private Logger logger = Logger.getLogger(HelloControllerdemo.class);
    private UserServer userServer;
    private RedisLockRegistry redisLockRegistry;
    private RedisService redisService;
    @Autowired
    public HelloControllerdemo(UserServer userServer, RedisLockRegistry redisLockRegistry,RedisService redisService) {
        this.userServer = userServer;
        this.redisLockRegistry = redisLockRegistry;
        this.redisService = redisService;
    }

    /**
     * @Author Ozone
     * @Description 查询所有用户
     * @Date 22:24 2019/5/20 0020
     * @Param []
     * @return java.util.List<com.ozone.mfls.beans.SA_USERS>
     **/
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public RespBean Hello(){
        List<SA_USERS> saUsers = userServer.getAll();
        if (saUsers != null && saUsers.size()>0) {
            return RespBean.ok("查询成功",saUsers);
        }else{
            return RespBean.error("查询失败");
        }
    }

   /**
    * @Author Ozone
    * @Description 根据userID查询客户信息
    * @Date 2019/5/24 9:50
    * @Param [userid]
    * @return com.ozone.mfls.beans.RespBean
    **/
    @RequestMapping(value = "/hello/{userid}",method = RequestMethod.GET)
    public RespBean Hello(@PathVariable String userid){
        SA_USERS saUsers = userServer.getUser(userid);
        logger.info("userid="+userid);
        if (saUsers != null ) {
            return RespBean.ok("ok",saUsers);
        }else{
            return RespBean.error("error");
        }
    }
    @RequestMapping(value = "/insertuser",method = RequestMethod.POST)
    public RespBean insertUser(SA_USERS sausers){
        int insertres = userServer.insertUser(sausers);
        if(insertres == 1){
            return RespBean.ok("insert ok");
        }else {
            return RespBean.error("insert fail");
        }
    }
    /**
     * @Author Ozone
     * @Description 测试分布式锁
     * @Date 2019/5/27 15:41
     * @Param []
     * @return void
     **/
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String Test()  {
        Lock lock = redisLockRegistry.obtain("lock");
        try {
            boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
            logger.info("b1 is : "+ b1);
            TimeUnit.SECONDS.sleep(5);
            boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
            logger.info("b2 is : "+ b2);
            return "ok";
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            lock.unlock();
        }
        return "error";
    }
    @RequestMapping(value = "/cach",method = RequestMethod.GET)
    public RespBean cachTest(){
       boolean res = redisService.set("name","ozone");
       if (res){
           return RespBean.ok("插入缓存成功！");
       }else {
           return RespBean.error("插入缓存失败！");
       }
    }
    @RequestMapping(value = "/getCach/{key}",method = RequestMethod.GET)
    public String getCach(@PathVariable String key){
        String res = redisService.get(key).toString();
        return res;
    }
}
